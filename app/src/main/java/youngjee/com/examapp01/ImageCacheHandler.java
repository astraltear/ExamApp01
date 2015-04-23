package youngjee.com.examapp01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.lang.ref.WeakReference;

public class ImageCacheHandler {
    static final String TAG = "ImageCacheHandler";

    private final ImageCache mImageCache;

    public ImageCacheHandler(ImageCache imageCache) {
        mImageCache = imageCache;
    }

    public void setImageBitmap(String url, ImageView imageView) {

        Bitmap bitmap = mImageCache.getBitmapFromCache(url);
        if (bitmap == null) {
            if (cancelDownloaderTask (url , imageView)) {
                BitmapDownloaderTask task = new BitmapDownloaderTask(imageView);
                DownloaderBitmapDrawable drawable = new DownloaderBitmapDrawable(mDefaultBitmap , task);
                imageView.setImageDrawable(drawable);
                task.execute(url);
            }
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    private Bitmap mDefaultBitmap;

    public void setDefaultBitmap(Bitmap bitmap) {
        mDefaultBitmap = bitmap;
    }

    public ImageCache getImageCache() {
        return mImageCache;
    }

    static boolean cancelDownloaderTask (String url , ImageView imageView) {
        BitmapDownloaderTask task = getBitmapDownloaderTask(imageView);
        if (task != null) {
            if (task.downloaderAddress == null || (!task.downloaderAddress.equals(url))) {
                task.cancel(true);
            } else return false;
        }
        return true;
    }

    static BitmapDownloaderTask getBitmapDownloaderTask(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof DownloaderBitmapDrawable) {
                DownloaderBitmapDrawable downloaderBitmapDrawable = (DownloaderBitmapDrawable)drawable;
                return downloaderBitmapDrawable.getBitmapDownloaderTask();
            }
        }
        return null;
    }

    static class DownloaderBitmapDrawable extends BitmapDrawable {

        private final WeakReference<BitmapDownloaderTask> bitmapDownloaderTaskReference;

        DownloaderBitmapDrawable(Bitmap bitmap, BitmapDownloaderTask bitmapDownloaderTask) {
            super(bitmap);

            bitmapDownloaderTaskReference = new WeakReference<BitmapDownloaderTask>(bitmapDownloaderTask);
        }

        public BitmapDownloaderTask getBitmapDownloaderTask() {
            return bitmapDownloaderTaskReference.get();
        }
    }



    class BitmapDownloaderTask extends AsyncTask<String, Void, Bitmap> {
        private String downloaderAddress;
        private final WeakReference<ImageView> imageViewReference;

        public BitmapDownloaderTask(ImageView imageView) {
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            downloaderAddress = params[0];
            return downloadBitmap(downloaderAddress);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                mImageCache.addBitmapToCache(downloaderAddress, bitmap);

                if (imageViewReference != null) {
                    ImageView imageView = imageViewReference.get();
                    BitmapDownloaderTask task = getBitmapDownloaderTask (imageView);

                    if (this == task) {
                        imageView.setImageBitmap(bitmap);
                    }
                }
            }
        }
    }

    Bitmap downloadBitmap(String url) {
        final HttpClient client = new DefaultHttpClient();
        final HttpGet getRequest = new HttpGet(url);

        try {
            HttpResponse response = client.execute(getRequest);
            final int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                final HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream inputStream = null;
                    try {
                        inputStream = entity.getContent();
                        return BitmapFactory.decodeStream(inputStream);
                    } finally {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        entity.consumeContent();
                    }
                }
            } else
                throw new Exception("Error Code : " + statusCode);
        } catch (Exception e) {
            getRequest.abort();
            Log.e(TAG, e.toString());
        }
        return null;
    }

}

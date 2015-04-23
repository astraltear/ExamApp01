package youngjee.com.examapp01;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ImageCache implements Serializable {

    Map<String, SynchronizedBitmap> synchronizedBitmapMap;

    public ImageCache() {
        synchronizedBitmapMap = new HashMap<String, SynchronizedBitmap>();
    }

    void addBitmapToCache(String url,Bitmap bitmap) {
        synchronizedBitmapMap.put(url, new SynchronizedBitmap(bitmap));
    }

    Bitmap getBitmapFromCache(String url) {
        SynchronizedBitmap bitmap = synchronizedBitmapMap.get(url);

        if (bitmap != null) {
            return bitmap.get();
        }
        return null;
    }

    public void clearCache(){
        synchronizedBitmapMap.clear();
    }

    public static ImageCache toImageCache(String fileName) {
        ImageCache imageCache = null;

        try {
            imageCache = (ImageCache) ObjectRepository.readObject(fileName);
        } catch (Exception e){
        }
        return imageCache;
    }

    public static boolean fromImageCache(String fileName,ImageCache cache){
        try {
            ObjectRepository.saveObject(cache,fileName);
            return true;
        } catch (Exception e){

        }
        return false;
    }
}


final class SynchronizedBitmap implements Serializable{
    private final Bitmap bitmap;

    SynchronizedBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap get() {
        return bitmap;
    }
}
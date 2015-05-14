package youngjee.com.examapp01.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import youngjee.com.examapp01.AuidSampleActivity;
import youngjee.com.examapp01.AuilConstants;
import youngjee.com.examapp01.R;


public class ImageGalleryFragment extends BaseFragment {

    public static final int INDEX = 3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fr_image_gallery, container, false);
        Gallery gallery = (Gallery) rootView.findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter(getActivity()));
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startImagePagerActivity(position);
            }
        });
        return rootView;
    }

    protected void startImagePagerActivity(int position) {
        Intent intent = new Intent(getActivity(), AuidSampleActivity.class);
        intent.putExtra(AuilConstants.Extra.FRAGMENT_INDEX, ImagePagerFragment.INDEX);
        intent.putExtra(AuilConstants.Extra.IMAGE_POSITION, position);
        startActivity(intent);
    }

    private static class ImageAdapter extends BaseAdapter{

        private static final String[] IMAGES_URLS = AuilConstants.IMAGES;

        private LayoutInflater inflater;
        private DisplayImageOptions options;

        ImageAdapter(Context context) {
            inflater = LayoutInflater.from(context);

            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.ic_stub)
                    .showImageForEmptyUri(R.drawable.ic_empty)
                    .showImageOnFail(R.drawable.ic_error)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
        }


        @Override
        public int getCount() {
            return IMAGES_URLS.length;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = (ImageView) convertView;

            if (imageView == null) {
                imageView = (ImageView) inflater.inflate(R.layout.item_gallery_image, parent, false);
            }
            ImageLoader.getInstance().displayImage(IMAGES_URLS[position], imageView, options);
            return imageView;
        }

        }
    }

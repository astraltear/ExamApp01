package youngjee.com.examapp01;

import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


public class ImageCacheActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_cache);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);

        ImageRepository.INSTANCE.setDefaultBitmap(bitmap);

        setListAdapter(new CustomAdapter());
    }

    class CustomAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return sImageUrlTemplates.length;
        }

        @Override
        public Object getItem(int position) {
            return sImageUrlTemplates[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if(convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.sample_list_item, null);
            }

            imageView = (android.widget.ImageView) convertView.findViewById(R.id.imageview);
            String url = sImageUrlTemplates[position];
            ImageRepository.INSTANCE.setImageBitmap(url,imageView);
            return convertView;
        }
    }


    static final String[] sImageUrlTemplates = new String[] {
            "http://i.ytimg.com/vi/OlCqsg4BO_o/1.jpg",
            "http://i.ytimg.com/vi/S4Rq0emfkKM/1.jpg",
            "http://i.ytimg.com/vi/ZGGiw-rX9Rw/1.jpg",
            "http://i.ytimg.com/vi/iU7y1Ek-kNg/1.jpg",
            "http://i.ytimg.com/vi/RSfKzWreYdY/1.jpg",
            "http://i.ytimg.com/vi/WZRhUB-q0Ng/1.jpg",
            "http://i.ytimg.com/vi/uCuNO051s-4/1.jpg",
            "http://i.ytimg.com/vi/390cjPv5v74/1.jpg",
            "http://i.ytimg.com/vi/5SdsqdtKwD4/1.jpg",
            "http://i.ytimg.com/vi/vyKkK_d_vfQ/1.jpg",
            "http://i.ytimg.com/vi/qhNmD5UVNB8/1.jpg",
            "http://i.ytimg.com/vi/WDMVSrNWPl0/1.jpg",
            "http://i.ytimg.com/vi/FwxVtO-qfpo/1.jpg",
            "http://i.ytimg.com/vi/YXxwTIcqKgE/1.jpg",
            "http://i.ytimg.com/vi/uvZbZ0fsVi4/1.jpg",
            "http://i.ytimg.com/vi/OutKeH323Qk/1.jpg",
            "http://i.ytimg.com/vi/0rtGX0dycXc/1.jpg",
            "http://i.ytimg.com/vi/8QOGGu8139A/1.jpg",
            "http://i.ytimg.com/vi/zNWovBUaStU/1.jpg",
            "http://i.ytimg.com/vi/q18p2OopLJg/1.jpg",
            "http://i.ytimg.com/vi/qO5hYDytyro/1.jpg",
            "http://i.ytimg.com/vi/8-ObOrQGtVw/1.jpg",
            "http://i.ytimg.com/vi/bI3ip1TAWkY/1.jpg",
            "http://i.ytimg.com/vi/JQLcFHuaawg/1.jpg",
            "http://i.ytimg.com/vi/-T3eTP3X8Zk/1.jpg"
    };
}

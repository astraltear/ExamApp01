package youngjee.com.examapp01;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import youngjee.com.examapp01.fragment.ImageGalleryFragment;
import youngjee.com.examapp01.fragment.ImageGridFragment;
import youngjee.com.examapp01.fragment.ImageListFragment;
import youngjee.com.examapp01.fragment.ImagePagerFragment;

public class AuidSampleActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int frIndex = getIntent().getIntExtra(AuilConstants.Extra.FRAGMENT_INDEX, 0);
        Fragment fragment;
        String tag;
        String titleRes;

        switch (frIndex){
            default:
            case ImageListFragment.INDEX:
                tag = ImageListFragment.class.getSimpleName();
//                Log.d(">>>>>>>>>>", tag);
                fragment = getSupportFragmentManager().findFragmentByTag(tag);

                if(fragment == null) {
                    fragment = new ImageListFragment();
                }

                titleRes = "Image List Example";
                break;

            case ImagePagerFragment.INDEX:
                tag = ImagePagerFragment.class.getSimpleName();
                fragment = getSupportFragmentManager().findFragmentByTag(tag);

                if(fragment == null) {
                    fragment = new ImagePagerFragment();
                    fragment.setArguments(getIntent().getExtras());
                }

                titleRes = "Image Pager Example";
                break;

            case ImageGridFragment.INDEX:
                tag = ImageGridFragment.class.getSimpleName();
                fragment = getSupportFragmentManager().findFragmentByTag(tag);

                if(fragment == null) {
                    fragment = new ImageGridFragment();
                }

                titleRes = "Image Grid Example";
                break;

            case ImageGalleryFragment.INDEX:
                tag = ImageGalleryFragment.class.getSimpleName();
                fragment = getSupportFragmentManager().findFragmentByTag(tag);

                if(fragment == null) {
                    fragment = new ImageGalleryFragment();
                }

                titleRes = "Image Gallery Example";
                break;

        }

        setTitle(titleRes);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fragment, tag).commit();


    }

}

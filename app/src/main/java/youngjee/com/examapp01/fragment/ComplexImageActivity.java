package youngjee.com.examapp01.fragment;

import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import youngjee.com.examapp01.R;

public class ComplexImageActivity extends FragmentActivity {

    private static final String STATE_POSITION = "STATE_POSITION";

    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_image);

        int pagePosition = savedInstanceState == null ? 0 : savedInstanceState.getInt(STATE_POSITION);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new ImagePagerAdapter(getSupportFragmentManager()));
        pager.setCurrentItem(pagePosition);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_POSITION,pager.getCurrentItem());
    }


    private class ImagePagerAdapter extends FragmentPagerAdapter {

        Fragment listFragment;
        Fragment gridFragment;
        Fragment galleryFragment;

        ImagePagerAdapter(FragmentManager fm){
            super(fm);
            listFragment = new ImageListFragment();
            gridFragment = new ImageGridFragment();
            galleryFragment = new ImageGalleryFragment();

        }

        @Override
        public Fragment getItem(int position) {
         switch (position){
             case 0:
                 return listFragment;
             case 1:
                 return gridFragment;
             case 2:
                 return galleryFragment;
             default:
                 return null;
         }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "LIST";
                case 1:
                    return "GRID";
                case 2:
                    return "Gallery";
                default:
                    return null;
            }
        }
    }
}

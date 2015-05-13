package youngjee.com.examapp01;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import youngjee.com.examapp01.fragment.ImageListFragment;


public class AuilSampleMainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auil_sample_main);
    }

    public void onImageListClick(View view) {
        Intent intent = new Intent(this, AuidSampleActivity.class);
        intent.putExtra(AuilConstants.Extra.FRAGMENT_INDEX, ImageListFragment.INDEX);
        startActivity(intent);
    }
}

package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class GlideDemoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_demo);

        ImageView imageView = (ImageView) findViewById(R.id.my_image_view);
        Glide.with(this).load("http://goo.gl/gEgYUd").into(imageView);
    }

}

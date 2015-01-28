package youngjee.com.examapp01;

import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;


public class AssertImageReadActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assert_image_read);

        ImageView imageView = (ImageView) findViewById(R.id.idImg);

        try {
            InputStream is = getAssets().open("image1.jpg");
            Drawable drawable = Drawable.createFromStream(is, null);

            if (drawable != null){
                imageView.setImageDrawable(drawable);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}

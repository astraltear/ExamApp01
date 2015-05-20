package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;


public class SlideUpPanelActivity extends ActionBarActivity {

    private String TAG = getClass().getSimpleName();
    private SlidingUpPanelLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_up_panel);

        setSupportActionBar((Toolbar) findViewById(R.id.main_toolbar));

        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        mLayout.setPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View view, float slideOffset) {
                Log.d(TAG, "onPanelSlide, offset:" + slideOffset);
            }

            @Override
            public void onPanelCollapsed(View view) {
                Log.d(TAG, "onPanelCollapsed");

            }

            @Override
            public void onPanelExpanded(View view) {
                Log.d(TAG, "onPanelExpanded");

            }

            @Override
            public void onPanelAnchored(View view) {
                Log.d(TAG, "onPanelAnchored");

            }

            @Override
            public void onPanelHidden(View view) {
                Log.d(TAG, "onPanelHidden");

            }
        });

        TextView tv = (TextView) findViewById(R.id.name);
        tv.setText(Html.fromHtml("<![CDATA[<b>The Awesome Sliding Up Panel</b><br/> Brought to you by<br/><a href=\"http://umanoapp.com\">http://umanoapp.com</a>]]>"));
        Button btn = (Button) findViewById(R.id.follow);
        btn.setText(Html.fromHtml("<![CDATA[Follow us<br/>on <a href=\"http://twitter.com/umanoapp\">Twitter</a>]]>"));

    }

}

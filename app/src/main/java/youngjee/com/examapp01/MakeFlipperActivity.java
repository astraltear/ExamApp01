package youngjee.com.examapp01;

import android.app.ActionBar;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;


public class MakeFlipperActivity extends ActionBarActivity {

    private CheckBox checkBox;
    private ViewFlipper viewFlipper;
    float xAtDown;
    float xAtUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_flipper);

        checkBox = (CheckBox) findViewById(R.id.chkAuto);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    viewFlipper.setInAnimation(AnimationUtils.loadAnimation(MakeFlipperActivity.this,R.anim.push_left_in));
                    viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getBaseContext(),R.anim.push_left_out));

                    viewFlipper.setFlipInterval(4000);
                    viewFlipper.startFlipping();
                } else {
                    viewFlipper.stopFlipping();
                }
            }
        });

        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if ( v != viewFlipper) return  false;

                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    xAtDown = event.getX();

                } else if(event.getAction() == MotionEvent.ACTION_UP) {
                    xAtUp = event.getX();

                    if (xAtUp < xAtDown){
                        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getBaseContext(),R.anim.push_left_in));
                        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getBaseContext(),R.anim.push_left_out));

                        viewFlipper.showNext();
                    } else if (xAtUp > xAtDown){
                        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getBaseContext(),R.anim.push_right_in));
                        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getBaseContext(),R.anim.push_right_out));

                        viewFlipper.showPrevious();
                    }

                }

                return true;
            }
        });

        TextView tv = new TextView(this);
        tv.setText("View 4 Dynamic add");

        tv.setTextColor(Color.CYAN);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,Gravity.CENTER|Gravity.CENTER_VERTICAL);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER | Gravity.CENTER_VERTICAL);
        tv.setLayoutParams(params);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,  30);
        viewFlipper.addView(tv);
    }

}

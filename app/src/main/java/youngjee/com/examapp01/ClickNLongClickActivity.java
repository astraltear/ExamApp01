package youngjee.com.examapp01;

import android.os.Build;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ClickNLongClickActivity extends ActionBarActivity {

    private float mLastMotionX=0;
    private float mLastMotionY=0;

    private int mTouchSlop;

    private boolean mHasPerformedLongPress;
    private CheckForLongPress mPendingCheckForLongPress;

    private Handler mHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_nlong_click);

        setBuildInfo();

        mHandler = new Handler();
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        Button btn_longClick = (Button) findViewById(R.id.btn_longClick);

        btn_longClick.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mLastMotionX = event.getX();
                        mLastMotionY = event.getY();

                        mHasPerformedLongPress = false;

                        postCheckForLongClick(0);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        final float x = event.getX();
                        final float y = event.getY();
                        final int deltaX =  Math.abs((int) (mLastMotionX - x));
                        final int deltaY =  Math.abs((int)(mLastMotionY - y));

                        if (deltaX >=mTouchSlop || deltaY >=mTouchSlop){
                            if(!mHasPerformedLongPress){
                                removeLongPressCallback();
                            }
                        }
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        if(!mHasPerformedLongPress){
                            removeLongPressCallback();
                        }
                        break;

                    case MotionEvent.ACTION_UP:
                        if(!mHasPerformedLongPress){
                            removeLongPressCallback();
                            performOneClick();
                        }
                        break;
                }
                return false;
            }
        });
    }

    private void setBuildInfo() {
        TextView tv_build_info = (TextView) findViewById(R.id.tv_build_info);

        StringBuffer buffer = new StringBuffer();
        buffer.append("Build.BOARD:").append(Build.BOARD).append("\n")
                .append("Build.BRAND:").append(Build.BRAND).append("\n")
                .append("Build.CPU_ABI:").append(Build.CPU_ABI).append("\n")
                .append("Build.DEVICE:").append(Build.DEVICE).append("\n")
                .append("Build.DISPLAY:").append(Build.DISPLAY).append("\n")
                .append("Build.FINGERPRINT:").append(Build.FINGERPRINT).append("\n")
                .append("Build.HOST:").append(Build.HOST).append("\n")
                .append("Build.ID:").append(Build.ID).append("\n")
                .append("Build.MANUFACTURER:").append(Build.MANUFACTURER).append("\n")
                .append("Build.MODEL:").append(Build.MODEL).append("\n")
                .append("Build.PRODUCT:").append(Build.PRODUCT).append("\n")
                .append("Build.TAGS:").append(Build.TAGS).append("\n")
                .append("Build.TYPE:").append(Build.TYPE).append("\n")
                .append("Build.USER:").append(Build.USER).append("\n")
                .append("Build.HARDWARE:").append(Build.HARDWARE).append("\n")
                .append("Build.SERIAL:").append(Build.SERIAL).append("\n")
                .append("Build.VERSION.RELEASE:").append(Build.VERSION.RELEASE).append("\n");

        tv_build_info.setText(buffer.toString());
    }

    class CheckForLongPress implements Runnable{

        @Override
        public void run() {
            if(performLongClick()) {
                mHasPerformedLongPress = true;
            }
        }
    }

    private void postCheckForLongClick(int delayOffset){
        mHasPerformedLongPress=false;

        if(mPendingCheckForLongPress == null){
            mPendingCheckForLongPress = new CheckForLongPress();
        }
        mHandler.postDelayed(mPendingCheckForLongPress, ViewConfiguration.getLongPressTimeout() - delayOffset);
    }

    private void removeLongPressCallback(){
        if(mPendingCheckForLongPress != null){
            mHandler.removeCallbacks(mPendingCheckForLongPress);
        }
    }

    private void performOneClick(){
        Toast.makeText(ClickNLongClickActivity.this,"One Click OK!!",Toast.LENGTH_SHORT).show();
    }

    private boolean performLongClick(){
        Toast.makeText(ClickNLongClickActivity.this,"Long Click OK!!",Toast.LENGTH_SHORT).show();
        return true;
    }

}

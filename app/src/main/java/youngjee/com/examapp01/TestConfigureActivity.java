package youngjee.com.examapp01;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;


public class TestConfigureActivity extends ActionBarActivity {

    private static final String TAG = "TestConfigureActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_configure);

        Log.d(TAG, getResources().getConfiguration().orientation + "|" + Configuration.ORIENTATION_LANDSCAPE+"|"+Configuration.ORIENTATION_PORTRAIT);
        Log.d(TAG, "getScreenWidth:"+getScreenWidth(this));
        Log.d(TAG, "getScreenHeight:"+getScreenHeight(this));
        Log.d(TAG, "density:"+this.getResources().getDisplayMetrics().density);

    }

    /*
    *
    * AndroidManifest 파일에 configChanges 항목이 설정이 되어야만 호출이 된다.
    * */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged");
    }

    public int getScreenWidth(Activity activity){
        int width=0;
        width = activity.getWindowManager().getDefaultDisplay().getWidth();
        return width;
    }

    public int getScreenHeight(Activity activity){
        int height=0;
        height = activity.getWindowManager().getDefaultDisplay().getHeight();
        return height;
    }

    /*
    * px to dip 로 변환
    * */
    public int getPxToDip(Context con, int px){
        float density = 0.0f;
        density = con.getResources().getDisplayMetrics().density;
        return (int) (px / density);
    }
}

package youngjee.com.examapp01;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class RotateExamActivity extends Activity {

    /*
    * 화면이 회전되면 activity가 종료되고 다시 실행된다.
    * onCreate, onDestroy 가 다시 호출되는데 이걸 해결하기 위해서는 아래의 방법을 사용한다.
    *
    *   <activity
            android:name=".RotateExamActivity"
            android:label="@string/title_activity_rotate_exam"
            android:configChanges="orientation|keyboardHidden|screenSize">

    * onConfigurationChanged 메소드를 오버라이드 한다.
    * 이렇게 하면 onCreate, onDestroy가 호출되지 않는다.
    *
    *  화면을 고정하려면
    *  android:screenOrientation="portrait" or android:screenOrientation="landscape"
    *
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
         AndroidManifest에 아래 의 두 개의 내용을 적용하면 반투명 효과가 나온다고 하나 작동안함
          아래의 activity theme만 설정하면 투명으로는 작동함
         android:theme="@android:style/Theme.Translucent">

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
*/
        setContentView(R.layout.activity_rotate_exam);

        TextView textView = (TextView) findViewById(R.id.tv_info);

        textView.setText(makeInfoText());

        Toast.makeText(this,"onCreate",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Toast.makeText(this,"onConfigurationChanged:"+this.getResources().getConfiguration().orientation,Toast.LENGTH_SHORT).show();

        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            //세로 전환시
        } else if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 가로 전환시
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT).show();
    }

    private String makeInfoText(){
        StringBuffer strbuf = new StringBuffer();
        strbuf.append("소스에 보면 해당 내용에 대한 자세한 설명이 있음.. \r\n");
        return strbuf.toString();
    }
}

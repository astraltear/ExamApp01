package youngjee.com.examapp01;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class PopupActivity extends Activity {

    /*
    * ActionBarActivity를 extend 해서 아래의 코드 requestWindowFeature 를 호출하면
    * requestWindowFeature() must be called before adding content 에러가 난다.
    *  Activity를 extends를 해야만 한다.
    *  AndroidManifest.xml 파일에서 아래의 내용을 추가해야 한다.
    *  android:theme="@android:style/Theme.Dialog"
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupActivity.this.finish();
            }
        });
    }


}

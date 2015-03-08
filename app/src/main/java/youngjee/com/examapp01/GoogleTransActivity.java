package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.api.GoogleAPI;
import com.google.api.GoogleAPIException;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;

public class GoogleTransActivity extends ActionBarActivity {

    private EditText et_trans_text = null;
    private TextView tv_translated = null;
    private Button btn_trans = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_trans);

        et_trans_text = (EditText) findViewById(R.id.et_trans_text);
        tv_translated = (TextView) findViewById(R.id.tv_translated);
        btn_trans = (Button) findViewById(R.id.btn_trans);

        btn_trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                * https://code.google.com/p/google-api-translate-java/
                * 이 어플리케이션은 작동하지 않는다.
                * 사용방법이 아래와 같이 심플한 버전에서 Oauth및 다른 방법으로 완전히 교체됨
                * */
                GoogleAPI.setHttpReferrer("http://darap.blog.me");
                GoogleAPI.setKey("AIzaSyBxOWtXZIKWsjjcDXVjyxPuGet6f1dcsKQ");

                String from = et_trans_text.getText().toString();
                String after="";
                try {
                    after = Translate.DEFAULT.execute(from, Language.AUTO_DETECT, Language.KOREAN);

                } catch (GoogleAPIException e) {
                    e.printStackTrace();
                }

                tv_translated.setText(after);

            }
        });


    }

}

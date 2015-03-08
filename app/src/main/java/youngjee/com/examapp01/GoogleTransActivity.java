package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
                * 아래코드는 작동하지 않는다.아래의 두 개의 메소드가 있어야만 translate를 사용할 수 있다.
                *GoogleAPI.setHttpReferrer("http://yoursite.com");
                * GoogleAPI.setKey( Enter your API key here );
                * */

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

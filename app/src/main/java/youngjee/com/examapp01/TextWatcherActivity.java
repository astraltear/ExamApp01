package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class TextWatcherActivity extends ActionBarActivity {

    private static final int EMAIL = 0;
    private static final int NAME= 1;
    private static final int CONFIRM = 2;
    private EditText [] etTexts;
    private Button[] buttons ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_watcher);

        setLayout();

        etTexts[EMAIL].addTextChangedListener(emailWatcher);
        etTexts[NAME].addTextChangedListener(nameWatcher);
    }

    private TextWatcher emailWatcher = new TextWatcher() {
        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override public void afterTextChanged(Editable s) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(s.length() > 0){
                buttons[EMAIL].setEnabled(true);
            } else {
                buttons[EMAIL].setEnabled(false);
            }
            setEnable();
        }
    };

    private TextWatcher nameWatcher = new TextWatcher() {
        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override public void afterTextChanged(Editable s) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(s.length() > 0){
                buttons[NAME].setEnabled(true);
            } else {
                buttons[NAME].setEnabled(false);
            }
            setEnable();
        }
    };

    private void setEnable() {
        boolean isAllFull = false;

        if(etTexts[EMAIL] != null && etTexts[EMAIL].length() > 0 && etTexts[NAME] != null && etTexts[NAME].length() > 0 ){
            isAllFull= true;
        }

        buttons[CONFIRM].setEnabled(isAllFull);
    }

    public void onButtonClick(View v){
        switch (v.getId()){
            case R.id.btn_confirm:
                if (buttons[CONFIRM].isEnabled()){
                    Toast.makeText(getApplicationContext(),"EMAIL REGIST["+etTexts[EMAIL].getText().toString()+"] NAME REGIST["+etTexts[NAME].getText().toString()+"]",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"ALL INPUT REGIST",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void setLayout() {
        etTexts = new EditText[]{
                (EditText) findViewById(R.id.et_box_01),
                (EditText) findViewById(R.id.et_box_02)
        };

        buttons = new Button[]{
                (Button) findViewById(R.id.btn_check_01),
                (Button) findViewById(R.id.btn_check_02),
                (Button) findViewById(R.id.btn_confirm)
        };

       for (Button b : buttons){
           b.setEnabled(false);
       }
    }
}

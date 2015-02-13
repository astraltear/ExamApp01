package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class DialogCallBackActivity extends ActionBarActivity implements View.OnClickListener {

    private Button [] mButtons;
    private CustomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_call_back);
        setLayout();
    }

    private void setLayout() {
        mButtons = new Button[]{(Button) findViewById(R.id.btn_dialog)};

        for (Button button : mButtons){
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dialog:
                dialog = new CustomDialog(this,mOnclickInterface);
                dialog.setTitle("알림!!!!!!");
                dialog.show();
                break;
            default:
                break;

        }
    }

    private IOnclickInterface mOnclickInterface = new IOnclickInterface() {
        @Override
        public void onClick() {
            Toast.makeText(DialogCallBackActivity.this,"Dialog Click!!!",Toast.LENGTH_LONG).show();

            if (dialog != null && dialog.isShowing()){
                dialog.dismiss();
            }
        }

        @Override
        public void show() {
            Toast.makeText(DialogCallBackActivity.this,"Dialog SHow!!",Toast.LENGTH_LONG).show();
        }

        @Override
        public void dismiss() {
            Toast.makeText(DialogCallBackActivity.this,"Dialog Dismiss!!",Toast.LENGTH_LONG).show();
        }

        @Override
        public void getTitle(CharSequence title) {
            Toast.makeText(DialogCallBackActivity.this,title,Toast.LENGTH_LONG).show();

        }
    };
}

package youngjee.com.examapp01;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MakeProgressActivity extends ActionBarActivity {

    ProgressDialog dialog = null;
    Handler handler = null;

    static final int PROGRESS_DIALOG=0;
    static final int PROGRESS_DIALOG3=1;
    Handler m_handle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_progress);

        handler = new Handler(){
            public void handleMessage(Message msg) {
            dialog.dismiss();
            }
        };

        m_handle = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                dismissDialog(PROGRESS_DIALOG);
            }
        };

        Button button = (Button) findViewById(R.id.btn_progress1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = ProgressDialog.show(MakeProgressActivity.this, "Noti", "waiting...", false, true);

                handler.sendEmptyMessageDelayed(0, 5000);
            }
        });

        Button button1 = (Button) findViewById(R.id.btn_progress2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(PROGRESS_DIALOG);
                m_handle.sendEmptyMessageDelayed(0, 5000);
            }
        });

        Button button3 = (Button) findViewById(R.id.btn_progress3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(PROGRESS_DIALOG3);
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case PROGRESS_DIALOG: {
                ProgressDialog dialog1 = new ProgressDialog(this);
                dialog1.setTitle("DialogTItle");
                dialog1.setMessage("Please Waiting....");
                dialog1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog1.setCancelable(true);
                return dialog1;
            }

            case PROGRESS_DIALOG3 : {
                ProgressDialog dialog1 = new ProgressDialog(this);
                dialog1.setTitle("Dialog HORIZONTAL");
                dialog1.setMessage("Please Waiting....");
                dialog1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dialog1.setCancelable(true);
                dialog1.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }
                });

                return dialog1;
            }
        }
       return  null;
    }
}

package youngjee.com.examapp01;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MakeProgressActivity extends ActionBarActivity {

    ProgressDialog dialog = null;
    Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_progress);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                dialog.dismiss();
            }
        };

        Button button = (Button) findViewById(R.id.btn_progress1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = ProgressDialog.show(MakeProgressActivity.this, "Noti", "waiting...", false, true);

                handler.sendEmptyMessageDelayed(0, 3000);
            }
        });

    }


}

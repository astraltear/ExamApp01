package youngjee.com.examapp01;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TestNotificationActivity extends ActionBarActivity {

    private NotificationManager nm = null;

    private EditText et_Tiker = null;
    private EditText et_Title = null;
    private EditText et_Message = null;
    private Button btn_Commit = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_notification);
        
        setLayout();
    }

    private void setLayout() {
        et_Tiker = (EditText) findViewById(R.id.et_tiker);
        et_Title = (EditText) findViewById(R.id.et_title);
        et_Message = (EditText) findViewById(R.id.et_message);

        btn_Commit = (Button) findViewById(R.id.btn_commit);
        btn_Commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                PendingIntent pendingIntent = PendingIntent.getActivity(TestNotificationActivity.this, 0, new Intent(TestNotificationActivity.this, NotificationConfirm.class), 0);

                String tiker = et_Tiker.getText().toString();
                String title = et_Title.getText().toString();
                String message = et_Message.getText().toString();

                Notification notification = new Notification(android.R.drawable.btn_star, tiker, System.currentTimeMillis());

                notification.setLatestEventInfo(TestNotificationActivity.this,title,message,pendingIntent);

                nm.notify(3456,notification);
                Toast.makeText(TestNotificationActivity.this,"노티등록!!",Toast.LENGTH_LONG).show();
            }
        });

    }

}

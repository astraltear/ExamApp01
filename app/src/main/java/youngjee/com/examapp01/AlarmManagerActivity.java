package youngjee.com.examapp01;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Context.ALARM_SERVICE;

public class AlarmManagerActivity extends ActionBarActivity implements View.OnClickListener {

    private Button mButtons [];
    private TextView tv_info ;
    private static final String INTENT_ACTION = "youngjee.alarmmanager";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);
        setLayout();
    }

    private void setLayout() {
        mButtons = new Button[]{
                (Button) findViewById(R.id.btn_set_alarm),
                (Button) findViewById(R.id.btn_release_alarm)
        };

        for (Button b : mButtons){
            b.setOnClickListener(this);
        }

        tv_info = (TextView) findViewById(R.id.tv_info);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_set_alarm :
                setAlarm(this, 5000);
                tv_info.setText("set Alarm");
                break;

            case R.id.btn_release_alarm:
                releaseAlarm(this);
                tv_info.setText("cancel Alarm");
                break;

            default:
                break;
        }

    }

    private void releaseAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(INTENT_ACTION);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        alarmManager.cancel(pendingIntent);
    }

    private void setAlarm(Context context, long second) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(INTENT_ACTION);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        alarmManager.set(AlarmManager.RTC,System.currentTimeMillis()+second,pendingIntent);
    }
}

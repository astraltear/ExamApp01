package youngjee.com.examapp01;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class AlarmManager2Activity extends ActionBarActivity {

    private Button set_alarm_manager_button;
    private Button cancel_alarm_manager_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager2);

        final AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(AlarmManager2Activity.this, AlarmReceiver.class);
        final PendingIntent sender = PendingIntent.getBroadcast(AlarmManager2Activity.this, 0, intent, 0);

        set_alarm_manager_button = (Button) findViewById(R.id.set_alarm_manager_button);
        set_alarm_manager_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(),1000,sender);
            }
        });

        cancel_alarm_manager_button = (Button) findViewById(R.id.cancel_alarm_manager_button);
        cancel_alarm_manager_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                am.cancel(sender);
            }
        });

    }
}

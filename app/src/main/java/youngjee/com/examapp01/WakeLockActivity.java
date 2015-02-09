package youngjee.com.examapp01;

import android.content.Context;
import android.os.PowerManager;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class WakeLockActivity extends ActionBarActivity {

    PowerManager.WakeLock m_sleep_lock = null;
    Vibrator vibrator = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_lock);

        PowerManager power = (PowerManager) getSystemService(Context.POWER_SERVICE);
        m_sleep_lock = power.newWakeLock(PowerManager.FULL_WAKE_LOCK, "NonSleepActivity");

        m_sleep_lock.acquire();

        Button button = (Button) findViewById(R.id.btn_setOn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.textView3);

                if(m_sleep_lock.isHeld()){
                    m_sleep_lock.release();
                    textView.setText("꺼지지 않도록 설정!!");
                } else {
                    m_sleep_lock.acquire();
                    textView.setText("꺼지도록 설정!!");

                }
            }
        });

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Button button1 = (Button) findViewById(R.id.btn_vibrate1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(3000);
            }
        });

        Button button2 = (Button) findViewById(R.id.btn_vibrate2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long[] pattern = {50, 100, 100, 200, 200, 400, 400, 800};
                vibrator.vibrate(pattern,0);
            }
        });


        Button button3 = (Button) findViewById(R.id.btn_vibrateCancle);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.cancel();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (m_sleep_lock.isHeld()){
            m_sleep_lock.release();
        }
    }
}

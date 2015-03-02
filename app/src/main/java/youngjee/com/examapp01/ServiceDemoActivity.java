package youngjee.com.examapp01;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import youngjee.com.examapp01.service.MusicService;


public class ServiceDemoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);
        Button button = (Button) findViewById(R.id.btn_play);
        Button button1 = (Button) findViewById(R.id.btn_stop);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startService(new Intent("young.jee.musicservice"));
                startService(new Intent(getBaseContext(),MusicService.class));
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent("youngjee.jee.musicservice"));
            }
        });

    }

}

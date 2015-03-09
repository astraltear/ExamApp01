package youngjee.com.examapp01;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;


public class MP3PlayerDemoActivity extends ActionBarActivity {
    private MediaPlayer mp ;
    private EditText et_mp3Url;
    private Button btn_open;
    private Button btn_play;
    private Button btn_stop;
    private CheckBox cb_repeat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp3_player_demo);

        et_mp3Url = (EditText) findViewById(R.id.et_mp3Url);
        cb_repeat = (CheckBox) findViewById(R.id.cb_repeat);
        btn_open = (Button) findViewById(R.id.btn_open);
        btn_play = (Button) findViewById(R.id.btn_play);
        btn_stop = (Button) findViewById(R.id.btn_stop);

        et_mp3Url.setText(Environment.getExternalStorageDirectory()+"/"+Environment.DIRECTORY_DOWNLOADS+"/bensound_cute.mp3");

        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!loadAudio()){
                    Toast.makeText(getApplicationContext(), "파일 불러오기에 실패했습니다.", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Toast.makeText(getApplicationContext(), "파일 불러오기 완료.", Toast.LENGTH_LONG).show();
                }
            }
        });

        cb_repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_repeat.isChecked()){
                    mp.setLooping(true);
                } else {
                    mp.setLooping(false);
                }
            }
        });

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PlayPauseAudio()==0){
                    btn_play.setText("일시정지");
                }else{
                    btn_play.setText("재생");
                }
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mp != null){
            mp.release();
        }
        mp = null;
    }

    private int PlayPauseAudio(){
        if(mp.isPlaying()){
            mp.pause();
            return 1;
        } else {
            mp.start();
            return 0;
        }
    }

    private boolean loadAudio(){
        mp = new MediaPlayer();
        try {
            mp.setDataSource(et_mp3Url.getText().toString());
            mp.prepare();
            return  true;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            return  false;
        }
    }

}

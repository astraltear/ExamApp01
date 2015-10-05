package youngjee.com.examapp01;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.concurrent.TimeUnit;


public class PlayVideoActivity extends ActionBarActivity {

    final static String SAMPLE_VIDEO_URL="http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    VideoView videoView;
    SeekBar seekBar;
    Handler updateHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        EditText tvURL = (EditText) findViewById(R.id.etVideoURL);
        tvURL.setText(SAMPLE_VIDEO_URL);

        videoView = (VideoView) findViewById(R.id.videoView);
        // MediaController mc = new MediaController(this);
        // videoView.setMediaController(mc);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
    }

    public  void loadVideo(View view){
        EditText tvURL = (EditText) findViewById(R.id.etVideoURL);
        String url = tvURL.getText().toString();

        Toast.makeText(getApplicationContext(),"Loading Video Plz wait",Toast.LENGTH_LONG).show();
        videoView.setVideoURI(Uri.parse(url));
        videoView.requestFocus();

        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        Toast.makeText(getApplicationContext(), "Buffering", Toast.LENGTH_LONG).show();
                        break;

                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        Toast.makeText(getApplicationContext(), "Buffering finished.\nResume Playing", Toast.LENGTH_LONG).show();
                        videoView.start();
                        break;
                }

                return false;
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
                long finalTime = videoView.getDuration();
                TextView tvTotalTime = (TextView) findViewById(R.id.tvTotalTime);
                tvTotalTime.setText(String.format("%d:%d",
                                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime)))
                );

                seekBar.setMax((int) finalTime);
                seekBar.setProgress(0);
                updateHandler.postDelayed(updateVideoTime, 100);
                Toast.makeText(getApplicationContext(),"Playing Video", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void playVideo(View view){
        videoView.requestFocus();
        videoView.start();
    }

    public void pauseVideo(View view){
        videoView.pause();
    }

    private Runnable updateVideoTime = new Runnable() {
        @Override
        public void run() {
            long currentPosition = videoView.getCurrentPosition();
            seekBar.setProgress((int) currentPosition);
            updateHandler.postDelayed(this,100);
        }
    };

}

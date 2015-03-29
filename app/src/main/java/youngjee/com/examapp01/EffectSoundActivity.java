package youngjee.com.examapp01;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


public class EffectSoundActivity extends ActionBarActivity {

    Button btn_download;
    ImageButton btn_play;
    boolean isPlaying;
    File downloadingMediaFile;
    MediaPlayer mPlayer = new MediaPlayer();

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(getBaseContext(), "PLAYING PREPARE !!", Toast.LENGTH_LONG).show();
            prepareAudio();
            btn_play.setEnabled(true);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effect_sound);

        initControls();
    }

    private void initControls() {
        btn_download = (Button) findViewById(R.id.btn_download);
        btn_play = (ImageButton) findViewById(R.id.btn_play);
        btn_play.setEnabled(false);

        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownloadFile();
            }
        });

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayer.isPlaying()) {
                    btn_play.setImageResource(R.drawable.button_play);
                    mPlayer.pause();
                } else {

                    btn_play.setImageResource(R.drawable.button_pause);
                    mPlayer.start();
                }
            }
        });
    }

    private void startDownloadFile() {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    downloadAudioFile("http://www.pocketjourney.com/downloads/pj/tutorials/audio.mp3",handler);
                }
            };
            new Thread(r).start();
    }

    private void downloadAudioFile(String mediaUrl,Handler handler) {
        try {
            URLConnection uc = new URL(mediaUrl).openConnection();
            uc.connect();
            InputStream is = uc.getInputStream();
            if( is == null) {
                Log.d(getClass().getName(), "Unable to create Input Stream for mediaUrl" + mediaUrl);
            }

            downloadingMediaFile = new File(getBaseContext().getCacheDir(), "downloadingMedia.dat");

            if (downloadingMediaFile.exists()) {
                handler.sendEmptyMessage(0);

            } else {
                FileOutputStream fos = new FileOutputStream(downloadingMediaFile);
                byte[] buffer = new byte[1024];

                try {
                    for (int readNum; (readNum = is.read(buffer)) != -1;) {
                        fos.write(buffer, 0, readNum); //no doubt here is 0
                    }
                    fos.close();
                    Log.d(getClass().getName(), "downloadingMediaFile"+downloadingMediaFile.getAbsolutePath());

                    handler.sendEmptyMessage(0);

                } catch (IOException ex) {
                    Log.d(getClass().getName(), "IOException IOExceptionIOExceptionIOExceptionIOException");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prepareAudio(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(downloadingMediaFile);
            mPlayer.setDataSource(fis.getFD());
            mPlayer.prepare();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.reset();
        mPlayer.release();

    }
}

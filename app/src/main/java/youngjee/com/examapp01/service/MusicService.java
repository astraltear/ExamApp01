package youngjee.com.examapp01.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import youngjee.com.examapp01.R;

public class MusicService extends Service {

    private MediaPlayer mediaPlayer = null;

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d("MusicService", "onStartonStartonStartonStartonStart");
        super.onStart(intent, startId);
        mediaPlayer = MediaPlayer.create(this, R.raw.bensound_cute);

//       현재 아래의 코드로 재생이 되지 않는다. 암시적 인텐트 호출에서도 warning을 뱉어내고 onStart가 호출되지 않는다.
//        mediaPlayer.start();

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            public void onPrepared(MediaPlayer mp){
                //재생 사운드 출력을 시작할 준비가되었을 때
                mp.start();
            }
        });
    }

    @Override
    public void onDestroy() {
        Log.d("MusicService", "onDestroyonDestroyonDestroyonDestroyonDestroy");
        mediaPlayer.stop();
        super.onDestroy();

    }
}

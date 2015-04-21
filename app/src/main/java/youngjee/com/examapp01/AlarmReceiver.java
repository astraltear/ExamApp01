package youngjee.com.examapp01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {}

    @Override
    public void onReceive(Context context, Intent intent) {
        MediaPlayer mp = null;
        try {
            if (mp == null) {
                mp = MediaPlayer.create(context, R.raw.dingdong);
                mp.seekTo(0);
                mp.start();
            }
        } catch (Exception e){

        }

    }
}

package youngjee.com.examapp01;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MP3Player2DemoActivity extends ActionBarActivity {

    private String MEDIA_PATH = Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS;
    private List<String> songs = new ArrayList<String>();
    private TextView playStatus;
    private ListView listView3 ;
    private int currentPosition = 0;
    private MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp3_player2_demo);
        playStatus = (TextView) findViewById(R.id.playStatus);

        listView3 = (ListView) findViewById(R.id.listView3);
        listView3.setOnItemClickListener(onItemClickListener);

        updateSongList();
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            currentPosition = position;
            playSong(MEDIA_PATH + "/" + songs.get(position));
        }
    };

    private void playSong(String songPath){
        try {
            mp.reset();
            mp.setDataSource(songPath);
            mp.prepare();
            mp.start();
            playStatus.setText(songPath);
//          한 곡이 끝나면 다음 곡을 재생
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    nextSong();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void nextSong(){
        if (++currentPosition >=songs.size()){
            currentPosition=0;

        } else {
            playSong(MEDIA_PATH + "/" + songs.get(currentPosition));
        }
    }


    private void updateSongList() {
        File home = new File(MEDIA_PATH);
        if(home.listFiles(new Mp3Filter()).length > 0  ){
            for (File file : home.listFiles(new Mp3Filter())) {
                songs.add(file.getName());
            }
            ArrayAdapter<String> songList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songs);
            listView3.setAdapter(songList);
        }
    }

    class Mp3Filter implements FilenameFilter{
        @Override
        public boolean accept(File dir, String filename) {
            return filename.endsWith(".mp3");
        }
    }
}

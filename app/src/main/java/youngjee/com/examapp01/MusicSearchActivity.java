package youngjee.com.examapp01;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import youngjee.com.examapp01.dto.Music;
import youngjee.com.examapp01.utils.GenArtWork;


public class MusicSearchActivity extends ActionBarActivity implements View.OnClickListener {

    private Button btn_ok;
    private Button btn_cancel;
    private ListView lvList;

    private ArrayList<Music> songs = new ArrayList<Music>();
    MusicArrayAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);

        lvList = (ListView) findViewById(R.id.lvList);

        adapter = new MusicArrayAdapter(this,R.layout.rowmusic,songs);
        lvList.setAdapter(adapter);

        btn_ok.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_ok){
            updateSongs();
            adapter.notifyDataSetChanged();
        } else if (v.getId() == R.id.btn_cancel){
            songs.clear();
            adapter.notifyDataSetChanged();
        }
    }

    public void updateSongs(){
        Music[] music = new Music[10];
        int cnt = 0;

        BitmapDrawable mDefaultAlbumIcon = (BitmapDrawable) getResources().getDrawable(R.drawable.git);

        String[] mCursorCols = new String[]{
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ALBUM
        };

        Cursor cur = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, mCursorCols, null, null, null);

        if( cur.moveToFirst() ){
            String title, artist, album;
            Drawable d;

            int titleColumn = cur.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int artistColumn = cur.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int albumColumn = cur.getColumnIndex(MediaStore.Audio.Media.ALBUM);

            while (cur.moveToNext()){
                title = cur.getString(titleColumn);
                artist = cur.getString(artistColumn);
                album = cur.getString(albumColumn);

//                d = (BitmapDrawable) getResources().getDrawable(R.drawable.git);
                d = GenArtWork.getCachedArtwork(this, cnt + 1, mDefaultAlbumIcon);
                Log.d(getClass().getSimpleName(), title + artist + album);


                music[cnt] = new Music(artist, title, album, d);
                songs.add(music[cnt]);
                cnt++;
            }
        }


        }

    private class MusicArrayAdapter extends ArrayAdapter<Music>{
        private ArrayList<Music> items;

        public MusicArrayAdapter(Context context, int textViewResourceId, ArrayList<Music> items){
            super(context, textViewResourceId, items);
            this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v =convertView;
            if (v==null){
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.rowmusic, null);

            }

            Music m = items.get(position);

            if( m != null ){
                ImageView imageview = (ImageView)v.findViewById(R.id.row_album_art);
                TextView tt = (TextView)v.findViewById(R.id.row_artist);
                TextView bt = (TextView)v.findViewById(R.id.row_title);


                tt.setText(m.getGasu() + " - " + m.getJemok());
                bt.setText(m.getAlbumName());
                imageview.setImageDrawable(m.getImage());
            }
            return v;
        }
    }

}

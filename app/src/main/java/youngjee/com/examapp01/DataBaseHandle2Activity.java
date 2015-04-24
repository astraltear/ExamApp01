package youngjee.com.examapp01;

import android.app.Activity;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import youngjee.com.examapp01.database.DataBaseHandler2Helper;

public class DataBaseHandle2Activity extends Activity {

    String ROOT_DIR = "/data/data/youngjee.com.examapp01/databases/";
    String DB_NAME = "kmdc.db";

    DataBaseHandler2Helper mHelper;
    SQLiteDatabase db;
    Cursor cursor;

    SimpleCursorAdapter simpleCursorAdapter1 = null;
    SimpleCursorAdapter simpleCursorAdapter2 = null;
    SimpleCursorAdapter simpleCursorAdapter3 = null;
    SimpleCursorAdapter simpleCursorAdapter4 = null;
    SimpleCursorAdapter simpleCursorAdapter5 = null;

    TabHost tabHost = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setUpDB();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base_handle2);

        tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();

        // Tab1 Setting
        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("tag1").setIndicator("위암").setContent(R.id.stomch_list);
        tabHost.addTab(tabSpec1);

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("tag2").setIndicator("대장암").setContent(R.id.colon_list);
        tabHost.addTab(tabSpec2);

        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("tag3").setIndicator("간암").setContent(R.id.liver_list);
        tabHost.addTab(tabSpec3);

        TabHost.TabSpec tabSpec4 = tabHost.newTabSpec("tag4").setIndicator("유방암").setContent(R.id.breast_list);
        tabHost.addTab(tabSpec4);

        TabHost.TabSpec tabSpec5 = tabHost.newTabSpec("tag5").setIndicator("자궁경부암").setContent(R.id.cervix_list);
        tabHost.addTab(tabSpec5);

        tabHost.setCurrentTab(0);
        tabHost.setOnTabChangedListener(onTabChangeListener);

        mHelper = new DataBaseHandler2Helper(this);
        db = mHelper.getWritableDatabase();

        cursor = db.rawQuery("select * from CanGuide where CanKnd='위암'", null);
        startManagingCursor(cursor);
        ListView stomch_list = (ListView) findViewById(R.id.stomch_list);
        simpleCursorAdapter1 = new SimpleCursorAdapter(stomch_list.getContext(), R.layout.mylist, cursor, new String[]{"_id","DxCodName"}, new int[]{R.id.idx,R.id.text1});
        stomch_list.setAdapter(simpleCursorAdapter1);
        stomch_list.setOnItemClickListener(onItemClickListener);

    }

    TabHost.OnTabChangeListener onTabChangeListener = new TabHost.OnTabChangeListener() {
        @Override
        public void onTabChanged(String tabId) {
            if ("tag1".equals(tabId)){
                cursor = db.rawQuery("select * from CanGuide where CanKnd='위암'", null);
                startManagingCursor(cursor);
                ListView stomch_list = (ListView) findViewById(R.id.stomch_list);
                simpleCursorAdapter1 = new SimpleCursorAdapter(stomch_list.getContext(), R.layout.mylist, cursor, new String[]{"_id","DxCodName"}, new int[]{R.id.idx,R.id.text1});
                stomch_list.setAdapter(simpleCursorAdapter1);
                stomch_list.setOnItemClickListener(onItemClickListener);

            } else if("tag2".equals(tabId)){
                cursor = db.rawQuery("select * from CanGuide where CanKnd='대장암'", null);
                startManagingCursor(cursor);
                ListView colon_list = (ListView) findViewById(R.id.colon_list);
                simpleCursorAdapter2 = new SimpleCursorAdapter(colon_list.getContext(), R.layout.mylist, cursor, new String[]{"_id","DxCodName"}, new int[]{R.id.idx,R.id.text1});
                colon_list.setAdapter(simpleCursorAdapter2);
                colon_list.setOnItemClickListener(onItemClickListener);

            } else if("tag3".equals(tabId)){
                cursor = db.rawQuery("select * from CanGuide where CanKnd='간암'", null);
                startManagingCursor(cursor);
                ListView liver_list = (ListView) findViewById(R.id.liver_list);
                simpleCursorAdapter3 = new SimpleCursorAdapter(liver_list.getContext(), R.layout.mylist, cursor, new String[]{"_id","DxCodName"}, new int[]{R.id.idx,R.id.text1});
                liver_list.setAdapter(simpleCursorAdapter3);
                liver_list.setOnItemClickListener(onItemClickListener);

            } else if("tag4".equals(tabId)){
                cursor = db.rawQuery("select * from CanGuide where CanKnd='유방암'", null);
                startManagingCursor(cursor);
                ListView breast_list = (ListView) findViewById(R.id.breast_list);
                simpleCursorAdapter4 = new SimpleCursorAdapter(breast_list.getContext(), R.layout.mylist, cursor, new String[]{"_id","DxCodName"}, new int[]{R.id.idx,R.id.text1});
                breast_list.setAdapter(simpleCursorAdapter4);
                breast_list.setOnItemClickListener(onItemClickListener);

            } else if("tag5".equals(tabId)){
                cursor = db.rawQuery("select * from CanGuide where CanKnd='자궁암'", null);
                startManagingCursor(cursor);
                ListView cervix_list = (ListView) findViewById(R.id.cervix_list);
                simpleCursorAdapter5 = new SimpleCursorAdapter(cervix_list.getContext(), R.layout.mylist, cursor, new String[]{"_id","DxCodName"}, new int[]{R.id.idx,R.id.text1});
                cervix_list.setAdapter(simpleCursorAdapter5);
                cervix_list.setOnItemClickListener(onItemClickListener);

            }
        }
    };

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String tagName = tabHost.getCurrentTabTag();

            String _id = ((TextView) view.findViewById(R.id.idx)).getText().toString();

            Cursor cursor1 = db.query("CanGuide", new String[]{"RecTxt"}, "_id" + "=" + _id, null, null, null, null,null);
            startManagingCursor(cursor1);
            String note = cursor1.getString(5);
            Log.d(getClass().getSimpleName(), note);
        }
    };

    private void setUpDB() {
        File folder = new File(ROOT_DIR);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        AssetManager assetManager = getResources().getAssets();
        File outfile = new File(ROOT_DIR + DB_NAME);

        InputStream is = null;
        FileOutputStream fo = null;

        long filesize=0;

        try {
            is = assetManager.open(DB_NAME, AssetManager.ACCESS_BUFFER);
            filesize = is.available();

            if (outfile.length() <=0) {
                byte[] tempdata = new byte[(int) filesize];
                is.read(tempdata);
                is.close();

                outfile.createNewFile();

                fo = new FileOutputStream(outfile);
                fo.write(tempdata);
                fo.close();
            }

        } catch (IOException e) {
            Toast.makeText(this,"DB 이동 실패",Toast.LENGTH_SHORT).show();
        }
    }


}

package youngjee.com.examapp01;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;

import youngjee.com.examapp01.adapter.DBCustomAdapter;
import youngjee.com.examapp01.database.DbOpenHelper;
import youngjee.com.examapp01.dto.AddressInfoClass;


public class SqlHandleActivity extends ActionBarActivity {

    private EditText[] mEditTexts ;
    private ListView mListView;
    private DbOpenHelper mDbOpenHelper;
    private Cursor mCursor;
    private ArrayList<AddressInfoClass> infoClassArrayList;
    private DBCustomAdapter dbCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_handle);

        setLayout();

        mDbOpenHelper = new DbOpenHelper(this);
        try {
            mDbOpenHelper.open();
            mDbOpenHelper.insertColumn("김태햐", "01022223333", "aaa@googl.com");
            mDbOpenHelper.insertColumn("송혜고", "010333344444", "bbb@googl.com");
            mDbOpenHelper.insertColumn("낸시량", "01044445555", "ccc@googl.com");
            mDbOpenHelper.insertColumn("제시키", "01066667777", "ddd@googl.com");
            mDbOpenHelper.insertColumn("성유라", "01088889999", "eee@googl.com");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        CursorToArray();

        dbCustomAdapter = new DBCustomAdapter(this, infoClassArrayList);
        mListView.setAdapter(dbCustomAdapter);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                boolean result = mDbOpenHelper.deleteColumn(infoClassArrayList.get(position).get_id());

                if (result) {
                    infoClassArrayList.remove(position);
                    dbCustomAdapter.setArrayList(infoClassArrayList);
                    dbCustomAdapter.notifyDataSetChanged();
                } else {

                }
                return false;
            }
        });

    }

    private void CursorToArray(){
        mCursor = null;
        mCursor = mDbOpenHelper.getAllColumns();
        Log.d("SqlHandleActivity", mCursor.getCount() + "");

        infoClassArrayList = new ArrayList<AddressInfoClass>();

        while (mCursor.moveToNext()) {
            AddressInfoClass aClass = new AddressInfoClass(
                    mCursor.getInt(mCursor.getColumnIndex("_id")),
                    mCursor.getString(mCursor.getColumnIndex("name")),
                    mCursor.getString(mCursor.getColumnIndex("contact")),
                    mCursor.getString(mCursor.getColumnIndex("email"))
            );
            infoClassArrayList.add(aClass);
        }

        mCursor.close();
    }

    public void addUser(View v){
        switch (v.getId()){
            case R.id.btn_add:
                mDbOpenHelper.insertColumn(mEditTexts[0].getText().toString().trim(), mEditTexts[1].getText().toString().trim(), mEditTexts[2].getText().toString().trim());
                infoClassArrayList.clear();
                CursorToArray();
                dbCustomAdapter.setArrayList(infoClassArrayList);
                dbCustomAdapter.notifyDataSetChanged();
                break;
        }
    }

    private void setLayout() {
        mEditTexts = new EditText[]{
                (EditText) findViewById(R.id.et_name),
                (EditText) findViewById(R.id.et_contact),
                (EditText) findViewById(R.id.et_email)
        };

        mListView = (ListView) findViewById(R.id.lv_list);
    }

}

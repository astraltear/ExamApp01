package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import youngjee.com.examapp01.adapter.CustomArrayAdapter;
import youngjee.com.examapp01.dto.InfoClass;


public class ArrayAdapterActivity extends ActionBarActivity {

    private ArrayList<InfoClass> mCareList = null;
    private ListView mListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter);

        setLayout();

        mCareList = new ArrayList<InfoClass>();

        for (int i = 0; i<10; i++) {
            mCareList.add( new InfoClass(i+"번째 ListView 입니다.",getResources().getDrawable(R.drawable.ic_launcher),""+i ));
        }

        // BaseAdapter연결
//        mListView.setAdapter(new CustomBaseAdapter(this,mCareList));
        mListView.setAdapter(new CustomArrayAdapter(this,R.layout.list_row,mCareList));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"ITEM CLICK="+position,Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setLayout() {
        mListView = (ListView) findViewById(R.id.lv_list);
    }


}

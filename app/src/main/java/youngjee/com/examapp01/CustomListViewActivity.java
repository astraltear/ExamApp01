package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import youngjee.com.examapp01.adapter.CustomAdapter;


public class CustomListViewActivity extends ActionBarActivity {

    private CustomAdapter mCustomAdapter = null;
    private ArrayList<String> mArrayList = new ArrayList<String>();

    private ListView mListView = null;
    private CheckBox mAllCheckBox = null;
    private Button mCountBt  =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);

        setLayout();

        mCountBt.setText("current checked count: 0");

        mArrayList.add("one");
        mArrayList.add("two");
        mArrayList.add("three");
        mArrayList.add("four");
        mArrayList.add("five");
        mArrayList.add("six");
        mArrayList.add("seven");
        mArrayList.add("eight");

        mCustomAdapter = new CustomAdapter(CustomListViewActivity.this, mArrayList);
        mListView.setAdapter(mCustomAdapter);
        mListView.setOnItemClickListener(mItemClickListener);

    }

    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(),""+(position+1),Toast.LENGTH_SHORT ).show();
            mCustomAdapter.setChecked(position);
            mCustomAdapter.notifyDataSetChanged();
        }
    };


    private void setLayout() {
        mListView = (ListView) findViewById(R.id.main_list);
        mCountBt = (Button) findViewById(R.id.main_text_button);
        mCountBt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountBt.setText("current checked count:"+mCustomAdapter.getChecked().size());
            }
        });

        mAllCheckBox = (CheckBox) findViewById(R.id.all_check_box);
        mAllCheckBox.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomAdapter.setAllCheckd(mAllCheckBox.isChecked());

                // Adapter에 Data 변화가 생겼을 때 Adapter에 알려준다.
                mCustomAdapter.notifyDataSetChanged();
            }
        });
    }

}

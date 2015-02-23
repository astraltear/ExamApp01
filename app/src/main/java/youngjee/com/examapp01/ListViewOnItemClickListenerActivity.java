package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewOnItemClickListenerActivity extends ActionBarActivity {

    private String[] mContents = {"사자","여우","곰","호랑이","타조","말","돼지","사슴","살쾡이","푸우","퓨마","백호","늑대","여우","물개","고래","새우","고등어"};
    private ListView mListView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_on_item_click_listener);

        Log.d("getApplicationContext:", getApplicationContext().toString());
        Log.d("getBaseContext:", getBaseContext().toString());
        Log.d("this:", this.toString());
        /*
        *   getApplicationContext:(20834): android.app.Application@41d0d300
            getBaseContext:(20834): android.app.ContextImpl@41f86de8
            this:(20834): youngjee.com.examapp01.ListViewOnItemClickListenerActivity@41f86290
        * */

        mListView = (ListView) findViewById(R.id.lv_list);
        mListView.setAdapter(new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,mContents));
        mListView.setOnItemClickListener(mItemClickListener);

    }

    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String tv = (String) parent.getAdapter().getItem(position);
            Toast.makeText(getBaseContext(),tv,Toast.LENGTH_SHORT).show();

            Log.d("Logging:",position+"|"+id);

            TextView tv_view = (TextView) view.findViewById(android.R.id.text1);
            tv_view.setText("바뀜");

        }
    };

}

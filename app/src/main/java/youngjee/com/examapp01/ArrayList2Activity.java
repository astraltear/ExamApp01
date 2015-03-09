package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ArrayList2Activity extends ActionBarActivity implements AdapterView.OnItemClickListener, View.OnClickListener{

    ArrayAdapter<String> mAdapter;

    EditText inputText ;
    ArrayList<String> arrlist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list2);

        mAdapter = new ArrayAdapter<String>(this,R.layout.listview_exam,arrlist);

        ListView list = (ListView) findViewById(R.id.idList);
        list.setAdapter(mAdapter);
        list.setOnItemClickListener(this);

        Button button = (Button) findViewById(R.id.idBtn);
        button.setOnClickListener(this);

        inputText =  (EditText) findViewById(R.id.idEdit);
        inputText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() ==KeyEvent.ACTION_DOWN) {
                    Log.d("ArrayList2Activity", "KEYCODE:"+keyCode);
                    if (keyCode == 66) {
                        arrlist.add(mAdapter.getCount(), inputText.getText().toString());
                        mAdapter.notifyDataSetChanged();
                        inputText.setText("");
                    }
                }
                return false;
            }
        });

    }


    @Override
    public void onClick(View v) {
        int listCount = mAdapter.getCount();

        mAdapter.insert(inputText.getText().toString() , listCount );

        ListView idList = (ListView) findViewById(R.id.idList);
        idList.smoothScrollToPosition(listCount);

        inputText.setText("");

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView selectView = (TextView) view;

        inputText.setText(selectView.getText());
    }
}

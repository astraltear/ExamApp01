package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list2);

        ArrayList<String> arrlist = new ArrayList<String>();
        mAdapter = new ArrayAdapter<String>(this,R.layout.listview_exam,arrlist);

        ListView list = (ListView) findViewById(R.id.idList);
        list.setAdapter(mAdapter);
        list.setOnItemClickListener(this);

        Button button = (Button) findViewById(R.id.idBtn);
        button.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int listCount = mAdapter.getCount();

        EditText editText = (EditText) findViewById(R.id.idEdit);
        mAdapter.insert(editText.getText().toString() , listCount );

        ListView idList = (ListView) findViewById(R.id.idList);
        idList.smoothScrollToPosition(listCount);

        editText.setText("");

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView selectView = (TextView) view;

        EditText editText = (EditText) findViewById(R.id.idEdit);
        editText.setText(selectView.getText());
    }
}

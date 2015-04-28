package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import youngjee.com.examapp01.adapter.HumanFilterAdapter;
import youngjee.com.examapp01.dto.Human;


public class AdapterFilterActivity extends ActionBarActivity {

    private ListView listView;
    private HumanFilterAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_filter);

        initLayout();
        initApp();
    }

    private void initApp() {
        mAdapter = new HumanFilterAdapter(AdapterFilterActivity.this);

        Human item;


        item = new Human();
        item.sex = HumanFilterAdapter.FEMALE;
        item.name= "Jessy";
        mAdapter.insertHuman(item);

        item = new Human();
        item.sex = HumanFilterAdapter.MALE;
        item.name= "John";
        mAdapter.insertHuman(item);

        item = new Human();
        item.sex = HumanFilterAdapter.FEMALE;
        item.name= "Fukuyu";
        mAdapter.insertHuman(item);

        item = new Human();
        item.sex = HumanFilterAdapter.MALE;
        item.name= "Jason";
        mAdapter.insertHuman(item);

        item = new Human();
        item.sex = HumanFilterAdapter.FEMALE;
        item.name= "Fukume";
        mAdapter.insertHuman(item);

        listView.setAdapter(mAdapter);
    }

    private void initLayout() {
        findViewById(R.id.btn_all).setOnClickListener(mTypeClick);
        findViewById(R.id.btn_male).setOnClickListener(mTypeClick);
        findViewById(R.id.btn_female).setOnClickListener(mTypeClick);

        listView = (ListView) findViewById(R.id.lv_type);
    }

    View.OnClickListener mTypeClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_all:
                    mAdapter.setType(HumanFilterAdapter.ALL);
                    break;
                case R.id.btn_male:
                    mAdapter.setType(HumanFilterAdapter.MALE);
                    break;
                case R.id.btn_female:
                    mAdapter.setType(HumanFilterAdapter.FEMALE);
                    break;
            }
            mAdapter.notifyDataSetChanged();
        }
    };

}

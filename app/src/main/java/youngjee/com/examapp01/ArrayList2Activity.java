package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;


public class ArrayList2Activity extends ActionBarActivity {

    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list2);

        ArrayList<String> arrlist = new ArrayList<String>();
        mAdapter = new ArrayAdapter<String>(this,R.l)

    }


}

package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static android.widget.Toast.*;


public class ArrayListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list);

        String [] mItemStr = new String[]{"first","second","third","fourth"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mItemStr);

        ListView listView  = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_array_list,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.id_menu_hi:
                makeText(this,"HI", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_menu_hello:
                makeText(this,"HELLO", LENGTH_SHORT).show();
                break;
            case R.id.id_menu_good:
                makeText(this,"GOOD", LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}

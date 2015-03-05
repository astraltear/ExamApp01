package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

        Button btn_left = (Button) findViewById(R.id.btn_left);

        registerForContextMenu(btn_left);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
       if (v.getId() == R.id.listView){
           MenuInflater inflater = getMenuInflater();
           inflater.inflate(R.menu.menu_array_list, menu);
       } else {
           menu.setHeaderIcon(android.R.drawable.btn_star);
           menu.setHeaderTitle("공지사항");
           menu.add(123, 0, Menu.NONE, "APPLE");
           menu.add(123, 1, Menu.NONE, "PEACH");
           menu.add(123,2,Menu.NONE,"BANANA");

           SubMenu subMenu = menu.addSubMenu("하우스과일>> 서버메뉴 존재");
           subMenu.setHeaderTitle("서브메뉴 타이틀");
           subMenu.add(456, 0, Menu.NONE, "방울토마토");
           subMenu.add(456, 1, Menu.NONE, "딸기");
           subMenu.add(456, 2, Menu.NONE, "호박");

       }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

           Log.d("item.getItemId()",item.getItemId()+"||"+item.getGroupId());
        if (item.getGroupId() == 123){

            Log.d("onContextItemSelected", "info.position[" + item.getTitle() + "]");
        } else if(item.getGroupId() == 456){

        } else {
            switch (item.getItemId()) {
                case R.id.id_menu_hi:
                    makeText(this, "HI", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.id_menu_hello:
                    makeText(this, "HELLO", LENGTH_SHORT).show();
                    break;
                case R.id.id_menu_good:
                    makeText(this, "GOOD", LENGTH_SHORT).show();
                    break;
            }
        }

        return super.onContextItemSelected(item);
    }
}

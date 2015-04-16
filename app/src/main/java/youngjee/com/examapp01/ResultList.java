package youngjee.com.examapp01;


import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.sql.SQLException;

public class ResultList extends ListActivity {
    DataBaseDBHandle dbHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            dbHandle = DataBaseDBHandle.open(this);
            String command = (String) getIntent().getExtras().get("command");

            Cursor cursor = null;

            if(command.equals("selectall")) {
                cursor = dbHandle.selectAll();

            } else if(command.equals("selectname")) {
                String name = (String) getIntent().getExtras().get("name");
                cursor = dbHandle.selectName(name);
            }

            String[] arr = new String[cursor.getCount()];
            int count =0;

            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                arr[count] = id + ":" + name;
                count++;
            }

            cursor.close();
            dbHandle.close();

            ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
            setListAdapter(adapter);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import youngjee.com.examapp01.adapter.UserAdapter;
import youngjee.com.examapp01.dto.User;


public class UserAdapterActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_adapter);

        ArrayList<User> arrUsers = new ArrayList<User>();

        UserAdapter userAdapter = new UserAdapter(this,arrUsers);

        ListView listView = (ListView) findViewById(R.id.lvitems);
        listView.setAdapter(userAdapter);

        User user = new User("A","SEOUL");
        userAdapter.add(user);

        user = new User("B","SEOUL");
        userAdapter.add(user);

        user = new User("B","SEOUL");
        userAdapter.add(user);

        user = new User("B","SEOUL");
        userAdapter.add(user);


    }

}

package youngjee.com.examapp01;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class UILayoutActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uilayout);
        Log.d("UILayoutActivity", "Build.VERSION.SDK_INT[" + Build.VERSION.SDK_INT + "]Build.VERSION_CODES.HONEYCOMB[" + Build.VERSION_CODES.HONEYCOMB + "]");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_uilayout,menu);
        return super.onCreateOptionsMenu(menu);
    }
}

package youngjee.com.examapp01;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;


public class UILayoutActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uilayout);
        Log.d("UILayoutActivity", "Build.VERSION.SDK_INT[" + Build.VERSION.SDK_INT + "]Build.VERSION_CODES.HONEYCOMB[" + Build.VERSION_CODES.HONEYCOMB + "]");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*
        if your minSdkVersion is 11 or higher, instead use :
        getActionBar().setDisplayHomeAsUpEnabled(true);
        * */

        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_uilayout,menu);
        return super.onCreateOptionsMenu(menu);
    }
}

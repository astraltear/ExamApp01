package youngjee.com.examapp01;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SharedPreferencesActivity extends ActionBarActivity  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        Button btn_save = (Button) findViewById(R.id.btn_save);
        Button btn_restore = (Button) findViewById(R.id.btn_restore);
        Button btn_delete = (Button) findViewById(R.id.btn_delete);
        Button btn_deleteALL = (Button) findViewById(R.id.btn_deleteALL);

        btn_save.setOnClickListener(this);
        btn_restore.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_deleteALL.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if ( v.getId() ==R.id.btn_save ){
            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("SPKEY", "save preferences");
            editor.commit();
        } else  if ( v.getId() ==R.id.btn_restore ){
            SharedPreferences sharedPref =  getPreferences(Context.MODE_PRIVATE);

            TextView tv_sharedInfo = (TextView) findViewById(R.id.sharedInfo);
            tv_sharedInfo.setText( sharedPref.getString("SPKEY","default preferences") );

        } else  if ( v.getId() ==R.id.btn_delete ) {
            SharedPreferences sharedPref =  getPreferences(Context.MODE_PRIVATE);
//            SharedPreferences sharedPref = getSharedPreferences("pref", MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.remove("SPKEY");
            editor.commit();
        } else  if ( v.getId() ==R.id.btn_deleteALL ) {
            SharedPreferences sharedPref =  getPreferences(Context.MODE_PRIVATE);
//            SharedPreferences sharedPref = getSharedPreferences("pref", MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.clear();
            editor.commit();
        }

    }
}

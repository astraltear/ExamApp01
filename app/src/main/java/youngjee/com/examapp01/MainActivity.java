package youngjee.com.examapp01;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_ArrAdapter = (Button) findViewById(R.id.btn_ArrAdapter);
        btn_ArrAdapter.setOnClickListener(this);

        Button btn_UserAdapter = (Button) findViewById(R.id.btn_UserAdapter);
        btn_UserAdapter.setOnClickListener(this);

        Button btn_ArrAdapter2 = (Button) findViewById(R.id.btn_ArrAdapter2);
        btn_ArrAdapter2.setOnClickListener(this);

        Button btnAssetImageRead = (Button) findViewById(R.id.btnAssetImageRead);
        btnAssetImageRead.setOnClickListener(this);

        Button btnMemoryCheck = (Button) findViewById(R.id.btnMemoryCheck);
        btnMemoryCheck.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v.getId() == R.id.btn_ArrAdapter){
            intent = new Intent(this,ArrayListActivity.class);
            startActivity(intent);
        } else  if (v.getId() ==R.id.btn_UserAdapter) {
            intent = new Intent(this, UserAdapterActivity.class);
            startActivity(intent);
        } else   if (v.getId() ==R.id.btn_ArrAdapter2) {
            intent = new Intent(this,ArrayList2Activity.class);
            startActivity(intent);
        } else   if (v.getId() ==R.id.btnAssetImageRead) {
            intent = new Intent(this,AssertImageReadActivity.class);
            startActivity(intent);
        } else   if (v.getId() ==R.id.btnMemoryCheck) {
            intent = new Intent(this,MemoryCheckActivity.class);
            startActivity(intent);


        }
    }
}

package youngjee.com.examapp01;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class DataSenderActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_sender);

        Button button = (Button) findViewById(R.id.btn_IntentSender);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_IntentSender){
            Intent intent = new Intent(this,DataReceiverActivity.class);
            intent.putExtra("NO",123);
            intent.putExtra("PW","testpw");
            startActivityForResult(intent,934);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("EXAM",requestCode+"|"+resultCode);

        if (resultCode == RESULT_OK && requestCode == 934){
            data.getIntExtra("AGE", 25);
            data.getStringExtra("ADRESS");

            Log.d("DataSenderActivity", data.getIntExtra("AGE", 1)+"|"+data.getStringExtra("ADRESS"));
        }
    }
}

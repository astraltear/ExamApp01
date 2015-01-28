package youngjee.com.examapp01;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DataReceiverActivity extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_receiver);

        TextView textView = (TextView) findViewById(R.id.tv_recInfo);
        Button button = (Button) findViewById(R.id.btn_Back);
        button.setOnClickListener(this);

        Intent intent = getIntent();

        int no =  intent.getIntExtra("NO",1);
        String pw = intent.getStringExtra("PW");
        String etc = intent.getStringExtra("etc");

        textView.setText("no["+no+"]pw["+pw+"]etc["+etc+"]");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("AGE",25);
        intent.putExtra("ADRESS","SEOUL");
        setResult(RESULT_OK,intent);
        finish();

    }
}

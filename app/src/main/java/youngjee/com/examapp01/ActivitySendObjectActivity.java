package youngjee.com.examapp01;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ActivitySendObjectActivity extends ActionBarActivity {

    private EditText editText1, editText2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_send_object);

        editText1 = (EditText) findViewById(R.id.ed01);
        editText2 = (EditText) findViewById(R.id.ed02);

        button = (Button) findViewById(R.id.btn_send);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityTextObject activityTextObject = new ActivityTextObject();
                activityTextObject.setText1(editText1.getText().toString());
                activityTextObject.setText2(editText2.getText().toString());

                Intent intent = new Intent(ActivitySendObjectActivity.this, ActivityReceiveObjectActivity.class);
                intent.putExtra("textObject", activityTextObject);
                startActivity(intent);
            }
        });
    }

}

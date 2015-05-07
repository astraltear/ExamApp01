package youngjee.com.examapp01;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ActivityReceiveObjectActivity extends ActionBarActivity {

    private ActivityTextObject activityTextObject;
    private TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_receive_object);

        textView1 = (TextView) findViewById(R.id.ed01);
        textView2 = (TextView) findViewById(R.id.ed02);

        Intent intent = getIntent();
        activityTextObject = (ActivityTextObject) intent.getSerializableExtra("textObject");

        textView1.setText(activityTextObject.getText1());
        textView2.setText(activityTextObject.getText2());


    }
}

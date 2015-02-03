package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class TouchEventExamActivity extends ActionBarActivity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event_exam);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        TextView textView = (TextView) findViewById(R.id.tv);

        if (v.getId() == R.id.imageView){
            if (event.getAction() == MotionEvent.ACTION_DOWN){
                textView.setText("Action DOWN!!!!!");
                return false;
            } else if (event.getAction() == MotionEvent.ACTION_MOVE){
                textView.setText("Action ACTION_MOVE!!!!!");
                return false;
            } else if (event.getAction() == MotionEvent.ACTION_SCROLL){
                textView.setText("Action ACTION_SCROLL!!!!!");
                return false;
            } else if (event.getAction() == MotionEvent.ACTION_UP){
                textView.setText("Action ACTION_UP!!!!!");
                return false;
            }
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater  = getMenuInflater();
        inflater.inflate(R.menu.menu_touch_event_exam,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_1:
                Toast.makeText(this,"menu1",Toast.LENGTH_SHORT).show();
        }

        return true;

    }
}

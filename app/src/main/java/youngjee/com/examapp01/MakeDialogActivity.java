package youngjee.com.examapp01;

import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MakeDialogActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_dialog);

        Button button = (Button) findViewById(R.id.btn_dialog);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          AlertDialog.Builder builder = new AlertDialog.Builder(MakeDialogActivity.this);
                                          builder.setTitle("단순한");
                                          builder.setMessage("very simple");
                                          builder.setIcon(R.drawable.ic_launcher);

                                          builder.setPositiveButton("ONe", null);
                                          builder.setNeutralButton("Two", null);
                                          builder.setNegativeButton("Three", null);

                                          builder.show();
                                      }
                                  }

        );
    }


}

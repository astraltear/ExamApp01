package youngjee.com.examapp01;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MemoryCheckActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_check);

        TextView textView1 = (TextView) findViewById(R.id.tvPathInfo1);
        TextView textView2 = (TextView) findViewById(R.id.tvPathInfo2);

        textView1.setText( getApplicationContext().toString() );
        textView2.setText( getApplicationContext().getFilesDir().getPath() );

        TextView tvMemoryInfo = (TextView) findViewById(R.id.tvMemoryInfo);

        int exceedMemory = ((ActivityManager)this.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        int largeMemory =  ((ActivityManager)this.getSystemService(Context.ACTIVITY_SERVICE)).getLargeMemoryClass();

        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();

        String str = "exceedMemory["+exceedMemory+"]largeMemory["+largeMemory+"]totalMemory["+totalMemory+"]freeMemory["+freeMemory+"]";

        tvMemoryInfo.setText(str);

    }

}

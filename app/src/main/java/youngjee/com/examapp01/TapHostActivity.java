package youngjee.com.examapp01;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class TapHostActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_host);
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();

        // Tab1 Setting
        TabSpec tabSpec1 = tabHost.newTabSpec("Tab1").setIndicator("신문, 뉴스").setContent(R.id.tv01);
        tabHost.addTab(tabSpec1);

        // Tab2 Setting
        TabSpec tabSpec2 = tabHost.newTabSpec("Tab2");
        tabSpec2.setIndicator("포털, IT"); // Tab Subject
        tabSpec2.setContent(R.id.tv02); // Tab Content
        tabHost.addTab(tabSpec2);

        // Tab3 Setting
        TabSpec tabSpec3 = tabHost.newTabSpec("Tab3");
        tabSpec3.setIndicator("방송, 해외"); // Tab Subject
        tabSpec3.setContent(R.id.tv03); // Tab Content
        tabHost.addTab(tabSpec3);

        // show First Tab Content
        tabHost.setCurrentTab(2);
    }

}

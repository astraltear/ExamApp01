package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;


public class TapHostActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_host);
        TabHost tabHost = (TabHost)findViewById(R.id.tab_host);
        tabHost.setup();

        // Tab1 Setting
        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("Tab1");
        tabSpec1.setIndicator("신문, 뉴스"); // Tab Subject
        tabSpec1.setContent(R.id.tab_view1); // Tab Content
        tabHost.addTab(tabSpec1);

        // Tab2 Setting
        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("Tab2");
        tabSpec2.setIndicator("포털, IT"); // Tab Subject
        tabSpec2.setContent(R.id.tab_view2); // Tab Content
        tabHost.addTab(tabSpec2);

        // Tab3 Setting
        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("Tab3");
        tabSpec3.setIndicator("방송, 해외"); // Tab Subject
        tabSpec3.setContent(R.id.tab_view3); // Tab Content
        tabHost.addTab(tabSpec3);

        // show First Tab Content
        tabHost.setCurrentTab(0);
    }

}

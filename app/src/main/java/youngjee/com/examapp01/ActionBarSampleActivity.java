package youngjee.com.examapp01;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;



public class ActionBarSampleActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);
        TextView textView = (TextView) findViewById(R.id.tvInfo);
        String str="ActionBar를 사용하기 위해서는 Support LIbrary가 추가되어야 한다.";
        str+="menu.xml 파일에 xmlns:ExamApp01='http://schemas.android.com/apk/res-auto' 를 추가하고  ExamApp01:showAsAction='always'을 추가한다.";

        textView.setText(str);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_action_bar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String text="";

        switch (id){
            case R.id.action_item_add:
                text="item add";
                break;
            case R.id.action_item_search:
                text="item search";
                break;
            case R.id.abc_ic_menu_cut_mtrl_alpha:
                text="item cut";
                break;
            case R.id.abc_ic_menu_paste_mtrl_am_alpha:
                text="item past";
                break;
            case R.id.action_item_normal:
                text="item normal";
                break;
        }

        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }
}

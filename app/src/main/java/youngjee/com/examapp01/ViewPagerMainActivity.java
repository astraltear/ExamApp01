package youngjee.com.examapp01;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ViewPagerMainActivity extends ActionBarActivity implements View.OnClickListener {

    private ViewPager mPager;

    private Button btn_one;
    private Button btn_two;
    private Button btn_three;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_main);

        setLayout();

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new PagerAda);
    }

    private void setLayout() {
        btn_one = (Button) findViewById(R.id.btn_one);
        btn_two = (Button) findViewById(R.id.btn_two);
        btn_three = (Button) findViewById(R.id.btn_three);

        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

}

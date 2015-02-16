package youngjee.com.examapp01;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import youngjee.com.examapp01.adapter.PagerAdapterClass;


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
        mPager.setAdapter(new PagerAdapterClass(getApplicationContext()));
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
        switch (v.getId()){
            case R.id.btn_one:
                setCurrentInflateItem(0);
                break;
            case R.id.btn_two:
                setCurrentInflateItem(1);
                break;
            case R.id.btn_three:
                setCurrentInflateItem(2);
                break;
        }

    }

    private void setCurrentInflateItem(int type) {
        if (type == 0){
            mPager.setCurrentItem(0);
            Log.d("ViewPagerMainActivity",""+type );
        } else if (type == 1){
            mPager.setCurrentItem(1);
            Log.d("ViewPagerMainActivity",""+type );
        } else {
            mPager.setCurrentItem(2);
            Log.d("ViewPagerMainActivity",""+type );
        }
    }

}

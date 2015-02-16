package youngjee.com.examapp01.adapter;


import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import youngjee.com.examapp01.R;

public class PagerAdapterClass extends PagerAdapter implements View.OnClickListener {

    private LayoutInflater mInflater;
    private Context parentContext = null;

    public PagerAdapterClass(Context context) {
        super();
        mInflater = LayoutInflater.from(context);
        parentContext = context;
    }

    @Override
    /*
    * 현재 PageAdapter에서 관리할 갯수를 반환한다.
    * */
    public int getCount() {
        return 3;
    }

    @Override
    /*
    * View 객체를 삭제한다.
    * */
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((View)object);
    }

    @Override
    /*
    * instantiateItem메소드에서 생성한 객체를 이용할 것인지 여부를 반환한다.
    * */
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    /*
    * ViewPager에서 사용할 뷰객체 생성 및 등록한다.
    * */
    public Object instantiateItem(ViewGroup container, int position) {
        View v = null;
        Log.d("PagerAdapterClass", "position:"+position);
        if (position == 0) {
            v = mInflater.inflate(R.layout.inflate_one, null);
            v.findViewById(R.id.iv_one);
            v.findViewById(R.id.btn_click).setOnClickListener(this);

        } else if(position== 1){
            v = mInflater.inflate(R.layout.inflate_two, null);
            v.findViewById(R.id.iv_two);
            v.findViewById(R.id.btn_click_2).setOnClickListener(this);

        } else {
            v = mInflater.inflate(R.layout.inflate_three, null);
            v.findViewById(R.id.iv_three);
            v.findViewById(R.id.btn_click_3).setOnClickListener(this);

        }

        ((ViewPager)container).addView(v,0);

        return  v;
    }

    /* saveState() 상태에서 저장했던 Adapter와 page를 복구한다. */
    @Override public void restoreState(Parcelable state, ClassLoader loader) {}
    /* 현재 UI 상태를 저장하기 위해 Adapter와  Page 관련 인스턴스 상태를 저장한다. */
    @Override public Parcelable saveState() { return null;}
    /* 페이지 변경이 시작될 때 호출됩니다. */
    @Override public void startUpdate(ViewGroup container) {}
    /*  페이지 변경이 완료되었을 때 호출됩니다. */
    @Override public void finishUpdate(ViewGroup container) {}

    @Override
    public void onClick(View v) {
        String text = ((Button)v).getText().toString();
        Toast.makeText(parentContext, text, Toast.LENGTH_LONG).show();

    }
}

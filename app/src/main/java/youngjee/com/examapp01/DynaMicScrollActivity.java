package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class DynaMicScrollActivity extends ActionBarActivity implements AbsListView.OnScrollListener {
    private final int INSERT_COUNT=10;
    private ArrayAdapter<String> m_adapter = null;
    private ListView m_list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dyna_mic_scroll);
        String str;
        ArrayList<String> strList = new ArrayList<String>();
        m_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,strList);

        for (int i =0; i < INSERT_COUNT ; i++){
            str="리스트뷰 항목-"+(i);
            m_adapter.insert(str,0);
        }
        m_list = (ListView) findViewById(R.id.listView2);
        m_list.setAdapter(m_adapter);
        m_list.setOnScrollListener(this);
        m_list.setSelection(m_list.getCount()-1);

    }

    /*
    onScroll 메소드는 호출 빈도가 매우 높기 때문에 스크롤이 보이지 않는다면 항목이 동적으로 추가되고 있다는 것을 느끼지 못할 정도로 매끄럽게 처리된다.
    단 부하가 증가한다.
    아래의 메소드 구현부를 서로 주석을 풀었다가 묶었다가 테스트를 한다.
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        /*
        if (view.isShown()){
            if (firstVisibleItem ==0) {
                Log.d("DynaMicScrollActivity", "firstVisibleItem[" + firstVisibleItem + "]visibleItemCount[" + visibleItemCount + "]totalItemCount[" + totalItemCount + "]");

                String str;
                for (int i =0 ; i<INSERT_COUNT;i++){
                    str="리스트뷰 항목-"+(i);
                    m_adapter.insert(str,0);
                    view.setSelection(INSERT_COUNT+1);
                }
            }
        }
        */
    }

    /*
    메서드 호출빈도가 상대적으로 낮기 때문에 어플이 가볍게 동작하지만 매끄럽지 못하게 처리될 수 있다.
    onScroll 메소드에 비해 호출빈도가 적고 사용가능한 상태값중 SCROLL_STATE_IDLE 에서만 항목 추가가 발생하므로 스크롤시 항목이 추가될 때마다 끊김 현상이 나타난다.
    */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (view.isShown()){
            if (scrollState == SCROLL_STATE_IDLE){
                if (view.getFirstVisiblePosition() ==0){
                    String str;
                    for (int i =0 ; i<INSERT_COUNT;i++){
                        str="리스트뷰 항목-"+(i);
                        m_adapter.insert(str,0);
                        view.setSelection(INSERT_COUNT+1);
                    }
                    view.setSelection(INSERT_COUNT);
                }
            }
        }
    }


}

package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;

import youngjee.com.examapp01.adapter.BaseExpandableAdapter;


public class ExpandableListViewActivity extends ActionBarActivity {

    private ArrayList<String> mGroupList = null;
    private ArrayList<ArrayList<String >> mChildList = null;
    private ArrayList<String> mChildListContent = null;
    private ExpandableListView expandableListView = null;
    private BaseExpandableAdapter baseExpandableAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);

        setLayout();

        mGroupList = new ArrayList<String>();
        mChildList = new ArrayList<ArrayList<String>>();
        mChildListContent = new ArrayList<String>();

        mGroupList.add("가위");
        mGroupList.add("바위");
        mGroupList.add("보");

        mChildListContent.add("1");
        mChildListContent.add("2");
        mChildListContent.add("3");

        mChildList.add(mChildListContent);
        mChildList.add(mChildListContent);
        mChildList.add(mChildListContent);

        baseExpandableAdapter = new BaseExpandableAdapter(this, mGroupList, mChildList);

        expandableListView.setAdapter(baseExpandableAdapter);

        // 그룹 indicator삭제
        expandableListView.setGroupIndicator(null);

        // 그룹 갯수 얻기
        final int groupCount = baseExpandableAdapter.getGroupCount();

        // 처음 시작시 그룹 모두 열기
        for (int i =0 ; i <groupCount; i++) {
            expandableListView.expandGroup(i);
            // 처음 시작시 그룹 모두 닫기
            //expandableListView.collapseGroup(i);
        }




        // 그룹 클릭 했을 경우 이벤트
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,int groupPosition, long id) {
                Toast.makeText(getApplicationContext(), "g click = " + groupPosition,Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // 차일드 클릭 했을 경우 이벤트
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(), "c click = " + childPosition, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // 그룹이 닫힐 경우 이벤트
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(), "g Collapse = " + groupPosition, Toast.LENGTH_SHORT).show();
            }
        });

        // 그룹이 열릴 경우 이벤트
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), "g Expand = " + groupPosition, Toast.LENGTH_SHORT).show();

                for (int i =0 ; i <groupCount; i++){
                    if(!(i==groupPosition)) {
                        expandableListView.collapseGroup(i);
                    }
                }

            }
        });
    }

    private void setLayout() {
        expandableListView = (ExpandableListView) findViewById(R.id.elv_list);
    }
}

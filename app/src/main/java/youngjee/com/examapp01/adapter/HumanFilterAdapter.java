package youngjee.com.examapp01.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import youngjee.com.examapp01.dto.Human;

public class HumanFilterAdapter extends BaseAdapter {

    public final static int ALL=0;
    public final static int FEMALE = 1;
    public final static int MALE = 2;

    private int type;

    private List<Human> mList;
    private Context context;

    public HumanFilterAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<Human>();
    }

    public void insertHuman(Human human){
        mList.add(human);
    }

    public void remove(int position) {
        mList.remove(position);
    }

    @Override
    public int getCount() {
        int size=0;

        int fullSize = mList.size();
        switch (type){
            case  ALL:
            default:
                size = fullSize;
                break;
            case FEMALE:
            case MALE:
                Human temp;

                for (int i=0; i<fullSize;i++) {
                    temp = mList.get(i);
                    if (temp.sex == type) {
                        size++;
                    }
                }
                break;
        }

        return size;
    }

    @Override
    public Human getItem(int position) {
        Human human;

        int itemIndex=0;
        int fullSize = mList.size();

        switch (type){
            case ALL:
                return mList.get(position);
        }

        for (int i=0; i<fullSize;i++) {
            human = mList.get(i);
            if (human.sex == type){
                if (position == itemIndex) {
                    return human;
                }
                itemIndex++;
            }
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv;

        if (convertView == null) {
            tv = new TextView(context);

            tv.setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, ListView.LayoutParams.WRAP_CONTENT));
            tv.setTextSize(20);
            convertView = tv;
        } else {
            tv = (TextView) convertView;
        }

        Human item = getItem(position);

        if(item != null){
            tv.setText(item.name);
        }
        return convertView;
    }

    public void setType(int type) {
        this.type = type;
    }
}

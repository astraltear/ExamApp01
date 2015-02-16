package youngjee.com.examapp01.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import java.util.ArrayList;

import youngjee.com.examapp01.R;

public class CustomAdapter extends BaseAdapter {

    private ViewHolder viewHolder = null;
    private LayoutInflater inflater = null;
    private ArrayList<String> sArrayList = new ArrayList<String>();
    private boolean [] isCheckedConfirm;

    public CustomAdapter(Context c,ArrayList<String> mList) {
        inflater = LayoutInflater.from(c);
        this.sArrayList = mList;
        this.isCheckedConfirm = new boolean[sArrayList.size()];
    }

    public void setAllCheckd(boolean ischecked){
        int tempSize = isCheckedConfirm.length;
        for(int a=0 ; a < tempSize; a++) {
            isCheckedConfirm[a] = ischecked;
        }
    }

    public void setChecked(int position) {
        isCheckedConfirm[position] = !isCheckedConfirm[position];
    }

    public ArrayList<Integer> getChecked(){
        int tempSize = isCheckedConfirm.length;
        ArrayList<Integer> mArrayList = new ArrayList<Integer>();

        for (int b =0 ; b<tempSize; b++){
            if(isCheckedConfirm[b]) {
                mArrayList.add(b);
            }
        }

        return mArrayList;
    }

    @Override
    public int getCount() {
        return sArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null){
            viewHolder = new ViewHolder();
            v = inflater.inflate(R.layout.row, null);
            viewHolder.cbox = (CheckBox) v.findViewById(R.id.main_check_box);
            v.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) v.getTag();
        }
            // CheckBox는 기본적으로 이벤트를 가지고 있기 때문에 ListView의 아이템
            // 클릭리스너를 사용하기 위해 CheckBox의 이벤트를 없애줘야 한다.
            viewHolder.cbox.setClickable(false);
            viewHolder.cbox.setFocusable(false);

            viewHolder.cbox.setText(sArrayList.get(position));
            viewHolder.cbox.setChecked(isCheckedConfirm[position]);

            return v;
    }
}

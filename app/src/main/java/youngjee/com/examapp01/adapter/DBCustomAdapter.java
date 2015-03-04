package youngjee.com.examapp01.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import youngjee.com.examapp01.R;
import youngjee.com.examapp01.dto.AddressInfoClass;

public class DBCustomAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<AddressInfoClass> infoClassArrayList;
    private ViewHolder viewHolder;

    public DBCustomAdapter(Context c , ArrayList<AddressInfoClass> arrayList) {
        inflater = LayoutInflater.from(c);
        infoClassArrayList = arrayList;
    }

    @Override
    public int getCount() {
        return infoClassArrayList.size();
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

        if (v == null ) {
            viewHolder = new ViewHolder();
            v = inflater.inflate(R.layout.sql_list_row, null);
            viewHolder.name = (TextView) v.findViewById(R.id.tv_name);
            viewHolder.contact = (TextView) v.findViewById(R.id.tv_contact);
            viewHolder.email = (TextView) v.findViewById(R.id.tv_email);

            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.name.setText(infoClassArrayList.get(position).name);
        viewHolder.contact.setText(infoClassArrayList.get(position).contact);
        viewHolder.email.setText(infoClassArrayList.get(position).email);

        return v;
    }

    public void setArrayList(ArrayList<AddressInfoClass> arrayList) {
        this.infoClassArrayList = arrayList;
    }

    public ArrayList<AddressInfoClass> getArrayList() {
        return infoClassArrayList;
    }

    class ViewHolder{
        TextView name;
        TextView contact;
        TextView email;
    }
}

package youngjee.com.examapp01.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import youngjee.com.examapp01.R;

public class RecyclerListAdapter extends BaseAdapter {

    private final Context mContext;

    public RecyclerListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecyclerViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.recycler_listitem, parent, false);

            holder = new RecyclerViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);

            convertView.setTag(holder);

        } else {
            holder = (RecyclerViewHolder) convertView.getTag();
        }

        holder.title.setText("item:" + position);
        return convertView;
    }
}

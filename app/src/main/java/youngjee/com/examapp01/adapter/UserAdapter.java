package youngjee.com.examapp01.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import youngjee.com.examapp01.R;
import youngjee.com.examapp01.dto.User;

public class UserAdapter extends ArrayAdapter<User> {

    public UserAdapter(Context context,  ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        User user = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user,parent,false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);

        tvName.setText(user.name);
        tvHome.setText(user.hometown);

        return convertView;
    }
}

package youngjee.com.examapp01.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import youngjee.com.examapp01.R;
import youngjee.com.examapp01.dto.InfoClass;


public class CustomArrayAdapter extends ArrayAdapter<InfoClass> {

    private ViewHolder2 viewHolder2 = null;
    private LayoutInflater inflater = null;
    private ArrayList<InfoClass> infoList = null;
    private Context mContext = null;

    public CustomArrayAdapter(Context c, int textResourceId, ArrayList<InfoClass> arrays) {
        super(c,textResourceId,arrays);
        this.inflater = LayoutInflater.from(c);
        this.mContext = c;

    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public InfoClass getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null){
            viewHolder2 = new ViewHolder2();
            v = inflater.inflate(R.layout.list_row, null);
            viewHolder2.tv_title = (android.widget.TextView) v.findViewById(R.id.tv_title);
            viewHolder2.iv_image = (android.widget.ImageView) v.findViewById(R.id.iv_image);
            viewHolder2.btn_button = (android.widget.Button) v.findViewById(R.id.btn_button);
            viewHolder2.cb_box = (android.widget.CheckBox) v.findViewById(R.id.cb_box);

            v.setTag(viewHolder2);
        } else {
            viewHolder2 = (ViewHolder2) v.getTag();
        }

        viewHolder2.tv_title.setText(getItem(position).title);
        viewHolder2.iv_image.setOnClickListener(buttonClickListener);
        viewHolder2.iv_image.setTag(position);

        viewHolder2.btn_button.setText(getItem(position).button);
        viewHolder2.btn_button.setOnClickListener(buttonClickListener);
        viewHolder2.btn_button.setTag(position);

        viewHolder2.cb_box.setTag(position);
        viewHolder2.cb_box.setOnClickListener(buttonClickListener);

        return v;
    }

    private  View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_image:
                    Toast.makeText(mContext, "이미지 tag=" + v.getTag(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_button:
                    Toast.makeText(mContext,"버튼 tag="+v.getTag(),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.cb_box:
                    Toast.makeText(mContext,"체크박스 tag="+v.getTag(),Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void finalize() throws Throwable {
        free();
        super.finalize();
    }

    private void free() {
        inflater = null;
        infoList = null;
        viewHolder2 = null;
        mContext = null;
    }

}

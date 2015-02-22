package youngjee.com.examapp01.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import youngjee.com.examapp01.R;
import youngjee.com.examapp01.dto.InfoClass;


public class CustomBaseAdapter extends BaseAdapter {

    private LayoutInflater inflater = null;
    private ArrayList<InfoClass> infoList = null;
    private ViewHolder2 viewHolder2 = null;
    private Context mContext = null;

    public CustomBaseAdapter(Context c, ArrayList<InfoClass> mCareList) {
        this.mContext = c;
        this.inflater = LayoutInflater.from(c);
        this.infoList = mCareList;
    }


    @Override
    public int getCount() {
        return infoList.size();
    }

    @Override
    public InfoClass getItem(int position) {
        return infoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            viewHolder2 = new ViewHolder2();
            v = inflater.inflate(R.layout.list_row, null);
            viewHolder2.tv_title = (TextView) v.findViewById(R.id.tv_title);
            viewHolder2.iv_image = (ImageView) v.findViewById(R.id.iv_image);
            viewHolder2.btn_button = (Button) v.findViewById(R.id.btn_button);
            viewHolder2.cb_box = (CheckBox) v.findViewById(R.id.cb_box);

            v.setTag(viewHolder2);

        } else {
            viewHolder2 = (ViewHolder2) v.getTag();
        }

        viewHolder2.tv_title.setText(getItem(position).title);

        viewHolder2.iv_image.setTag(position);
        viewHolder2.iv_image.setOnClickListener(buttonClickListener);

        viewHolder2.btn_button.setText(getItem(position).button);
        viewHolder2.btn_button.setOnClickListener(buttonClickListener);
        viewHolder2.btn_button.setTag(position);

        viewHolder2.cb_box.setTag(position);
        viewHolder2.cb_box.setOnClickListener(buttonClickListener);

        return v;
    }

    public void setArrayList(ArrayList<InfoClass> arrays){
         this.infoList = arrays;
    }

    public ArrayList<InfoClass> getArrayList() {
        return infoList;
    }

    private  View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_image:
                    Toast.makeText(mContext,"이미지 tag="+v.getTag(),Toast.LENGTH_SHORT).show();
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

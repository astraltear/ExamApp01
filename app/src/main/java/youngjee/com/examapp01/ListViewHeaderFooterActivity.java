package youngjee.com.examapp01;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ListViewHeaderFooterActivity extends ActionBarActivity {

    private String[] mContents = {"사자","여우","곰","호랑이","타조","말","돼지","사슴","살쾡이","푸우","퓨마","백호","늑대","여우","물개","고래","새우","고등어"};
    private CustomArrayAdapter2  customArrayAdapter2 ;
    private ListView lt_List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_header_footer);

        setLayout();
//        ListView의 Header, Footer는 LIstView에 setAdapter를 해주기 전에 적용 시켜야 한다.
        View header =getLayoutInflater().inflate(R.layout.header, null);
        View footer = getLayoutInflater().inflate(R.layout.footer, null);

        lt_List.addHeaderView(header);
        lt_List.addFooterView(footer);

        header.findViewById(R.id.btn_header).setOnClickListener(mClickListener);
        footer.findViewById(R.id.btn_footer).setOnClickListener(mClickListener);

        customArrayAdapter2 = new CustomArrayAdapter2(this,R.layout.list_row2,mContents);
        lt_List.setAdapter(customArrayAdapter2);

    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_header){
                Toast.makeText(ListViewHeaderFooterActivity.this,"Header!!",Toast.LENGTH_SHORT).show();
            } else if (v.getId() == R.id.btn_footer){
                Toast.makeText(ListViewHeaderFooterActivity.this,"Footer!!",Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void setLayout() {
        lt_List = (ListView) findViewById(R.id.lv_list);
    }

    class CustomArrayAdapter2 extends ArrayAdapter<String>{
        private ViewHolder viewHolder ;
        private LayoutInflater inflater;
        private int resourceID;


        public CustomArrayAdapter2(Context context, int resource, String [] contents) {
            super(context, resource,contents);
            resourceID = resource;
            inflater = LayoutInflater.from(context);
        }

        @Override  public int getCount() { return super.getCount(); }
        @Override  public String getItem(int position) { return super.getItem(position); }
        @Override  public long getItemId(int position) {  return super.getItemId(position); }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null){
                v = inflater.inflate(resourceID, null);
                viewHolder = new ViewHolder();
                viewHolder.tv_title = (TextView) v.findViewById(R.id.tv_title);

                v.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) v.getTag();
            }

            viewHolder.tv_title.setText(getItem(position));
            return v;
        }

        @Override
        protected void finalize() throws Throwable {
            free();
            super.finalize();
        }

        private void free() {
            inflater = null;
            viewHolder = null;
        }

        class ViewHolder {
            public TextView tv_title =null;
        }
    }

}

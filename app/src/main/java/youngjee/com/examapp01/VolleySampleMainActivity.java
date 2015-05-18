package youngjee.com.examapp01;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class VolleySampleMainActivity extends ActionBarActivity {

    private String TAG = this.getClass().getSimpleName();
    private ListView listView;
    private RequestQueue mRequestQueue;
    private ArrayList<NewsModel> arrNews;
    private LayoutInflater lf;
    private VolleyAdapter va;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_sample_main);

        lf = LayoutInflater.from(this);

        arrNews = new ArrayList<NewsModel>();
        va = new VolleyAdapter();

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(va);

        mRequestQueue = Volley.newRequestQueue(this);

        String url ="http://pipes.yahooapis.com/pipes/pipe.run?_id=giWz8Vc33BG6rQEQo_NLYQ&_render=json";
        pd = ProgressDialog.show(this,"Please Wait...","Please Wait...");

        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url,null,

        new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.d(TAG, jsonObject.toString());
                parseJSON(jsonObject);
                va.notifyDataSetChanged();
                pd.dismiss();

            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d(TAG, volleyError.getMessage());

            }
        });

        mRequestQueue.add(jr);

    }

    private void parseJSON(JSONObject json){
        try {
            JSONObject value = json.getJSONObject("value");
            JSONArray items = value.getJSONArray("items");

            for (int i =0; i< items.length();i++) {
                JSONObject item = items.getJSONObject(i);
                NewsModel newsModel = new NewsModel();
                newsModel.setTitle(item.optString("title"));
                newsModel.setDescription(item.optString("description"));
                newsModel.setLink(item.optString("link"));
                newsModel.setPubDate(item.optString("pubDate"));

                arrNews.add(newsModel);
            }

        } catch (Exception e){

        }
    }


    class NewsModel{
        private String title;
        private String link;
        private String description;
        private String pubDate;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }
    }


    class VolleyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrNews.size();
        }

        @Override
        public Object getItem(int position) {
            return arrNews.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            VolleyViewHolder volleyViewHolder;

            if (convertView == null) {
                volleyViewHolder = new VolleyViewHolder();
                convertView = lf.inflate(R.layout.volley_row_listview, null);
                volleyViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.txtTitle);
                volleyViewHolder.tvDesc = (TextView) convertView.findViewById(R.id.txtDesc);
                volleyViewHolder.tvDate = (TextView) convertView.findViewById(R.id.txtDate);

                convertView.setTag(volleyViewHolder);
            } else {
                volleyViewHolder = (VolleyViewHolder) convertView.getTag();
            }

            NewsModel newsModel = arrNews.get(position);
            volleyViewHolder.tvTitle.setText(newsModel.getTitle());
            volleyViewHolder.tvDesc.setText(newsModel.getDescription());
            volleyViewHolder.tvDate.setText(newsModel.getPubDate());

            return convertView;
        }
    }

    class VolleyViewHolder {
        TextView tvTitle;
        TextView tvDesc;
        TextView tvDate;
    }

}

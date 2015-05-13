package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import org.lucasr.twowayview.TwoWayView;

import youngjee.com.examapp01.adapter.RecyclerListAdapter;


public class RecyclerViewActivity extends ActionBarActivity {

    private final String TAG = getClass().getSimpleName();

    private TwoWayView mListView;

    private Toast mToast;
    private String mClickMessage;
    private String mScrollMessage;
    private String mStateMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mClickMessage="";
        mScrollMessage="";
        mStateMessage="";
        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER,0,0);

        mListView = (TwoWayView) findViewById(R.id.list);
        mListView.setItemMargin(10);
        mListView.setLongClickable(true);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mClickMessage = "[item Clicked:" + position+"]";
                refreshToast();
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mClickMessage = "[item Long Clicked:" + position+"]";
                refreshToast();
                return true;
            }
        });

        mListView.setOnScrollListener(new TwoWayView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(TwoWayView view, int scrollState) {
                String stateName = "Undefined";

                switch (scrollState) {
                    case SCROLL_STATE_IDLE:
                        stateName = "idle";
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        stateName = "Dragging";
                        break;
                    case SCROLL_STATE_FLING:
                        stateName = "Flinging";
                        break;
                }

                mStateMessage = "[Scroll state changed" + stateName+"]";
                refreshToast();
            }

            @Override
            public void onScroll(TwoWayView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                mScrollMessage = "[Scroll(first:" + firstVisibleItem + ", count=" + visibleItemCount + ")]";
                refreshToast();
            }
        });

        mListView.setRecyclerListener(new TwoWayView.RecyclerListener() {
            @Override
            public void onMovedToScrapHeap(View view) {
                Log.d(TAG, "View moved to scrap heap");
            }
        });

        mListView.setAdapter(new RecyclerListAdapter(RecyclerViewActivity.this));
    }

    private void refreshToast() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(mClickMessage).append(mScrollMessage).append(mStateMessage);

        mToast.setText(buffer.toString());
        mToast.show();

    }


}

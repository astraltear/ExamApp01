package youngjee.com.examapp01;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class DownloaderDemoActivity extends ActionBarActivity implements View.OnClickListener {

    private EditText et_input_url = null;
    private Button btn_downqueue = null;
    private Button btn_downcancel = null;
    private Button btn_downview = null;

    private long lastId = -1;

    private DownloadManager downloadManager;
    private DownloadManager.Request request;
    private Uri downloadUri;

    private BroadcastReceiver completeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"다운로드가 완료되었습니다",Toast.LENGTH_LONG).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloader_demo);

        et_input_url = (EditText) findViewById(R.id.et_input_url);
        btn_downqueue = (Button) findViewById(R.id.btn_downqueue);
        btn_downcancel = (Button) findViewById(R.id.btn_downqueue);
        btn_downview = (Button) findViewById(R.id.btn_downview);

        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        btn_downqueue.setOnClickListener(this);
        btn_downcancel.setOnClickListener(this);
        btn_downview.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(completeReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(completeReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_downqueue: {
                downloadUri = Uri.parse(et_input_url.getText().toString());
                List<String> pathSegments = downloadUri.getPathSegments();

                request = new DownloadManager.Request(downloadUri);
                request.setTitle("다운로드 예제");
                request.setDescription("설명");
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, pathSegments.get(pathSegments.size()-1));
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdirs();
                lastId = downloadManager.enqueue(request);

                break;
            }

            case R.id.btn_downcancel: {
                downloadManager.remove(lastId);
                break;
            }
            case R.id.btn_downview:{
                startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));
                break;
            }
        }

    }
}

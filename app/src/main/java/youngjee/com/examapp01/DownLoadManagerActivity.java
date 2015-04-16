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

public class DownLoadManagerActivity extends ActionBarActivity implements View.OnClickListener {

    private EditText downloadUrl;
    private Button addToQueueBtn;
    private Button cancelLastestBtn;
    private Button viewDownloadsBtn;

    private DownloadManager downloadManager;
    private DownloadManager.Request request;
    private Uri urlToDownload;

    private long latestId = -1;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"다운로드가 완료되었습니다.",Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load_manager);

        downloadUrl = (EditText) findViewById(R.id.downloadUrl);
        addToQueueBtn = (Button) findViewById(R.id.addQueueBtn);
        cancelLastestBtn = (Button) findViewById(R.id.cancelDownloadBtn);
        viewDownloadsBtn = (Button) findViewById(R.id.viewDownloadsBtn);

        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        addToQueueBtn.setOnClickListener(this);
        cancelLastestBtn.setOnClickListener(this);
        viewDownloadsBtn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addQueueBtn:
                urlToDownload = Uri.parse(downloadUrl.getText().toString());
                List<String> pathSegments = urlToDownload.getPathSegments();
                request = new DownloadManager.Request(urlToDownload);
                request.setTitle("다운로드 예제");
                request.setDescription("항목설명");
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, pathSegments.get(pathSegments.size() - 1));
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdirs();
                latestId = downloadManager.enqueue(request);

                break;

            case R.id.cancelDownloadBtn:
                downloadManager.remove(latestId);
                break;

            case R.id.viewDownloadsBtn:
                startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));
                break;
        }
    }
}

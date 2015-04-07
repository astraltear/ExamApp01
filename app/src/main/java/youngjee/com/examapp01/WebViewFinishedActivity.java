package youngjee.com.examapp01;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewFinishedActivity extends ActionBarActivity {

    ProgressDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_finished);

        WebView webView = (WebView) findViewById(R.id.wbView);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("http://m.naver.com");

        dialog = ProgressDialog.show(this, "제목", "waiting...", false, true);

        webView.setWebViewClient(
                new WebViewClient(){
                    @Override
                    public void onPageFinished(WebView view, String url) {

                        if (dialog != null){
                            dialog.dismiss();
                        }
                    }
                }
        );
    }
}

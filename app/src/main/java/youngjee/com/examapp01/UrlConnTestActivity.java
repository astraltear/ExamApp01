package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlConnTestActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_conn_test);

        NetworkThread networkThread = new NetworkThread();
        networkThread.start();





    }


    private class NetworkThread extends Thread{
        @Override
        public void run() {
            try {
                URL url = new URL("http://www.daum.net");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                InputStream is = urlConnection.getInputStream();
//                OutputStream os = urlConnection.getOutputStream();

//                Log.d("RESPONSECODE:", urlConnection.getResponseCode()+"|"+urlConnection.getResponseMessage());
//
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

                StringBuffer buffer = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    buffer.append(line + "\n");
                }
                bufferedReader.close();
                Log.d("UrlConnTestActivity", buffer.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

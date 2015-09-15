package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import youngjee.com.examapp01.async.OpenWeatherAPITask;
import youngjee.com.examapp01.dto.Weather;


public class RestAPISampleActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_apisample);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rest_apisample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getWeather(View view) {
        EditText tvLon = (EditText) findViewById(R.id.lon);
        EditText tvLat = (EditText) findViewById(R.id.lat);

        int lon = Integer.parseInt(tvLon.getText().toString());
        int lat = Integer.parseInt(tvLat.getText().toString());

        OpenWeatherAPITask task = new OpenWeatherAPITask();

        try {
            Weather w = task.execute(lon, lat).get();

            EditText temp = (EditText) findViewById(R.id.temp);
            temp.setText( String.valueOf(w.getTemperature())  );

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

package youngjee.com.examapp01.async;

import android.os.AsyncTask;

import youngjee.com.examapp01.client.OpenWeatherAPIClient;
import youngjee.com.examapp01.dto.Weather;

public class OpenWeatherAPITask extends AsyncTask<Integer,Void,Weather> {

    @Override
    protected Weather doInBackground(Integer... params) {
        OpenWeatherAPIClient client = new OpenWeatherAPIClient();

        int lat = params[0];
        int lon = params[1];

        Weather w = client.getWeather(lat, lon);

        return w;
    }
}

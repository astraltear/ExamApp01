package youngjee.com.examapp01.client;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import youngjee.com.examapp01.dto.Weather;

public class OpenWeatherAPIClient {

    String openWeatherURL = "http://api.openweathermap.org/data/2.5/weather";

    public Weather getWeather(int lat,int lon){
        Weather w = new Weather();
        String urlString = openWeatherURL+"?lat="+lat+"&lon="+lon;

            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                JSONObject json = new JSONObject(getStringFromInputStream(in));

                w = parseJSON(json);
                w.setLon(lon);
                w.setLat(lat);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        return  w;
    }

    private Weather parseJSON(JSONObject json) throws JSONException{
        Weather w = new Weather();
        w.setTemperature(json.getJSONObject("main").getInt("temp"));
        w.setCity(json.getString("name"));

        return w;
    }

    private static String getStringFromInputStream(InputStream is){
        BufferedReader br =  null;
        StringBuffer sb = new StringBuffer();

        String line;

        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) !=null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return  sb.toString();
    }
}

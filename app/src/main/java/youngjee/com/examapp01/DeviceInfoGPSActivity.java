package youngjee.com.examapp01;

import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DeviceInfoGPSActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info_gps);

        TextView tv_deviceInfo = (TextView) findViewById(R.id.tv_deviceInfo);

        TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(TELEPHONY_SERVICE);
        String NetworkOPName = tm.getNetworkOperatorName();
        String countryISO = tm.getSimCountryIso();
        boolean isNetworkRoaming = tm.isNetworkRoaming();
        String systemLanguage = getBaseContext().getResources().getConfiguration().locale.getLanguage();

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        String provider = locationManager.getBestProvider(setCriteria(), true);

        Location loc = locationManager.getLastKnownLocation(provider);
        Geocoder geocoder = new Geocoder(this, Locale.KOREAN);

        String country="";
        try {
            List<Address> addresses = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 5);
            Log.d("DeviceInfoGPSActivity", "addresses.size()" + addresses.size());
            if (addresses.size() > 0 ) {
                Address address = addresses.get(0);
                country = address.getCountryName();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2초마다 0미터 이상 이동시 업데이트
        locationManager.requestLocationUpdates(provider,2000,0,mLocationListener);


        StringBuffer buffer = new StringBuffer();
        buffer.append("getNetworkOperatorName:").append(NetworkOPName).append("\n")
        .append("getSimCountryIso:").append(countryISO).append("\n")
        .append("isNetworkRoaming:").append(isNetworkRoaming).append("\n")
        .append("Language:").append(systemLanguage).append("\n")
        .append("provider:").append(provider).append("\n")
        .append("country:").append(country).append("\n")
        ;

        tv_deviceInfo.setText(buffer.toString());
    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Toast.makeText(getBaseContext(), "GPS Change",Toast.LENGTH_SHORT).show();
        }

        // GPS 상태 변화에 따른 액션
        @Override  public void onStatusChanged(String provider, int status, Bundle extras) {}
        // GPS 사용가능으로 변경했을 때
        @Override public void onProviderEnabled(String provider) {}
        // GPS 사용불가능으로 변경했을 때
        @Override public void onProviderDisabled(String provider) {}
    };

    private Criteria setCriteria() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);

        return criteria;
    }

}

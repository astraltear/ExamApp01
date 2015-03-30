package youngjee.com.examapp01;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

public class GetUUIDActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_uuid);

        String andorid_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        // secure.andorid_id
        TextView tv_info1 = (TextView) findViewById(R.id.tv_info1);
        tv_info1.setText("Secure.ANDROID_ID["+andorid_id+"]");

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String tmDevice, tmSerial;
        tmDevice = telephonyManager.getDeviceId();
        tmSerial = telephonyManager.getSimSerialNumber();

        UUID deviceUuid = new UUID(andorid_id.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode() );
        String deviceid = deviceUuid.toString();

        // uuid
        TextView tv_info2 = (TextView) findViewById(R.id.tv_info2);
        tv_info2.setText("tmDevice["+tmDevice+"]tmSerial["+tmSerial+"]deviceid["+deviceid+"]");

        String serial ="";

        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            serial = (String) get.invoke(c, "ro.serialno");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        TextView tv_info3 = (TextView) findViewById(R.id.tv_info3);
        tv_info3.setText("serial["+serial+"]");

        WifiManager wm = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        TextView tv_info4 = (TextView) findViewById(R.id.tv_info4);
        tv_info4.setText("WIFI MAC ADDRESS["+wm.getConnectionInfo().getMacAddress()+"]");

    }
}

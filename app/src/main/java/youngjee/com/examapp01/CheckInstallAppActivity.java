package youngjee.com.examapp01;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class CheckInstallAppActivity extends ActionBarActivity {

    private static final String CHECK_PACKAGE_NAME = "youngjee.com.examapp01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_install_app);
        PackageManager pm = getPackageManager();

        try {
            PackageInfo pi = pm.getPackageInfo(CHECK_PACKAGE_NAME.trim(), PackageManager.GET_META_DATA);
            ApplicationInfo appInfo = pi.applicationInfo;
            Log.d("CheckInstallAppActivity", "패키지가 설치되었습니다.");
            Toast.makeText(getApplicationContext(), "pi.versionName["+pi.versionName+"]pi.versionCode["+pi.versionCode+"]", Toast.LENGTH_SHORT);

            // OR
            pm.getApplicationInfo(CHECK_PACKAGE_NAME.toLowerCase(), PackageManager.GET_META_DATA);

        } catch (PackageManager.NameNotFoundException e) {
            Log.d("CheckInstallAppActivity", "패키지가 설치 되지 않았습니다.");
        }
    }
}

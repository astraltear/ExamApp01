package youngjee.com.examapp01;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;


public class ImplicitActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);
    }

    public void goCalling(View v) {
       /* Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:02-6959-8027"));
        startActivity(intent);
        */

        Uri number = Uri.parse("tel:02-6959-8027");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }

    public void goViewMap(View v) {

        Uri location = Uri.parse("geo:39.422219, -122.0864?z=14");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
        startActivity(mapIntent);
    }

    public void goViewWeb(View v) {

        Uri webpage = Uri.parse("http://www.android.com");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

        PackageManager packageManager = getPackageManager();
        List activities = packageManager.queryIntentActivities(webIntent, 0);
        Toast.makeText(this,  "activities.size()" + activities.size(),Toast.LENGTH_LONG).show();
        boolean isIntentSafe =  activities.size() > 0;

        if (isIntentSafe) {
            Intent chooser = Intent.createChooser(webIntent, "is selected??");
            startActivity(chooser);
        }


    }

}

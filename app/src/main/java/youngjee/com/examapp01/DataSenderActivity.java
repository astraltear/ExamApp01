package youngjee.com.examapp01;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;


public class DataSenderActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_sender);

        Button button = (Button) findViewById(R.id.btn_IntentSender);
        button.setOnClickListener(this);

        Button button1 = (Button) findViewById(R.id.btn_implicit);
        button1.setOnClickListener(this);

        Button button2 = (Button) findViewById(R.id.btn_isAvailable);
        button2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_IntentSender){
            Intent intent = new Intent(this,DataReceiverActivity.class);
            intent.putExtra("NO",123);
            intent.putExtra("PW","testpw");
            startActivityForResult(intent,934);
        } else if ( v.getId() == R.id.btn_implicit ) {
            startActivity(new Intent("young.jee.Implicit.Intent"));

        } else if(v.getId() == R.id.btn_isAvailable){
            boolean flag_sms = availableSMS(getBaseContext());
            boolean flag_mms = availableMMS(getBaseContext());
            boolean flag_call = availableCALL(getBaseContext());

            Toast.makeText(this,"available SMS["+flag_sms+"]MMS["+flag_mms+"]CALL["+flag_call+"]",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus == true){
            Toast.makeText(getBaseContext(),"Visible", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getBaseContext(),"IN Visible", Toast.LENGTH_SHORT).show();
        }
    }

    public void goTelNo(View v) {
        Intent contactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        contactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(contactIntent,888);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("EXAM",requestCode+"|"+resultCode);

        if (resultCode == RESULT_OK && requestCode == 934){
            data.getIntExtra("AGE", 25);
            data.getStringExtra("ADRESS");

            Log.d("DataSenderActivity", data.getIntExtra("AGE", 1)+"|"+data.getStringExtra("ADRESS"));
        } else if (resultCode == RESULT_OK && requestCode == 888){
            Uri contactUri = data.getData();

            Log.d("DataSenderActivity", contactUri.getPath());

            String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};
            Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
            cursor.moveToFirst();

            int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            Log.d("DataSenderActivity", "["+cursor.getString(column)+"]");

        }
    }

    private boolean availableSMS(Context context) {
        PackageManager pac = context.getPackageManager();
        Uri smsUri = Uri.parse("sms:");
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, smsUri);
        List<ResolveInfo> list = pac.queryIntentActivities(smsIntent, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);

        String packageName ="";
        for (int i = 0 ; i<list.size(); i++) {
            ResolveInfo firstInfo = list.get(i);
            packageName = firstInfo.activityInfo.applicationInfo.packageName;
            Log.d("availableSMS", packageName);
        }

        if (packageName ==  null || ("").equals(packageName)){
            return  false;
        } else {
            return true;
        }
    }

    private boolean availableMMS(Context baseContext) {
        PackageManager pac = baseContext.getPackageManager();
        Uri mmsUri = Uri.parse("mmsto:");
        Intent mmsIntent = new Intent(Intent.ACTION_VIEW, mmsUri);
        List<ResolveInfo> list = pac.queryIntentActivities(mmsIntent, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);

        String packageName ="";
        for (int i = 0 ; i<list.size();i++) {
            ResolveInfo firstInfo = list.get(i);
            packageName = firstInfo.activityInfo.applicationInfo.packageName;
            Log.d("availableMMS", packageName);
        }

        if (packageName ==  null || ("").equals(packageName)){
            return  false;
        } else {
            return true;
        }
    }

    private boolean availableCALL(Context baseContext) {
        PackageManager pac = baseContext.getPackageManager();
        Uri callUri = Uri.parse("tel:");
        Intent callIntent = new Intent(Intent.ACTION_CALL, callUri);
        List<ResolveInfo> list = pac.queryIntentActivities(callIntent, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);


        String packageName ="";
        for (int i = 0 ; i<list.size();i++) {
            ResolveInfo firstInfo = list.get(i);
            packageName = firstInfo.activityInfo.applicationInfo.packageName;
            Log.d("availableCALL", packageName);
        }

        if (packageName ==  null || ("").equals(packageName)){
            return  false;
        } else {
            return true;
        }
    }



}

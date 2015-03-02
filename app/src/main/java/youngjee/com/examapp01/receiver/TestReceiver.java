package youngjee.com.examapp01.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import youngjee.com.examapp01.ServiceDemoActivity;

public class TestReceiver extends BroadcastReceiver {
    public TestReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getAction();
        if (name.equals("youngjee.recevier.sender")) {
            Intent intent1 = new Intent(context, ServiceDemoActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
//            Toast.makeText(context, "receive OK!!!", Toast.LENGTH_SHORT).show();

        }
    }
}

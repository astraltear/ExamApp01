package youngjee.com.examapp01;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewNotificationActivity extends ActionBarActivity implements View.OnClickListener {

    private Button btnBigPicture;
    private Button btnBigText;
    private Button btnInBox;
    private Button btnNormal;
    private Bitmap bmBigPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notification);

        btnBigPicture = (Button) findViewById(R.id.big_picture);
        btnBigText = (Button) findViewById(R.id.big_text);
        btnInBox = (Button) findViewById(R.id.inbox_style);
        btnNormal = (Button) findViewById(R.id.normal);

        btnBigPicture.setOnClickListener(this);
        btnBigText.setOnClickListener(this);
        btnInBox.setOnClickListener(this);
        btnNormal.setOnClickListener(this);

        bmBigPicture = BitmapFactory.decodeResource(getResources(), R.drawable.android_jellybean);
    }

    @Override
    public void onClick(View v) {
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = null;
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://www.google.com")), 0);

        switch (v.getId()){
            case R.id.big_picture:
                notification = new Notification.BigPictureStyle(
                        new Notification.Builder(getApplicationContext()).setContentTitle("Title").setContentText("Big Picture")
                                .setSmallIcon(R.drawable.ic_launcher).setLargeIcon(bmBigPicture).setTicker("BigPicture!!")
                                .addAction(R.drawable.ic_launcher, "View online", pendingIntent)
                                .addAction(R.drawable.ic_action_search, "Search similar", pendingIntent))
                        .bigPicture(bmBigPicture)
                        .setBigContentTitle("Big Notification")
                        .setSummaryText("BigNotification summary").build();
                break;
            case R.id.big_text:
                notification = new Notification.BigTextStyle(
                        new Notification.Builder(getApplicationContext()).setContentTitle("Content Title").setContentText("Content Text")
                                .setSmallIcon(R.drawable.ic_launcher).setLargeIcon(bmBigPicture).setTicker("Big Text!"))
                        .setSummaryText("Android News")
                        .bigText("adfsrjweklrwepeirtikjflksjfaslfsjawer rwee rwrwrer ssjflasjklw werwrpwqopwrjklsjlkjdklgd  ").build();
                break;

            case R.id.inbox_style:
                notification = new Notification.InboxStyle(
                        new Notification.Builder(getApplicationContext()).setContentTitle("4 new mails").setContentText("content text").setSmallIcon(R.drawable.ic_launcher)
                            .setLargeIcon(bmBigPicture).setTicker("Inbox"))
                        .addLine("adccjklvjklerjwek werw e jkjklsf lk werewr  rwwrwewr")
                        .addLine("adccjklvjklerjwek werw e jkjklsf lk werewr  rwwrwewr")
                        .addLine("adccjklvjklerjwek werw e jkjklsf lk werewr  rwwrwewr")
                        .addLine("adccjklvjklerjwek werw e jkjklsf lk werewr  rwwrwewr")
                        .setSummaryText("+3 more").build();
                break;
            case R.id.normal:
                notification = new Notification.Builder(getApplicationContext())
                        .setContentTitle("Normal notification title").setContentText("Normal Content Text")
                        .setProgress(100, 10, false).setSmallIcon(R.drawable.ic_launcher).setLargeIcon(bmBigPicture)
                        .setTicker("Normal Noti").addAction(R.drawable.ic_action_search, "Action", pendingIntent).build();
                break;
        }
        nm.notify(0,notification);

    }
}

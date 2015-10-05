package youngjee.com.examapp01;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    boolean m_close_flag= false;

    Handler m_close_handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            m_close_flag=false;
        }
    };

    @Override
    public void onBackPressed() {

        if (m_close_flag == false) {
            Toast.makeText(this, "취소키를 빨리 한번 더 누르시면 종료됩니다.", Toast.LENGTH_LONG).show();
            m_close_flag = true;
           m_close_handler.sendEmptyMessageDelayed(0, 3000);

        } else {
            super.onBackPressed();
        }
    }

    protected void onStop(){
        super.onStop();

        // 핸드러에 등록된 0번 메세지를 모두 지운다.
        m_close_handler.removeMessages(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_ArrAdapter = (Button) findViewById(R.id.btn_ArrAdapter);
        btn_ArrAdapter.setOnClickListener(this);

        Button btn_UserAdapter = (Button) findViewById(R.id.btn_UserAdapter);
        btn_UserAdapter.setOnClickListener(this);

        Button btn_ArrAdapter2 = (Button) findViewById(R.id.btn_ArrAdapter2);
        btn_ArrAdapter2.setOnClickListener(this);

        Button btnAssetImageRead = (Button) findViewById(R.id.btnAssetImageRead);
        btnAssetImageRead.setOnClickListener(this);

        Button btnMemoryCheck = (Button) findViewById(R.id.btnMemoryCheck);
        btnMemoryCheck.setOnClickListener(this);

        Button btnActionBar = (Button) findViewById(R.id.btnActionBar);
        btnActionBar.setOnClickListener(this);

        Button btnIntentDataSend = (Button) findViewById(R.id.btnIntentDataSend);
        btnIntentDataSend.setOnClickListener(this);

        Button btnTouchEvent = (Button) findViewById(R.id.btnTouchEvent);
        btnTouchEvent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v.getId() == R.id.btn_ArrAdapter){
            intent = new Intent(this,ArrayListActivity.class);
            startActivity(intent);
        } else  if (v.getId() ==R.id.btn_UserAdapter) {
            intent = new Intent(this, UserAdapterActivity.class);
            startActivity(intent);
        } else   if (v.getId() ==R.id.btn_ArrAdapter2) {
            intent = new Intent(this,ArrayList2Activity.class);
            startActivity(intent);
        } else   if (v.getId() ==R.id.btnAssetImageRead) {
            intent = new Intent(this,AssertImageReadActivity.class);
            startActivity(intent);
        } else   if (v.getId() ==R.id.btnMemoryCheck) {
            intent = new Intent(this,MemoryCheckActivity.class);
            startActivity(intent);
        } else   if (v.getId() ==R.id.btnActionBar) {
            intent = new Intent(this,ActionBarSampleActivity.class);
            startActivity(intent);
        } else   if (v.getId() ==R.id.btnIntentDataSend) {
            intent = new Intent(this,DataSenderActivity.class);
            startActivity(intent);
        } else   if (v.getId() ==R.id.btnTouchEvent) {
            intent = new Intent(this,TouchEventExamActivity.class);
            startActivity(intent);

        }
    }


    public void goTakePicture(View v){
        Intent intent =new Intent(this,TakePictureActivity.class);
        startActivity(intent);
    }

    public void goTapHost(View v) {
        Intent intent = new Intent(this, TapHostActivity.class);
        startActivity(intent);
    }

    public void goMakeDialog(View v) {
        Intent intent = new Intent(this, MakeDialogActivity.class);
        startActivity(intent);
    }

    public void goMakeProgress(View v) {
        Intent intent = new Intent(this, MakeProgressActivity.class);
        startActivity(intent);
    }

    public void goWakeLock(View v) {
        Intent intent = new Intent(this, WakeLockActivity.class);
        startActivity(intent);
    }

    public void goDynaScroll(View v) {
        Intent intent = new Intent(this, DynaMicScrollActivity.class);
        startActivity(intent);
    }

    public void goUiLayout(View v) {
        Intent intent = new Intent(this, UILayoutActivity.class);
        startActivity(intent);
    }

    public void goSharedpr(View v) {
        Intent intent = new Intent(this, SharedPreferencesActivity.class);
        startActivity(intent);
    }

    public void goFileWrite(View v){
        Intent intent = new Intent(this, FileWriteActivity.class);
        startActivity(intent);
    }

    public void goSqlHandle(View v){
        Intent intent = new Intent(this, SqlHandleActivity.class);
        startActivity(intent);
    }

    public void goImplicitIntent(View v){
        Intent intent = new Intent(this, ImplicitActivity.class);
        startActivity(intent);
    }

    public void goDialogCallBack(View v){
        Intent intent = new Intent(this, DialogCallBackActivity.class);
        startActivity(intent);
    }

    public void goNotification(View v){
        Intent intent = new Intent(this, TestNotificationActivity.class);
        startActivity(intent);
    }

    public void goAutoCompleteText(View v){
        Intent intent = new Intent(this, AutoCompleteTextActivity.class);
        startActivity(intent);
    }

    public void goViewPager(View v){
        Intent intent = new Intent(this, ViewPagerMainActivity.class);
        startActivity(intent);
    }

    public void goTextWatcher(View v){
        Intent intent = new Intent(this, TextWatcherActivity.class);
        startActivity(intent);
    }

    public void goArraySpinner(View v){
        Intent intent = new Intent(this, ArraySpinnerActivity.class);
        startActivity(intent);
    }

    public void goCustomListView(View v){
        Intent intent = new Intent(this, CustomListViewActivity.class);
        startActivity(intent);
    }

    public void goArrayAdapter2(View v){
        Intent intent = new Intent(this, ArrayAdapterActivity.class);
        startActivity(intent);
    }

    public void goExpandableListView(View v){
        Intent intent = new Intent(this, ExpandableListViewActivity.class);
        startActivity(intent);
    }

    public void goListView_OnItemCLick(View v){
        Intent intent = new Intent(this, ListViewOnItemClickListenerActivity.class);
        startActivity(intent);
    }

    public void goListView_HeaderFooter(View v){
        Intent intent = new Intent(this, ListViewHeaderFooterActivity.class);
        startActivity(intent);
    }
    public void goDateTimerPicker(View v){
        Intent intent = new Intent(this, DateTimerPickerActivity.class);
        startActivity(intent);
    }

    public void goMakeFlipper(View v){
        Intent intent = new Intent(this, MakeFlipperActivity.class);
        startActivity(intent);
    }

    public void goMakeThread(View v){
        Intent intent = new Intent(this, ThreadDemoActivity.class);
        startActivity(intent);
    }

    public void goAsyncTask(View v){
        Intent intent = new Intent(this, AsyncTaskDemoActivity.class);
        startActivity(intent);
    }

    public void goPopupWindow(View v){
        Intent intent = new Intent(this, PopupWindowActivity.class);
        startActivity(intent);
    }

    public void goIntentExam(View v){
        Intent intent = new Intent(this, IntentExamActivity.class);
        startActivity(intent);
    }

    public void goRotateActivity(View v){
        Intent intent = new Intent(this, RotateExamActivity.class);
        startActivity(intent);
    }

    public void goTestConfigure(View v){
        Intent intent = new Intent(this, TestConfigureActivity.class);
        startActivity(intent);
    }

    public void goMakeService(View v){
        Intent intent = new Intent(this, ServiceDemoActivity.class);
        startActivity(intent);
    }

    public void goBroadCastReceiver(View v){
        Intent intent = new Intent(this, BroadcastReceiverActivity.class);
        startActivity(intent);
    }

    public void goAlarmManager(View v){
        Intent intent = new Intent(this, AlarmManagerActivity.class);
        startActivity(intent);
    }

    public void goThreadDemo2(View v){
        Intent intent = new Intent(this, ThreadDemo2Activity.class);
        startActivity(intent);
    }

    public void goUseHandler(View v){
        Intent intent = new Intent(this, UseHandlerActivity.class);
        startActivity(intent);
    }

    public void goFileMng(View v){
        Intent intent = new Intent(this, FileManagerActivity.class);
        startActivity(intent);
    }

    public void goClickLongClick(View v){
        Intent intent = new Intent(this, ClickNLongClickActivity.class);
        startActivity(intent);
    }

    public void goImageSendSMS(View v){
        Intent intent = new Intent(this, ImageSendSMSActivity.class);
        startActivity(intent);
    }

    public void goDeviceInfoGPS(View v){
        Intent intent = new Intent(this, DeviceInfoGPSActivity.class);
        startActivity(intent);
    }

    public void goCheckInstallApp(View v){
        Intent intent = new Intent(this, CheckInstallAppActivity.class);
        startActivity(intent);
    }

    public void goConnNet(View v){
        Intent intent = new Intent(this, UrlConnTestActivity.class);
        startActivity(intent);
    }

    public void goGoogleTrans(View v){
        Intent intent = new Intent(this, GoogleTransActivity.class);
        startActivity(intent);
    }

    public void goDownloader(View v){
        Intent intent = new Intent(this, DownloaderDemoActivity.class);
        startActivity(intent);
    }

    public void goMp3Player(View v){
        Intent intent = new Intent(this, MP3PlayerDemoActivity.class);
        startActivity(intent);
    }

    public void goMp3Player2(View v){
        Intent intent = new Intent(this, MP3Player2DemoActivity.class);
        startActivity(intent);
    }

    public void goFragmentDemo(View v){
        Intent intent = new Intent(this, FragmentDemoActivity.class);
        startActivity(intent);
    }

    public void goNewNotification(View v){
        Intent intent = new Intent(this, NewNotificationActivity.class);
        startActivity(intent);
    }

    public void goDrawerLayout(View v){
        Intent intent = new Intent(this, DrawerLayoutActivity.class);
        startActivity(intent);
    }

    public void goDrawerNFrag(View v){
        Intent intent = new Intent(this, DrawerNFragmentActivity.class);
        startActivity(intent);
    }

    public void goEffectSound(View v){
        Intent intent = new Intent(this, EffectSoundActivity.class);
        startActivity(intent);
    }

    public void goStreamingPlayer(View v){
        Intent intent = new Intent(this, StreamingPlayerActivity.class);
        startActivity(intent);
    }

    public void goGetUuid(View v){
        Intent intent = new Intent(this, GetUUIDActivity.class);
        startActivity(intent);
    }

    public void goTelNoSearch(View v){
        Intent intent = new Intent(this, TelNoSearchActivity.class);
        startActivity(intent);
    }

    public void goMusicPlayer(View v){
        Intent intent = new Intent(this, MusicSearchActivity.class);
        startActivity(intent);
    }

    public void goWebViewFinished(View v){
        Intent intent = new Intent(this, WebViewFinishedActivity.class);
        startActivity(intent);
    }

    public void goDBHandle(View v) {
        Intent intent = new Intent(this, DataBaseHandleActivity.class);
        startActivity(intent);
    }

    public void goDownloadMng(View v) {
        Intent intent = new Intent(this, DownLoadManagerActivity.class);
        startActivity(intent);
    }

    public void goAlarmMng(View v) {
        Intent intent = new Intent(this, AlarmManager2Activity.class);
        startActivity(intent);
    }

    public void goPWDMng(View v) {
        Intent intent = new Intent(this, PasswordManagerActivity.class);
        startActivity(intent);
    }

    public void goListViewIndex(View v) {
        Intent intent = new Intent(this, ListViewIndexActivity.class);
        startActivity(intent);
    }

    public void goImageCache(View v){
        Intent intent = new Intent(this, ImageCacheActivity.class);
        startActivity(intent);
    }
    public void goDataBaseHandle(View v){
        Intent intent = new Intent(this, DataBaseHandle2Activity.class);
        startActivity(intent);
    }

    public void goAdapterFiler(View v){
        Intent intent = new Intent(this, AdapterFilterActivity.class);
        startActivity(intent);
    }

    public void goSendObject(View v){
        Intent intent = new Intent(this, ActivitySendObjectActivity.class);
        startActivity(intent);
    }

    public void goPhotoView(View v){
        Intent intent = new Intent(this, PhotoViewerActivity.class);
        startActivity(intent);
    }

    public void goRecyclerView(View v){
        Intent intent = new Intent(this, RecyclerViewActivity.class);
        startActivity(intent);
    }

    public void goAuilsample(View v){
        Intent intent = new Intent(this, AuilSampleMainActivity.class);
        startActivity(intent);
    }

    public void goVolleysample(View v){
        Intent intent = new Intent(this, VolleySampleMainActivity.class);
        startActivity(intent);
    }

    public void goSlideUpPanel(View v){
        Intent intent = new Intent(this, SlideUpPanelActivity.class);
        startActivity(intent);
    }

    public void goGlide(View v){
        Intent intent = new Intent(this, GlideDemoActivity.class);
        startActivity(intent);
    }

    public void goRestAPI(View v) {
        Intent intent = new Intent(this, RestAPISampleActivity.class);
        startActivity(intent);
    }

    public void goPlayVideo(View v) {
        Intent intent = new Intent(this, PlayVideoActivity.class);
        startActivity(intent);
    }
}

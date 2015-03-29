package youngjee.com.examapp01;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class UseHandlerActivity extends ActionBarActivity {

    private TextView tv_Count = null;
    private static final int SEND_THREAD_INFORMATION=0;
    private static final int SEND_THREAD_STOP_MESSAGE = 1;

    private SendMessageHandler mMainHandler = null;
    private CountThread mCountThread = null;

    private Handler variousHandler;
    private Handler countdownHandler = new Handler();

    private class SendMessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch ( msg.what){
                case SEND_THREAD_INFORMATION:
                    tv_Count.setText("현재시간="+msg.arg1+"\n index="+msg.arg2+"인 \n"+msg.obj);
                    break;

                case SEND_THREAD_STOP_MESSAGE:
                    mCountThread.stopThread();
                    tv_Count.setText("Count Thread가 중지되었습니다.");
                    break;

                default:
                    break;

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_handler);

        tv_Count = (TextView) findViewById(R.id.tv_count);
        mMainHandler = new SendMessageHandler();

        variousHandler = new Handler();
    }

    public void useHandleClick(View v){
        switch (v.getId()){
            case R.id.btn_thread_start:
                mCountThread = new CountThread();
                mCountThread.start();
                break;

            case R.id.btn_thread_stop_message:
                mMainHandler.sendEmptyMessage(SEND_THREAD_STOP_MESSAGE);
                break;

            case R.id.btn_various:
                // 주석을 하나씩 풀면서 테스트를 한다.
                variousHandler.post(mRunnable);
               /* variousHandler.postAtFrontOfQueue(mRunnable);
                variousHandler.postDelayed(mRunnable, 4000);

                new Handler().post(mRunnable);
                new Handler().postAtFrontOfQueue(mRunnable);
                new Handler().postDelayed(mRunnable, 3000);

                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                         tv_Count.setText("런어블 심플 2222");
                    }
                });*/
                break;

            case R.id.btn_countDown:
                final Button btn_countDown = (Button) findViewById(R.id.btn_countDown);

                new Thread(new Runnable() {
                    int i =10;
                    @Override
                    public void run() {
                        while ( i >= 0 ){
                            countdownHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if(i==0){
                                        btn_countDown.setText("10 카운트다운");
                                    }else {
                                        btn_countDown.setText(""+i);
                                    }
                                }
                            });

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            i--;
                        }
                    }
                }).start();


            default:
                break;
        }
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            tv_Count.setText("런어블 심플");
        }
    };

    private class CountThread extends Thread implements Runnable {
        private boolean isPlay = false;

        private CountThread() {
            this.isPlay = true;
        }
        public void stopThread() {
            isPlay = !isPlay;
        }

        @Override
        public void run() {
            super.run();
            int i=0;

            while (isPlay){
                i++;
                Message msg = mMainHandler.obtainMessage();
                msg.what = SEND_THREAD_INFORMATION;
                msg.arg1 = Integer.valueOf(getNowDateTime());
                msg.arg2 =i;

                String hi = "Count Thread 가 동작하고 있습니다.";
                msg.obj = hi;

                mMainHandler.sendMessage(msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getNowDateTime() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return new SimpleDateFormat("HHmmss").format(date);

    }
}

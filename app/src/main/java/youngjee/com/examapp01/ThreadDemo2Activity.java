package youngjee.com.examapp01;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class ThreadDemo2Activity extends ActionBarActivity {

    private Handler mHandler;
    private TextView mTvNumber;
    private NumberThread mNumberThread;

    private Handler postHandler;
    private Runnable mRunnable;

    private TimerTask mTask;
    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_demo2);

        mTvNumber = (TextView) findViewById(R.id.tv_number);
        mHandler = new Handler();
    }

    @Override
    protected void onDestroy() {
        postHandler.removeCallbacks(mRunnable);
        mTimer.cancel();
        super.onDestroy();
    }

    public void onButtonClick(View v){
        Log.d("onButtonClick",v.getId()+"");
        switch (v.getId()){
            case R.id.btn_start:
                mNumberThread = new NumberThread(true);
                mNumberThread.start();
                break;
            case R.id.btn_stop:
                mNumberThread.stopThread();
                break;

            case R.id.btn_postDelayed:
                mRunnable = new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent("youngjee.alarmmanager");
                        startActivity(intent);
                    }
                };
                postHandler = new Handler();
                postHandler.postDelayed(mRunnable, 5000);
                break;

            case R.id.btn_timerTask:
                mTask = new TimerTask() {
                    @Override
                    public void run() {
                        Intent intent = new Intent("youngjee.alarmmanager");
                        startActivity(intent);
                    }
                };

                mTimer = new Timer();
                mTimer.schedule(mTask,6000);

        }
    }

    private class NumberThread  extends Thread{
        private  int i=0;
        private boolean isPlay=false;

        private NumberThread(boolean isPlay) {
            this.isPlay = isPlay;
        }

        @Override
        public void run() {
            super.run();
            while ((isPlay)){
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTvNumber.setText(""+i++);
                    }
                });
            }
        }

        public void stopThread() {
            isPlay = !isPlay;
        }
    }
}

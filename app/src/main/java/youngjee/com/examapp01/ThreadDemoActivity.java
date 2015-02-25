package youngjee.com.examapp01;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


public class ThreadDemoActivity extends ActionBarActivity implements View.OnClickListener {

    int nProgress;
    ProgressBar bar;
    Button btnNoThread;
    Button btnThread01;
    Button btnThread02;
    Button btnThread03;
    Button btnThread04;
    Handler handle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_demo);
        bar = (ProgressBar) findViewById(R.id.progressBar);
        btnNoThread = (Button) findViewById(R.id.btnNoThread);
        btnThread01 = (Button) findViewById(R.id.btnThread01);
        btnThread02 = (Button) findViewById(R.id.btnThread02);
        btnThread03 = (Button) findViewById(R.id.btnThread03);
        btnThread04 = (Button) findViewById(R.id.btnThread04);

        btnNoThread.setOnClickListener(this);
        btnThread01.setOnClickListener(this);
        btnThread02.setOnClickListener(this);
        btnThread03.setOnClickListener(this);
        btnThread04.setOnClickListener(this);

        handle = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                bar.incrementProgressBy(20);
            }
        };
    }

    @Override
    public void onClick(View v) {
        nProgress = 0;
        if (v == btnNoThread){
            /*
            * sleep한 시간만큼 UI Thread는 블럭된다.
            * 해당 시간동안 현재 실행중인 작업을 중단하거나, 해당 UI 뷰를 사용할 수 없다.
            * 블럭되는 동안 다른 버튼을 클릭하는 등 UI를 사용하면 메시지 queue에 보관된다.
            * 블럭된 UI Thread가 작업 완료 후 queue에 있는 메시지를 처리한다.
            * 5초이상 대기 메시지가 있다면 ANR을 팝업 시킨다.
            * */
            for (nProgress=0; nProgress <=100 ; nProgress+=10){
                bar.setProgress(nProgress);
                SystemClock.sleep(2000);
            }
        } else if(v == btnThread01){
            // 잘못된 thread. Worker Thread에서  UI객체를 직접 update함.
            /*
            * 코드를 실행 시켜 보면 2초씩 10%씩 순차적으로 100%까지 증가하는 정상적인 progress bar가 구현된 것을 볼 수 있다.
            * 또 전 예제와 다르게 UI가 block되지 않아 다른 UI이벤트에 대해서도 즉각 반응함을 볼 수 있다.
            * 하지만 위의 예제는 잠재적으로 심각한 오류를 일으킬 소지를 가지고 있다.
            * 안드로이드 UI 위젯들은 스레드 세이프 (Threadsafe)하게 디자인 되지 않았기 때문이다.
            * 만약 두 개 이상의 스레드가 동시에 UI위젯 자원에 접근하여 이를 조작 하려고 한다면 개발자가 예상하지 못한 결과가 일어날 수도 있다.
            * 여기서 사용된 ProgressBar객체는 별 문제 없이 작동하지만, UI스레드에서 생성된 TextView가 worker 스레드에서 직접 컨트롤 되면,
            * run-time exception이 발생하며 exception 처리가 안되어 있을 경우 어플리케이션이 강제 종료 될 수도 있다.
            * */
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            for (nProgress=0; nProgress <=100 ; nProgress+=10){
                                try {
                                    bar.setProgress(nProgress);
                                } catch (Exception e) {
                                    Log.w("Thread 01 Exceptioin", e.toString());
                                }
                                SystemClock.sleep(2000);
                            }
                        }
                    }
            ).start();

            /*
            * 올바른 스레드 구현방법
            *  worker 스레드가 ui위젯에 적용할 작업을 메시지 queue에 추가함
            *  ui 스레드가 메시지를 dispatch에 해당 ui위젯에 메시지를 전달함
            *  ui 위젯은 메시지 대로 자신의 상태를 update
            * */


         } else if (v == btnThread02){
            /*
            * using View.post() method
            * 새로 생성된 work 스레드의 역할은 긴 시간이 필요한 작업을 처리 후 특정자원의 상태 update 명령을 메시지 큐에 등록하는 것까지이고,
            * 저장된 메시지가 UI위젯에 전달 되는 시기는 전적으로 UI스레드의 상태에 따라 결정
            * 우선순위가 높은 작업의 메시지가 발생하면 저장된 메시지의 앞으로 등록시켜 우선적으로 처리함.
            * */
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (nProgress=0; nProgress <=100 ; nProgress+=10){
                        bar.post(new Runnable() {
                            @Override
                            public void run() {
                                bar.setProgress(nProgress);
                            }
                        });
                        SystemClock.sleep(2000);
                    }
                }
            } ).start();

        } else if (v==btnThread03) {
            /*
            * using Activity.runOnUiThread() method
            * 해당 메소드는 자신이 어디서 call 되었는지 따라 처리방법이 상이하다.
            * UI스레드 내부에서 call 되었으면 인자로 제공된 Runnnable 이 바로 실행
            * 메시지 큐에 등록 시켜 UI 스레드의 스케쥴에 맞춰 실행
            * */
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (nProgress = 0; nProgress <= 100; nProgress += 10) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                bar.setProgress(nProgress);
                            }
                        });
                        SystemClock.sleep(2000);
                    }
                }
            }).start();

        } else if (v==btnThread04){
            /*
            * Handler 객체 이용
            * */
            bar.setProgress(0);
            Thread worker = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (nProgress = 0; nProgress <= 100; nProgress += 10) {
                        handle.sendMessage(handle.obtainMessage());
                        SystemClock.sleep(2000);
                    }
                }
            });
            worker.start();
        }
    }
}

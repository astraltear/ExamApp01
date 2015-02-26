package youngjee.com.examapp01;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AsyncTaskDemoActivity extends ActionBarActivity implements View.OnClickListener {

    ProgressBar progressBar;
    TextView textResult;
    Button btnExecuteTask;
    TextView tv_asynctaskinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_demo);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textResult = (TextView) findViewById(R.id.textResult);
        btnExecuteTask = (Button) findViewById(R.id.btnExecuteTask);
        tv_asynctaskinfo = (TextView) findViewById(R.id.tv_asynctaskinfo);

        btnExecuteTask.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        new DoComplecatedJob().execute("987", "1589", "687", "399", "1722", "50");
    }

    /*
    * AsyncTask클래스는 항상 Subclassing 해서 사용해야 함.인스턴스는 항상 UI 스레드에서 생성
    * 작업에 사용할 data의 자료형 : String
    * 작업 진행 표시를 위해 사용할 인자 : Integer
    * 작업의 결과를 표현할 자료형 : Long
    * http://cfile4.uf.tistory.com/image/14798B0F4B987FDE48907E
    * 또, AsyncTask 클래스는 background 작업의 시작과 background 작업 중 진행정보의 UI스레드 표현을 위해 다음과 같은 메소드를 제공한다.
    * final AsyncTask<…> execute(Params… params): Background 작업을 시작한다.
    * 꼭 UI스레드에서 호출하여야 함. 가변인자를 받아들임으로 임의의 개수의 인자를 전달할 수 있으며, 인자들은 doInBackground(…) 메소드로 전달된다.
    * final void publishProgress(Progress... values): Background 작업 수행 중 작업의 진행도를 UI 스레드에 전달 함. doInBackground(…)메소드 내부에서만 호출.
    * 마지막으로 AsyncTask 사용해 background작업을 구현 시 꼭 지켜야 하는 사항들이다.
    * AsyncTask클래스는 항상 subclassing 하여 사용하여야 한다.
    * AsyncTask 인스턴스는 항상 UI 스레드에서 생성한다.
    * AsyncTask:execute(…) 메소드는 항상 UI 스레드에서 호출한다.
    * AsyncTask:execute(…) 메소드는 생성된 AsyncTask 인스턴스 별로 꼭 한번만 사용 가능하다.
    * 같은 인스턴스가 또 execute(…)를 실행하면 exception이 발생하며, 이는 AsyncTask:cancel(…) 메소드에 의해 작업완료 되기 전 취소된 AsyncTask 인스턴스라도 마찬가지이다.
    * 그럼으로 background 작업이 필요할 때마다 new 연산자를 이용해 해당 작업에 대한 AsyncTask 인스턴스를 새로 생성해야 한다.
    * AsyncTask의 callback 함수 onPreExecute(), doInBackground(…), onProgressUpdate(…), onPostExecute(…)는 직접 호출 하면 안 된다. (꼭 callback으로만 사용)
    * */
    private class DoComplecatedJob extends AsyncTask<String, Integer, Long>{

        @Override
        protected void onPreExecute() {
            textResult.setText("Background 작업시작");
            super.onPreExecute();
        }

//        UI 스레드에서 AsyncTask객체.execute(...) 명령으로 실행되는 callback
        @Override
        protected Long doInBackground(String... params) {
            long totalTimeSpent =0;
            int numberOfParmas = params.length;

            for (int i =0 ; i<numberOfParmas;i++){
                SystemClock.sleep(new Integer(params[i]));
                totalTimeSpent += new Long(params[i]);

                int result = (int) (((1+i)/(float)numberOfParmas)*100)  ;
                Log.d("result", "===>"+result);

                // onProgressUpdate callback을 호출 해 background 작업의 실행결과를 UI에 표현함
                publishProgress(result);
            }

            return totalTimeSpent;
        }

        // doInBackground 에서 publishProgress 를 사용하면 자동 호출되는 callback
        // 이곳에서 background 작업 진행 상황을 UI에 표현함

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
            tv_asynctaskinfo.setText(""+values[0]);
        }

        // doInBackground 가 완료되면 자동으로 실행되는 callback
       // 이곳에서 doInBackground 가 리턴된 정보를 UI위젯에 표시 하는 등의 작업을 수행

        @Override
        protected void onPostExecute(Long aLong) {
            textResult.setText("Background 작업에 걸린 총 시간:"+new Long(aLong).toString()+"m 초");
        }

        // AsyncTask.cancel(boolean) 메소드가 true인자로 실행되면 호출되는 콜백 background 작업이 취소될 때 꼭 해야할 작업은 여기에 구현함

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
 }

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
    * AsyncTask클래스는 항상 Subclassing 해서 사용해야 함
    * 작업에 사용할 data의 자료형 : String
    * 작업 진행 표시를 위해 사용할 인자 : Integer
    * 작업의 결과를 표현할 자료형 : Long
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

                // onProgressUpdate callback을 호출 해 background 작업의 실행결과를 UI에 표현함
                int result = (int) (((1+i)/(float)numberOfParmas)*100)  ;

                Log.d("result", "===>"+result);

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

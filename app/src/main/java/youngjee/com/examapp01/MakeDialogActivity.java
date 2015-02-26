package youngjee.com.examapp01;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;


public class MakeDialogActivity extends ActionBarActivity {


    private int ID_2BUTTON_DIALOG=2;
    private int ID_3BUTTON_DIALOG=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_dialog);

        Button button = (Button) findViewById(R.id.btn_dialog);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {

                                          AlertDialog.Builder builder = new AlertDialog.Builder(MakeDialogActivity.this);
                                          builder.setTitle("단순한");
                                          builder.setMessage("very simple");
                                          builder.setIcon(R.drawable.ic_launcher);

                                          builder.setPositiveButton("ONe", null);
                                          builder.setNeutralButton("Two", null);
                                          builder.setNegativeButton("Three", null);

                                          builder.show();


                                      }
                                  });

        Button button1 = (Button) findViewById(R.id.btn_dialog2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(ID_2BUTTON_DIALOG);
            }
        });

        Button button2 = (Button) findViewById(R.id.btn_dialog3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(ID_3BUTTON_DIALOG);
            }
        });


    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        String str = "";

        switch (id){
            case 2 : {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("what is it");
                builder.setMessage("message");
                builder.setCancelable(false);

                builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MakeDialogActivity.this.dismissDialog(2);
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                dialog = builder.create();
                break;
            }

            case 3 : {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("List ICon");
                builder.setIcon(R.drawable.ic_launcher);

                String [] m_singer = {"Tiara","BigBang","APINK","GirlsDay","FOUR MINUTE","TEN","NINE","HALLI","GALLI","HAPPY","TONG","INDEX"};
                //builder.setItems(m_singer, new DialogInterface.OnClickListener() {
//                builder.setSingleChoiceItems(m_singer,0, new DialogInterface.OnClickListener() {
            /*    @Override
                public void onClick(DialogInterface dialog, int which) {

                }*/
                builder.setMultiChoiceItems(m_singer, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                    }
                });

                builder.setNeutralButton("STAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                dialog=builder.create();
                break;

            }

            default:
                dialog=null;
        }
        return  dialog;
    }

    private Dialog mMainDialog;
    private CheckBox[] mCheckBoxs;

    public void onDialog4(View view) {
        mMainDialog = createDialog();
        mMainDialog.show();
    }

    private AlertDialog createDialog() {
        final View innerView = getLayoutInflater().inflate(R.layout.dialog_layout, null);
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("Title");
        ab.setView(innerView);

        mCheckBoxs = new CheckBox[]{
                (CheckBox) innerView.findViewById(R.id.cb_check_all),
                (CheckBox) innerView.findViewById(R.id.cb_01),
                (CheckBox) innerView.findViewById(R.id.cb_02),
                (CheckBox) innerView.findViewById(R.id.cb_03),
                (CheckBox) innerView.findViewById(R.id.cb_04)
        };

        ab.setPositiveButton("확인",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setDismiss(mMainDialog);
            }
        });

        ab.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setDismiss(mMainDialog);
            }
        });

        mCheckBoxs[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allChecked(mCheckBoxs, mCheckBoxs[0].isChecked());
            }
        });

        return ab.create();
    }

    private void allChecked(CheckBox[] mCheckBoxs, boolean checked) {
        for (CheckBox checkBox : mCheckBoxs){
            checkBox.setChecked(checked);
        }
    }

    private void setDismiss(Dialog mMainDialog) {
        if(mMainDialog != null && mMainDialog.isShowing()){
            mMainDialog.dismiss();
        }
    }

    private Handler mHandler;
    private ProgressDialog progressDialog;

    public void onDialog5(View view){
        mHandler = new Handler();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog = ProgressDialog.show(MakeDialogActivity.this, "", "기다려 >.<", true);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if(progressDialog != null && progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }, 3000);
            }
        });
    }

    private AlertDialog basicDialog = null;
    public void onBasicDialog(View view) {
        basicDialog = createOtherBasicDialog();
        basicDialog.show();
    }

    private AlertDialog createOtherBasicDialog() {
        AlertDialog.Builder abBuilder = new AlertDialog.Builder(this);
        abBuilder.setTitle("베이직 다이얼로그");
        abBuilder.setMessage("베이지 다이얼로그 내용");
        abBuilder.setCancelable(false);
        abBuilder.setIcon(getResources().getDrawable(R.drawable.ic_menu_search));

        abBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setBasicDismiss(basicDialog);
            }
        });

        abBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setBasicDismiss(basicDialog);
            }
        });

        return abBuilder.create();
    }

    private void setBasicDismiss(AlertDialog basicDialog) {
        if (basicDialog != null && basicDialog.isShowing()){
            basicDialog.dismiss();
        }
    }

    public void onInflateDialog(View view){
        basicDialog = createOtherInflateDialog();
        basicDialog.show();
    }

    private AlertDialog createOtherInflateDialog() {
        final View innerView = getLayoutInflater().inflate(R.layout.dialog_layout, null);
        AlertDialog.Builder abBuilder = new AlertDialog.Builder(this);
        abBuilder.setTitle("InflateDialog");
        abBuilder.setView(innerView);

        abBuilder.setPositiveButton("Activity 자체 종료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //setBasicDismiss(basicDialog);
                MakeDialogActivity.this.finish();
            }
        });

        abBuilder.setNegativeButton("Activity 자체 종료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //setBasicDismiss(basicDialog);
                MakeDialogActivity.this.finish();
            }
        });

        return abBuilder.create();
    }

    private Handler otherHandle;
    private ProgressDialog otherProgressDialog;

    public void onOtherProgress(View v) {
        otherHandle = new Handler();

        otherProgressDialog = new ProgressDialog(this);
        otherProgressDialog.setMessage("Other!!!!!!!!");
        otherProgressDialog.setCancelable(false); // back button 활성화 여부

        otherProgressDialog.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (otherProgressDialog != null && otherProgressDialog.isShowing()){
                    otherProgressDialog.dismiss();
                }
            }
        });

        otherProgressDialog.show();

        otherHandle.postDelayed(mRunner, 5000);

    }

    private Runnable mRunner = new Runnable() {
        @Override
        public void run() {
            if (otherProgressDialog != null && otherProgressDialog.isShowing()){
                otherProgressDialog.dismiss();
            }
        }
    };



}

package youngjee.com.examapp01;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


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
}

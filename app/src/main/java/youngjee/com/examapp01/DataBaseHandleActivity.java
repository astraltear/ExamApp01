package youngjee.com.examapp01;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class DataBaseHandleActivity extends ActionBarActivity implements View.OnClickListener {

    EditText edtInsName, edtId, edtName, edtDelid;
    DataBaseDBHandle dbHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base_handle);

        Button btnIns = (Button)findViewById(R.id.btnIns);
        Button btnSelectAll = (Button)findViewById(R.id.btnSelectAll);
        Button btnSelect = (Button)findViewById(R.id.btnSelect);
        Button btnSelectByName = (Button)findViewById(R.id.btnSelectByName);
        Button btnDel = (Button)findViewById(R.id.btnDel);

        btnIns.setOnClickListener(this);
        btnSelectAll.setOnClickListener(this);
        btnSelect.setOnClickListener(this);
        btnSelectByName.setOnClickListener(this);
        btnDel.setOnClickListener(this);

        edtInsName = (EditText)findViewById(R.id.edtInsName);
        edtId = (EditText)findViewById(R.id.edtId);
        edtName = (EditText)findViewById(R.id.edtName);
        edtDelid = (EditText)findViewById(R.id.edtDelid);

    }

    @Override
    public void onClick(View v) {

        try {
            dbHandle = DataBaseDBHandle.open(this);

            if(v.getId() == R.id.btnIns){
                if(edtInsName.getText().toString().equals("")){
                    Toast.makeText(this,"input add name",Toast.LENGTH_LONG).show();
                    return;
                } else {
                    long re = dbHandle.insert(edtInsName.getText().toString());

                    if(re <= 0){
                        Toast.makeText(this,"add fail",Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this,"add success",Toast.LENGTH_LONG).show();
                        edtInsName.setText("");
                    }
                }
            }else if(v.getId() == R.id.btnSelectAll) {
                Intent intent = new Intent(this, ResultList.class);
                intent.putExtra("command", "selectall");
                startActivity(intent);

            }else if(v.getId() == R.id.btnSelect) {
                Cursor cursor = dbHandle.selectId(edtId.getText().toString());
                int id = cursor.getInt(0);
                String name = cursor.getString(1);

                Toast.makeText(this,id+":"+name,Toast.LENGTH_LONG).show();
                cursor.close();
                edtId.setText("");

            }else if(v.getId() == R.id.btnSelectByName) {
                Intent intent = new Intent(this,ResultList.class);
                intent.putExtra("command", "selectname");
                intent.putExtra("name", edtName.getText().toString());
                startActivity(intent);

            }else if(v.getId() == R.id.btnDel) {
                if(edtDelid.getText().toString().equals("")){
                    Toast.makeText(this,"input del number",Toast.LENGTH_LONG).show();
                    return;
                }else {
                    long re = dbHandle.delete(edtDelid.getText().toString());

                    if(re <= 0){
                        Toast.makeText(this,"del fail",Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this,"del success",Toast.LENGTH_LONG).show();
                    }
                }
            }

            dbHandle.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

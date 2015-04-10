package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class ArraySpinnerActivity extends ActionBarActivity {

    private Spinner mSpinner = null;
    private ArrayAdapter<String> mSpinnerAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_spinner);

        mSpinner = (Spinner) findViewById(R.id.spinner);
        mSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,
                (String[])getResources().getStringArray(R.array.array_list) );

        mSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mSpinnerAdapter);

    }
}

package youngjee.com.examapp01;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;


public class DateTimerPickerActivity extends ActionBarActivity {
    Calendar calendar = Calendar.getInstance();
    TextView txtLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_timer_picker);

        Button btnSetDate = (Button) findViewById(R.id.btnSetDate);
        Button btnSetTime = (Button) findViewById(R.id.btnSetTime);

        btnSetDate.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DateTimerPickerActivity.this,
                        dateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnSetTime.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(DateTimerPickerActivity.this,
                        timeSetListener,
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                        ).show();
            }
        });

        txtLabel = (TextView) findViewById(R.id.txtTime);
        setLable();
    }

    private void setLable(){
        txtLabel.setText(DateFormat.getDateTimeInstance().format(calendar.getTime()));
    }

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(year,monthOfYear,dayOfMonth);
            setLable();
        }
    };

    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
            calendar.set(Calendar.MINUTE,minute);
            setLable();
        }
    };

}

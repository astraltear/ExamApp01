package youngjee.com.examapp01;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;


public class AutoCompleteTextActivity extends ActionBarActivity {

    private static String [] ITEMS = {"APPLE","BANANA","WATERMELON","ORANGE", "GRAPE","CHERRY"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_text);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ITEMS);

        MultiAutoCompleteTextView mTv = (MultiAutoCompleteTextView) findViewById(R.id.et_search);
        mTv.setAdapter(arrayAdapter);
        mTv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        TextView textView = (TextView) findViewById(R.id.textView5);
        SpannableStringBuilder sp = new SpannableStringBuilder("안녕하세요.반갑습니다. Hello Nice to Meet You");
        sp.setSpan(new ForegroundColorSpan(Color.CYAN), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(sp);
    }

}

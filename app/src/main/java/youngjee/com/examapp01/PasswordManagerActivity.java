package youngjee.com.examapp01;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/*
작성하다 중지. 쓸데 없이 복잡함

 */

public class PasswordManagerActivity extends ActionBarActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_manager);

        editText = (EditText) findViewById(R.id.editText1);

        findViewById(R.id.btn_chgpwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PasswordManagerActivity.this, Password.class);
                intent.putExtra("nextActivity", "youngjee.com.examapp01.Profile");
                intent.putExtra("password", editText.getText().toString());
                intent.putExtra("mode", 0);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_initpwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PasswordManagerActivity.this, Password.class);
                intent.putExtra("nextActivity", "youngjee.com.examapp01.Profile");
                intent.putExtra("password", editText.getText().toString());
                intent.putExtra("mode", 1);
                startActivity(intent);
            }
        });


        findViewById(R.id.btn_checkpwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PasswordManagerActivity.this, Password.class);
                intent.putExtra("nextActivity", "youngjee.com.examapp01.Profile");
                intent.putExtra("password", editText.getText().toString());
                intent.putExtra("mode", 2);
                startActivity(intent);
            }
        });
    }
}

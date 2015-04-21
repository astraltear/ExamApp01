package youngjee.com.examapp01;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ViewFlipper;


public class Password extends ActionBarActivity {


    Intent nextActivity;

    int currentMode = 2;
    int initMode = 2;

    String passwordString;
    String currentPassword;
    int passwordLength = 9999;

    ViewFlipper passwordFlipper;

    EditText passwordForm, passwordConfirmForm;

    TranslateAnimation pushLeftIn, pushLeftOut, shakeAni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        init();
        initAnimation();
    }

    Runnable passwordRunnable = new Runnable() {
        @Override
        public void run() {
            checkPassword();
        }
    };

    private void checkPassword(){
        switch (currentMode){
            case 0:
                if(passwordString.equals(passwordForm.getText().toString())){
                    goToNextPhase();
                } else {
                    passwordForm.startAnimation(shakeAni);
                }
                break;
            case 1:
                goToNextPhase();
                break;

            case 2:
                EditText currentForm = passwordConfirmForm;
                if (initMode == 2) currentForm = passwordForm;

                if (currentPassword.equals(currentForm.getText().toString())){
                    goToNextPhase();
                } else {
                    passwordForm.startAnimation(shakeAni);
                }
        }

        passwordForm.setText("");
        passwordConfirmForm.setText("");
    }

    private void goToNextPhase(){
        switch (currentMode){
            case 0:
                currentMode = 0;
                break;
            case 1:
                currentPassword = passwordForm.getText().toString();
                currentMode = 2;

                passwordFlipper.setAnimation(pushLeftIn);
                passwordFlipper.setOutAnimation(pushLeftOut);
                passwordFlipper.showPrevious();
                break;

            case 2:
                finish();
                nextActivity.putExtra("resultPassword", currentPassword);
                startActivity(nextActivity);
                break;
        }
    }


    public void init() {
        Intent intent = getIntent();
        String nextActivityClassString = intent.getStringExtra("nextActivity");
        nextActivity = new Intent();
        nextActivity.setClassName(Password.this, nextActivityClassString);

        initMode = intent.getIntExtra("mode", 2);
        passwordString = intent.getStringExtra("password");

        currentMode = initMode;
        currentPassword = passwordString;
        passwordLength = passwordString.length();

        passwordFlipper = (ViewFlipper) findViewById(R.id.password_flipper);

        passwordForm = (EditText) findViewById(R.id.password);
        passwordForm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (passwordForm.getText().toString().length() == passwordLength) {
                    Handler passwordHandler = new Handler();
                    passwordHandler.postDelayed(passwordRunnable, 200);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        passwordConfirmForm = (EditText) findViewById(R.id.password_confirm);
        passwordConfirmForm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (passwordConfirmForm.getText().toString().length() == passwordLength) {
                    Handler passwordHandler = new Handler();
                    passwordHandler.postDelayed(passwordRunnable, 200);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void initAnimation(){
        pushLeftIn = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,1.0f,
                TranslateAnimation.RELATIVE_TO_SELF,0.0f,
                TranslateAnimation.RELATIVE_TO_SELF,0.0f,
                TranslateAnimation.RELATIVE_TO_SELF,0.0f
        );
        pushLeftIn.setDuration(200);
        pushLeftIn.setFillAfter(true);

        pushLeftOut = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,0.0f,
                TranslateAnimation.RELATIVE_TO_SELF,-1.0f,
                TranslateAnimation.RELATIVE_TO_SELF,0.0f,
                TranslateAnimation.RELATIVE_TO_SELF,0.0f
        );
        pushLeftOut.setDuration(200);
        pushLeftOut.setFillAfter(true);

        shakeAni = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,0.0f,
                TranslateAnimation.RELATIVE_TO_SELF,0.05f,
                TranslateAnimation.RELATIVE_TO_SELF,0.0f,
                TranslateAnimation.RELATIVE_TO_SELF,0.0f
        );
        shakeAni.setDuration(300);
        shakeAni.setInterpolator(new CycleInterpolator(2.0f));
    }
}

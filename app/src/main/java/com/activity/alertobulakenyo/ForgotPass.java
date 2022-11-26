package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class ForgotPass extends AppCompatActivity {

    TextInputLayout tilFPemail;
    EditText etFPemail;
    Button btnSend, btnBackLogin;
    TextView tvResend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_forgot_pass);

        tilFPemail = (TextInputLayout) findViewById (R.id.tilFPemail);
        etFPemail = (EditText) findViewById (R.id.etFPemail);

        btnSend = (Button) findViewById (R.id.btnSend);
        btnBackLogin = (Button) findViewById (R.id.btnBackLogin);

        tvResend = (TextView) findViewById (R.id.tvResend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etFPemail.getText().toString()))
                {
                    etFPemail.setError("Required!");
                    return;
                }

                new CountDownTimer(59000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        tvResend.setText("Code sent. Resend code in " + millisUntilFinished / 1000 + " seconds.");
                    }

                    public void onFinish() {
                        tvResend.setText("Resend a new code");
                        btnSend.setText("RESEND");
                    }
                }.start();
            }
        });

        btnBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPass.this, login.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(ForgotPass.this, login.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class Verification extends AppCompatActivity {

    TextInputLayout tilOTP;
    EditText etOTP;
    Button btnSend, btnBack;
    TextView tvResend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_verification);

        tilOTP = (TextInputLayout) findViewById (R.id.tilOTP);
        etOTP = (EditText) findViewById (R.id.etOTP);

        btnSend = (Button) findViewById (R.id.btnSend);
        btnBack = (Button) findViewById (R.id.btnBack);

        tvResend = (TextView) findViewById (R.id.tvResend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etOTP.getText().toString()))
                {
                    etOTP.setError("Required!");
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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // balik login

                Intent intent = new Intent(Verification.this, login.class);
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
        Intent intent = new Intent(Verification.this, ForgotPass.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
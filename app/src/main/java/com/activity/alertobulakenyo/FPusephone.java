package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FPusephone extends AppCompatActivity {

    EditText etFPnumber;
    Button btnSend, btnProceed, btnBack;
    TextView tvResend, tvUseEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_fpusephone);

        etFPnumber = (EditText) findViewById (R.id.etFPnumber);

        btnSend = (Button) findViewById (R.id.btnSend);
        btnProceed = (Button) findViewById (R.id.btnProceed);
        btnBack = (Button) findViewById (R.id.btnBack);

        tvResend = (TextView) findViewById (R.id.tvResend);
        tvUseEmail = (TextView) findViewById (R.id.tvUseEmail);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etFPnumber.getText().toString()))
                {
                    etFPnumber.setError("This cannot be empty!");
                    return;
                }

                new CountDownTimer(59000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        tvResend.setText("Resend code in " + millisUntilFinished / 1000 + " seconds");
                    }

                    public void onFinish() {
                        tvResend.setText("Resend code");
                    }
                }.start();
            }
        });

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etFPnumber.getText().toString()))
                {
                    etFPnumber.setError("This cannot be empty!");
                    return;
                }

                Intent intent = new Intent(FPusephone.this, Verification.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        tvUseEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FPusephone.this, Verification.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FPusephone.this, login.class);
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
        Intent intent = new Intent(FPusephone.this, login.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
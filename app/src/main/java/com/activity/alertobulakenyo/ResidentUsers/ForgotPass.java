package com.activity.alertobulakenyo.ResidentUsers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.activity.alertobulakenyo.R;
import com.activity.alertobulakenyo.login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPass extends AppCompatActivity {

    TextInputLayout tilFPemail;
    EditText etFPemail;
    Button btnSend, btnBack;
    TextView tvResend;
    ProgressBar progressBar;

    private FirebaseAuth fAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_forgot_pass);

        progressBar = (ProgressBar) findViewById (R.id.progressBar);

        tilFPemail = (TextInputLayout) findViewById (R.id.tilFPemail);
        etFPemail = (EditText) findViewById (R.id.etFPemail);

        btnSend = (Button) findViewById (R.id.btnSend);
        btnBack = (Button) findViewById (R.id.btnBack);

        tvResend = (TextView) findViewById (R.id.tvResend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etFPemail.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(ForgotPass.this, "Please enter your registered email.", Toast.LENGTH_SHORT).show();
                    etFPemail.setError("Email is required!");
                    etFPemail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(ForgotPass.this, "Please enter a valid email.", Toast.LENGTH_SHORT).show();
                    etFPemail.setError("Email is invalid!");
                    etFPemail.requestFocus();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    resetPassword(email);
                }

//                new CountDownTimer(59000, 1000) {
//
//                    public void onTick(long millisUntilFinished) {
//                        tvResend.setText("Code sent. Resend code in " + millisUntilFinished / 1000 + " seconds.");
//                    }
//
//                    public void onFinish() {
//                        tvResend.setText("Resend a new code");
//                        btnSend.setText("RESEND");
//                    }
//                }.start();
            }
        });
        
        

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPass.this, com.activity.alertobulakenyo.login.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);
            }
        });
    }

    private void resetPassword(String email) {
        fAuth = FirebaseAuth.getInstance();
        fAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPass.this, "Please check your inbox for password reset link.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), login.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(ForgotPass.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        // balik login

        Intent intent = new Intent(ForgotPass.this, com.activity.alertobulakenyo.login.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
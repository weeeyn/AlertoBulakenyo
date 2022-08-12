package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Verification extends AppCompatActivity {

    EditText etCode1, etCode2, etCode3, etCode4;
    Button btnVerify, btnBack;
    TextView tvNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_verification);

        btnVerify = (Button) findViewById (R.id.btnVerify);
        btnBack = (Button) findViewById (R.id.btnBack);
        tvNote = (TextView) findViewById (R.id.tvNote);





        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etCode1.getText().toString()))
                {
                    tvNote.setText("There is an error in the verification code.");
                    return;
                }
                if (TextUtils.isEmpty(etCode2.getText().toString()))
                {
                    tvNote.setText("There is an error in the verification code.");
                    return;
                }
                if (TextUtils.isEmpty(etCode3.getText().toString()))
                {
                    tvNote.setText("There is an error in the verification code.");
                    return;
                }
                if (TextUtils.isEmpty(etCode3.getText().toString()))
                {
                    tvNote.setText("There is an error in the verification code.");
                    return;
                }

                Intent intent = new Intent(Verification.this, ResetPass.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class ResetPass extends AppCompatActivity {

    EditText etResetNewPass, etResetConPass;
    TextView tvPassNote;
    Button btnResetPass, btnBackVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_reset_pass);

        etResetNewPass = (EditText) findViewById (R.id.etResetNewPass);
        etResetConPass = (EditText) findViewById (R.id.etResetConPass);

        tvPassNote = (TextView) findViewById (R.id.tvPassNote);

        btnResetPass = (Button) findViewById (R.id.btnResetPass);
        btnBackVer = (Button) findViewById (R.id.btnBackVer);

        btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            /*

                if (TextUtils.isEmpty(etResetNewPass.getText().toString()))
                {
                    tvPassNote.setText("Please enter new password!");
                    return;
                }
                if (TextUtils.isEmpty(etResetConPass.getText().toString()))
                {
                    tvPassNote.setText("Please enter new password!");
                    return;
                }

             */

                Intent intent = new Intent(ResetPass.this, login.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        btnBackVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPass.this, Verification.class);
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
        Intent intent = new Intent(ResetPass.this, Verification.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
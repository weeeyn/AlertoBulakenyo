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
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class login extends AppCompatActivity {

    TextInputLayout tilLoginEmail, tilLoginPass;
    EditText etLoginEmail, etLoginPass;
    Button btnLogin;
    TextView tvForgotPass, tvSignup;
    ImageButton btnGoogle, btnFB, btnTwt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_login);

        etLoginEmail = (EditText) findViewById (R.id.etLoginEmail);
        etLoginPass = (EditText) findViewById (R.id.etLoginPass);

        btnLogin = (Button) findViewById (R.id.btnLogin);

        tvForgotPass = (TextView) findViewById (R.id.tvForgotPass);
        tvSignup = (TextView) findViewById (R.id.tvSignup);

        btnGoogle = (ImageButton) findViewById (R.id.btnGoogle);
        btnFB = (ImageButton) findViewById (R.id.btnFB);
        btnTwt = (ImageButton) findViewById (R.id.btnTwt);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*

                if (TextUtils.isEmpty(etLoginEmail.getText().toString()))
                {
                    etLoginEmail.setError("Required!");
                    return;
                }
                if (TextUtils.isEmpty(etLoginPass.getText().toString()))
                {

                    return;
                }

                 */

                Intent intent = new Intent(login.this, completeInfo.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, ForgotPass.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnTwt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
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
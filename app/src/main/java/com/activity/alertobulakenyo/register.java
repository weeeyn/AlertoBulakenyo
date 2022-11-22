package com.activity.alertobulakenyo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class register extends AppCompatActivity {

    TextInputLayout tilRegFname, tilRegLname, tilRegUsername, tilContact, tilHouse, tilCity, tilBrgy, tilProvince, tilRegEmail, tilRegPass, tilRegConPass;
    EditText etRegFname, etRegLname, etRegUsername, etContact, etHouse, etRegEmail, etRegPass, etRegConPass;
    AutoCompleteTextView actCity, actBrgy, actProvince;
    CheckBox cbAgree1, cbAgree2;
    Button btnSignup;
    TextView tvTerms, tvPrivacy, tvLogin;
    int cb1 = 0, cb2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_register);

        tilRegFname = (TextInputLayout) findViewById (R.id.tilRegFname);
        tilRegLname = (TextInputLayout) findViewById (R.id.tilRegLname);
        tilRegUsername = (TextInputLayout) findViewById (R.id.tilRegUsername);
        tilContact = (TextInputLayout) findViewById (R.id.tilContact);
        tilHouse = (TextInputLayout) findViewById (R.id.tilHouse);
        tilCity = (TextInputLayout) findViewById (R.id.tilCity);
        tilBrgy = (TextInputLayout) findViewById (R.id.tilBrgy);
        tilProvince = (TextInputLayout) findViewById (R.id.tilProvince);
        tilRegEmail = (TextInputLayout) findViewById (R.id.tilRegEmail);
        tilRegPass = (TextInputLayout) findViewById (R.id.tilRegPass);
        tilRegConPass = (TextInputLayout) findViewById (R.id.tilRegConPass);

        etRegFname = (EditText) findViewById (R.id.etRegFname);
        etRegLname = (EditText) findViewById (R.id.etRegLname);
        etRegUsername = (EditText) findViewById (R.id.etRegUsername);
        etContact = (EditText) findViewById (R.id.etContact);
        etHouse = (EditText) findViewById (R.id.etHouse);
        etRegEmail = (EditText) findViewById (R.id.etRegEmail);
        etRegPass = (EditText) findViewById (R.id.etRegPass);
        etRegConPass = (EditText) findViewById (R.id.etRegConPass);

        actBrgy = (AutoCompleteTextView) findViewById (R.id.actBrgy);
        actCity = (AutoCompleteTextView) findViewById (R.id.actCity);
        actProvince = (AutoCompleteTextView) findViewById (R.id.actProvince);

        cbAgree1 = (CheckBox) findViewById (R.id.cbAgree1);
        cbAgree2 = (CheckBox) findViewById (R.id.cbAgree2);
        btnSignup = (Button) findViewById (R.id.btnSignup);

        tvTerms = (TextView) findViewById (R.id.tvTerms);
        tvPrivacy = (TextView) findViewById (R.id.tvPrivacy);
        tvLogin = (TextView) findViewById (R.id.tvLogin);

        tvTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cbAgree1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAgree1.isChecked()) {
                    cb1 = 1;
                }
                else {
                    cb1 = 0;
                }
            }
        });

        cbAgree2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAgree2.isChecked()) {
                    cb2 = 1;
                }
                else {
                    cb2 = 0;
                }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*

                if (TextUtils.isEmpty(etRegFname.getText().toString()))
                {
                    etRegFname.setError("Required!");
                    return;
                }
                if (TextUtils.isEmpty(etRegLname.getText().toString()))
                {
                    etRegLname.setError("Required!");
                    return;
                }
                if (TextUtils.isEmpty(etRegUsername.getText().toString()))
                {
                    etRegUsername.setError("Required!");
                    return;
                }
                if (TextUtils.isEmpty(etRegEmail.getText().toString()))
                {
                    etRegEmail.setError("Required!");
                    return;
                }
                if (TextUtils.isEmpty(etRegPass.getText().toString()))
                {
                    etRegPass.setError("Required!");
                    return;
                }
                if (TextUtils.isEmpty(etRegConPass.getText().toString()))
                {
                    etRegConPass.setError("Required!");
                    return;
                }

                 */

                if ( (cb1 == 1) && (cb2 == 1) )
                {
                    Intent intent = new Intent(register.this, login.class);
                    startActivity(intent);

                    Toast.makeText(register.this, "You have successfully registered to Alerto Bulakenyo. You can now Log in.", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(register.this, "Please read and agree to the Terms of Service and Privacy Policy.", Toast.LENGTH_LONG).show();
                }

            }
        });

        tvTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, Terms.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        tvPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, Privacy.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, login.class);
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
        Intent intent = new Intent(register.this, login.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
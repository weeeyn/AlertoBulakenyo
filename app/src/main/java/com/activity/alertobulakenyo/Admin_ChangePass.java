package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Admin_ChangePass extends AppCompatActivity {

    TextInputLayout tilOldPass, tilNewPass, tilConNewPass;
    EditText etOldPass, etNewPass, etConNewPass;
    Button btnChangePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_change_pass);

        tilOldPass = (TextInputLayout) findViewById (R.id.tilOldPass);
        tilNewPass = (TextInputLayout) findViewById (R.id.tilNewPass);
        tilConNewPass = (TextInputLayout) findViewById (R.id.tilConNewPass);

        etOldPass = (EditText) findViewById (R.id.etOldPass);
        etNewPass = (EditText) findViewById (R.id.etNewPass);
        etConNewPass = (EditText) findViewById (R.id.etConNewPass);

        btnChangePass = (Button) findViewById (R.id.btnChangePass);

        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Admin_ChangePass.this, "Password Updated!", Toast.LENGTH_SHORT).show();

                finish();
                finishActivity(107);
                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);

            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Admin_Settings.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Admin_EditHotline extends AppCompatActivity {

    TextInputLayout tilHotName, tilHotAbAc, tilHot01, tilHot02, tilHot03, tilHot04, tilHot05, tilHot06, tilHot07, tilHot08, tilHot09, tilHot10;
    EditText etHotName, etHotAbAc, etHot01, etHot02, etHot03, etHot04, etHot05, etHot06, etHot07, etHot08, etHot09, etHot10;
    Button btnSaveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_edit_hotline);

        tilHotName = (TextInputLayout) findViewById (R.id.tilHotName);
        tilHotAbAc = (TextInputLayout) findViewById (R.id.tilHotAbAc);
        tilHot01 = (TextInputLayout) findViewById (R.id.tilHot01);
        tilHot02 = (TextInputLayout) findViewById (R.id.tilHot02);
        tilHot03 = (TextInputLayout) findViewById (R.id.tilHot03);
        tilHot04 = (TextInputLayout) findViewById (R.id.tilHot04);
        tilHot05 = (TextInputLayout) findViewById (R.id.tilHot05);
        tilHot06 = (TextInputLayout) findViewById (R.id.tilHot06);
        tilHot07 = (TextInputLayout) findViewById (R.id.tilHot07);
        tilHot08 = (TextInputLayout) findViewById (R.id.tilHot08);
        tilHot09 = (TextInputLayout) findViewById (R.id.tilHot09);
        tilHot10 = (TextInputLayout) findViewById (R.id.tilHot10);

        etHotName = (EditText) findViewById (R.id.etHotName);
        etHotAbAc = (EditText) findViewById (R.id.etHotAbAc);
        etHot01 = (EditText) findViewById (R.id.etHot01);
        etHot02 = (EditText) findViewById (R.id.etHot02);
        etHot03 = (EditText) findViewById (R.id.etHot03);
        etHot04 = (EditText) findViewById (R.id.etHot04);
        etHot05 = (EditText) findViewById (R.id.etHot05);
        etHot06 = (EditText) findViewById (R.id.etHot06);
        etHot07 = (EditText) findViewById (R.id.etHot07);
        etHot08 = (EditText) findViewById (R.id.etHot08);
        etHot09 = (EditText) findViewById (R.id.etHot09);
        etHot10 = (EditText) findViewById (R.id.etHot10);

        btnSaveChanges = (Button) findViewById (R.id.btnSaveChanges);

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Admin_EditHotline.this, "Hotline Updated", Toast.LENGTH_SHORT).show();

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
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
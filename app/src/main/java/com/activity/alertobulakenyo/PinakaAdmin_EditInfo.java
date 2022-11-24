package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class PinakaAdmin_EditInfo extends AppCompatActivity {

    TextInputLayout tilCity, tilDeptName, tilDeptAbbv, tilEmail, tilPass, tilAdminName;
    AutoCompleteTextView actCity;
    EditText etDeptName, etDeptAbbv, etEmail, etPass, etAdminName;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_pinaka_admin_edit_info);

        tilCity = (TextInputLayout) findViewById (R.id.tilCity);
        tilDeptName = (TextInputLayout) findViewById (R.id.tilDeptName);
        tilDeptAbbv = (TextInputLayout) findViewById (R.id.tilDeptAbbv);
        tilEmail = (TextInputLayout) findViewById (R.id.tilEmail);
        tilPass = (TextInputLayout) findViewById (R.id.tilPass);
        tilAdminName = (TextInputLayout) findViewById (R.id.tilAdminName);

        actCity = (AutoCompleteTextView) findViewById (R.id.actCity);

        etDeptName = (EditText) findViewById (R.id.etDeptAbbv);
        etDeptAbbv = (EditText) findViewById (R.id.etDeptName);
        etEmail = (EditText) findViewById (R.id.etEmail);
        etPass = (EditText) findViewById (R.id.etPass);
        etAdminName = (EditText) findViewById (R.id.etAdminName);

        btnSave = (Button) findViewById (R.id.btnSave);

        String [] city = {"Bocaue", "Marilao", "Meycauayan", "San Jose del Monte", "Santa Maria"};

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(PinakaAdmin_EditInfo.this, R.layout.dropdown_items, city);
        actCity.setDropDownBackgroundResource(R.color.white);
        actCity.setAdapter(cityAdapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // maglagay ng toast for save changes
                Toast.makeText(PinakaAdmin_EditInfo.this, "Saved!", Toast.LENGTH_SHORT).show();

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
        Intent intent = new Intent(PinakaAdmin_EditInfo.this, PinakaAdmin_AccountInfo.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
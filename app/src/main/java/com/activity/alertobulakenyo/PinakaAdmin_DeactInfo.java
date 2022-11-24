package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PinakaAdmin_DeactInfo extends AppCompatActivity {

    Button btnActivate;
    TextView tvDeptAbbre, tvDeptName, tvCity, tvEmail, tvAdminName, tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_pinaka_admin_deact_info);

        btnActivate = (Button) findViewById (R.id.btnActivate);

        tvDeptAbbre = (TextView) findViewById (R.id.tvDeptAbbre);
        tvDeptName = (TextView) findViewById (R.id.tvDeptName);
        tvCity = (TextView) findViewById (R.id.tvCity);
        tvEmail = (TextView) findViewById (R.id.tvEmail);
        tvAdminName = (TextView) findViewById (R.id.tvAdminName);
        tvStatus = (TextView) findViewById (R.id.tvStatus);

        btnActivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // maglagay ng toast for save changes
                Toast.makeText(PinakaAdmin_DeactInfo.this, "Account Activated!", Toast.LENGTH_SHORT).show();

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
        Intent intent = new Intent(PinakaAdmin_DeactInfo.this, PinakaAdmin_Deacts.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
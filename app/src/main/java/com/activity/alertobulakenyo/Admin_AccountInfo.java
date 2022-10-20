package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Admin_AccountInfo extends AppCompatActivity {

    TextView tvDeptAbbre, tvDeptName, tvCity, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_account_info);

        tvDeptAbbre = (TextView) findViewById (R.id.tvDeptAbbre);
        tvDeptName = (TextView) findViewById (R.id.tvDeptAbbre);
        tvCity = (TextView) findViewById (R.id.tvCity);
        tvEmail = (TextView) findViewById (R.id.tvEmail);

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
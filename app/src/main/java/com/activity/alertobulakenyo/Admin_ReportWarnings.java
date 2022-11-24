package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Admin_ReportWarnings extends AppCompatActivity {

    RecyclerView rvRepWarn;
    TextView tvDept, tvCity, tvWarnType, tvDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_report_warnings);

        rvRepWarn = (RecyclerView) findViewById (R.id.rvRepWarn);

        tvDept = (TextView) findViewById (R.id.tvDept);
        tvCity = (TextView) findViewById (R.id.tvWarnType);
        tvWarnType = (TextView) findViewById (R.id.tvWarnType);
        tvDateTime = (TextView) findViewById (R.id.tvDateTime);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class PinakaAdmin_ReportAnnouncements extends AppCompatActivity {

    RecyclerView rvRepAncmt;
    TextView tvCity, tvDept, tvNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_pinaka_admin_report_announcements);

        rvRepAncmt = (RecyclerView) findViewById (R.id.rvRepAncmt);

        tvCity = (TextView) findViewById (R.id.tvCity);
        tvDept = (TextView) findViewById (R.id.tvDept);
        tvNum = (TextView) findViewById (R.id.tvNum);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(PinakaAdmin_ReportAnnouncements.this, PinakaAdmin_Report.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
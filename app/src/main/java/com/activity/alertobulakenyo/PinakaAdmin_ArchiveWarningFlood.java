package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class PinakaAdmin_ArchiveWarningFlood extends AppCompatActivity {

    TextView tvDate, tvTime, tvRainWarn, tvFloodLvl, tvLoc, tvIns, tvHot01, tvHot02, tvHot03, tvHot04, tvHot05,tvHot06, tvHot07, tvHot08, tvHot09, tvHot10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_pinaka_admin_archive_warning_flood);

        tvDate = (TextView) findViewById (R.id.tvDate);
        tvTime = (TextView) findViewById (R.id.tvTime);
        tvRainWarn = (TextView) findViewById (R.id.tvRainWarn);
        tvFloodLvl = (TextView) findViewById (R.id.tvFloodLvl);
        tvLoc = (TextView) findViewById (R.id.tvLoc);
        tvIns = (TextView) findViewById (R.id.tvIns);
        tvHot01 = (TextView) findViewById (R.id.tvHot01);
        tvHot02 = (TextView) findViewById (R.id.tvHot02);
        tvHot03 = (TextView) findViewById (R.id.tvHot03);
        tvHot04 = (TextView) findViewById (R.id.tvHot04);
        tvHot05 = (TextView) findViewById (R.id.tvHot05);
        tvHot06 = (TextView) findViewById (R.id.tvHot06);
        tvHot07 = (TextView) findViewById (R.id.tvHot07);
        tvHot08 = (TextView) findViewById (R.id.tvHot08);
        tvHot09 = (TextView) findViewById (R.id.tvHot09);
        tvHot10 = (TextView) findViewById (R.id.tvHot10);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(PinakaAdmin_ArchiveWarningFlood.this, PinakaAdmin_RepArchiveWarning.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
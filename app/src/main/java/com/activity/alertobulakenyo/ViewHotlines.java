package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewHotlines extends AppCompatActivity {

    LinearLayout layHot01, layHot02, layHot03, layHot04, layHot05, layHot06, layHot07 , layHot08, layHot09, layHot10;
    TextView tvAbAc, tvHotName, tvHot01, tvHot02, tvHot03, tvHot04, tvHot05, tvHot06, tvHot07, tvHot08, tvHot09, tvHot10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_view_hotlines);

        layHot01 = (LinearLayout) findViewById (R.id.layHot01);
        layHot02 = (LinearLayout) findViewById (R.id.layHot02);
        layHot03 = (LinearLayout) findViewById (R.id.layHot03);
        layHot04 = (LinearLayout) findViewById (R.id.layHot04);
        layHot05 = (LinearLayout) findViewById (R.id.layHot05);
        layHot06 = (LinearLayout) findViewById (R.id.layHot06);
        layHot07 = (LinearLayout) findViewById (R.id.layHot07);
        layHot08 = (LinearLayout) findViewById (R.id.layHot08);
        layHot09 = (LinearLayout) findViewById (R.id.layHot09);
        layHot10 = (LinearLayout) findViewById (R.id.layHot10);

        tvAbAc = (TextView) findViewById (R.id.tvAbAc);
        tvHotName = (TextView) findViewById (R.id.tvHotName);
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
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
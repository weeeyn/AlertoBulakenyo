package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewHotlines extends AppCompatActivity {

    TextView tvAbAc, tvHotName, tvHot01, tvHot02, tvHot03, tvHot04, tvHot05, tvHot06, tvHot07, tvHot08, tvHot09, tvHot10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_view_hotlines);

        HotlinesHolder hotlinesHolder = (HotlinesHolder) getIntent().getSerializableExtra("hotlineview");

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

        tvAbAc.setText(hotlinesHolder.getHotlineName());
        tvAbAc.setText(hotlinesHolder.getHotlineNameAbv());
        tvHot01.setText(hotlinesHolder.getHotlineOne());
        tvHot02.setText(hotlinesHolder.getHotlineTwo());
        tvHot03.setText(hotlinesHolder.getHotlineThree());
        tvHot04.setText(hotlinesHolder.getHotlineFour());
        tvHot05.setText(hotlinesHolder.getHotlineFive());
        tvHot06.setText(hotlinesHolder.getHotlineSix());
        tvHot07.setText(hotlinesHolder.getHotlineSeven());
        tvHot08.setText(hotlinesHolder.getHotlineEight());
        tvHot09.setText(hotlinesHolder.getHotlineNine());
        tvHot10.setText(hotlinesHolder.getHotlineTen());

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
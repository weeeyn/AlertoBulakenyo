package com.activity.alertobulakenyo.ResidentUsers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.activity.alertobulakenyo.ObjectClasses.HotlinesHolder;
import com.activity.alertobulakenyo.R;
import com.activity.alertobulakenyo.ResidentUsers.Hotlines;

public class ViewHotlines extends AppCompatActivity {

    TextView tvCity, tvHotName, tvHot01, tvHot02, tvHot03, tvHot04, tvHot05;
    LinearLayout layHot01, layHot02, layHot03, layHot04, layHot05;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_view_hotlines);

        HotlinesHolder hotlinesHolder = (HotlinesHolder) getIntent().getSerializableExtra("hotlineview");

        layHot01 = (LinearLayout) findViewById (R.id.layHot01);
        layHot02 = (LinearLayout) findViewById (R.id.layHot02);
        layHot03 = (LinearLayout) findViewById (R.id.layHot03);
        layHot04 = (LinearLayout) findViewById (R.id.layHot04);
        layHot05 = (LinearLayout) findViewById (R.id.layHot05);

        tvCity = (TextView) findViewById (R.id.tvCity);
        tvHotName = (TextView) findViewById (R.id.tvHotName);
        tvHot01 = (TextView) findViewById (R.id.tvHot01);
        tvHot02 = (TextView) findViewById (R.id.tvHot02);
        tvHot03 = (TextView) findViewById (R.id.tvHot03);
        tvHot04 = (TextView) findViewById (R.id.tvHot04);
        tvHot05 = (TextView) findViewById (R.id.tvHot05);

        tvCity.setText(hotlinesHolder.getHotlineCity());
        tvHotName.setText(hotlinesHolder.getHotlineName());
        tvHot01.setText(hotlinesHolder.getHotlineOne());
        tvHot02.setText(hotlinesHolder.getHotlineTwo());
        tvHot03.setText(hotlinesHolder.getHotlineThree());
        tvHot04.setText(hotlinesHolder.getHotlineFour());
        tvHot05.setText(hotlinesHolder.getHotlineFive());

        if (tvHot01 != null) {
            layHot01.setVisibility(View.VISIBLE);
            layHot02.setVisibility(View.GONE);
            layHot03.setVisibility(View.GONE);
            layHot04.setVisibility(View.GONE);
            layHot05.setVisibility(View.GONE);
        } else if (tvHot02 != null) {
            layHot01.setVisibility(View.VISIBLE);
            layHot02.setVisibility(View.VISIBLE);
            layHot03.setVisibility(View.GONE);
            layHot04.setVisibility(View.GONE);
            layHot05.setVisibility(View.GONE);
        } else if (tvHot03 != null) {
            layHot01.setVisibility(View.VISIBLE);
            layHot02.setVisibility(View.VISIBLE);
            layHot03.setVisibility(View.VISIBLE);
            layHot04.setVisibility(View.GONE);
            layHot05.setVisibility(View.GONE);
        } else if (tvHot04 != null) {
            layHot01.setVisibility(View.VISIBLE);
            layHot02.setVisibility(View.VISIBLE);
            layHot03.setVisibility(View.VISIBLE);
            layHot04.setVisibility(View.VISIBLE);
            layHot05.setVisibility(View.GONE);
        } else if (tvHot05 != null) {
            layHot01.setVisibility(View.VISIBLE);
            layHot02.setVisibility(View.VISIBLE);
            layHot03.setVisibility(View.VISIBLE);
            layHot04.setVisibility(View.VISIBLE);
            layHot05.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Hotlines.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Walkthrough extends AppCompatActivity {

    private ViewPager screenPager;
    WalkthroughViewPagerAdapter WalkthroughViewPagerAdapter;
    TabLayout tabIndicator;
    TextView tvSkip;
    ImageButton btnNext;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_walkthrough);

        tvSkip = (TextView) findViewById (R.id.tvSkip);
        btnNext = (ImageButton) findViewById (R.id.btnNext);
        tabIndicator = findViewById(R.id.tabIndicator);

        List<ScreenItem> list = new ArrayList<>();
        list.add(new ScreenItem("DISASTER WARNING", "When a disaster occurs in the province of Bulacan, the app issues official warnings. These come with guidelines and hotline numbers.", R.drawable.feature1));
        list.add(new ScreenItem("EVACUATION CENTER", "Users of the app have access to a list of evacuation facilities they can use in the event of a crisis.", R.drawable.feature2));
        list.add(new ScreenItem("PREPAREDNESS TIPS", "The application assists users in becoming prepared for disasters by providing instructions and guidance for disaster preparedness.", R.drawable.feature3));
        list.add(new ScreenItem("EMERGENCY HOTLINES", "Users of the app can contact the numbers listed in the emergency hotlines in the event of an emergency.", R.drawable.feature4));

        screenPager = findViewById(R.id.screenViewPager);
        WalkthroughViewPagerAdapter = new WalkthroughViewPagerAdapter(this, list);
        screenPager.setAdapter(WalkthroughViewPagerAdapter);

        tabIndicator.setupWithViewPager(screenPager);

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Walkthrough.this, login.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();

                if (position < list.size())
                {
                    position++;
                    screenPager.setCurrentItem(position);
                }

                if (position == list.size())
                {
                    Intent intent = new Intent(Walkthrough.this, login.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right,
                            R.anim.slide_out_left);
                }
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
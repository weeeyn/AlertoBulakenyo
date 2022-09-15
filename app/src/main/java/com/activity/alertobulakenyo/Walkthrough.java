package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
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
        list.add(new ScreenItem("DISASTER WARNING", "The app sends out official warnings when a disaster occurs close to the user's current location via an in-app and SMS alert message. Notifications can be received by users with or without an internet connection.", R.drawable.feature1));
        list.add(new ScreenItem("EVACUATION CENTER", "By setting up their pin location, this tool will assist users in finding the closest evacuation facility. After users create accounts, this page will provide a list of evacuation centers they can search for.", R.drawable.feature2));
        list.add(new ScreenItem("PREPAREDNESS TIPS", "This application also allows users to prepare their homes and families for disasters. Increase public understanding of the importance of disaster preparedness before, during, and after a disaster.", R.drawable.feature3));
        list.add(new ScreenItem("EMERGENCY HOTLINES", "One of this app's best features is the one-tap access to hotlines or emergency personnel. Users can communicate directly with the local emergency and rescue services.", R.drawable.feature4));
        list.add(new ScreenItem("TRUSTED CONTACTS", "Users can add people they care about as trusted contacts to ensure the safety of their relatives. In the event of a disaster, added trusted contacts will be able to track the user's location.", R.drawable.feature5));

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
package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class PinakaAdmin_Report extends AppCompatActivity {

    CardView card_repUsers, card_repAncmt, card_repWarn, card_repArcAncmt, card_repArcWarn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_pinaka_admin_report);

        card_repUsers = (CardView) findViewById (R.id.card_repUsers);
        card_repAncmt = (CardView) findViewById (R.id.card_repAncmt);
        card_repWarn = (CardView) findViewById (R.id.card_repWarn);
        card_repArcAncmt = (CardView) findViewById (R.id.card_repArcAncmt);
        card_repArcWarn = (CardView) findViewById (R.id.card_repArcWarn);

        card_repUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PinakaAdmin_Report.this, PinakaAdmin_ReportUsers.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        card_repAncmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PinakaAdmin_Report.this, PinakaAdmin_ReportAnnouncements.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        card_repWarn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PinakaAdmin_Report.this, PinakaAdmin_ReportWarning.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        card_repArcAncmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PinakaAdmin_Report.this, PinakaAdmin_RepArchiveAncmt.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        card_repArcWarn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PinakaAdmin_Report.this, PinakaAdmin_RepArchiveWarning.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(PinakaAdmin_Report.this, PinakaAdmin_Home.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
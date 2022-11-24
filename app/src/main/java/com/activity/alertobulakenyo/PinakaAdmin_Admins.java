package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class PinakaAdmin_Admins extends AppCompatActivity {

    CardView card_createAdmin, card_adminAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_pinaka_admin_admins);

        card_createAdmin = (CardView) findViewById (R.id.card_createAdmin);
        card_adminAcc = (CardView) findViewById (R.id.card_adminAcc);

        card_createAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PinakaAdmin_Admins.this, PinakaAdmin_CreateAcc.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        card_adminAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PinakaAdmin_Admins.this, PinakaAdmin_ViewAcc.class);
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
        Intent intent = new Intent(PinakaAdmin_Admins.this, PinakaAdmin_Home.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
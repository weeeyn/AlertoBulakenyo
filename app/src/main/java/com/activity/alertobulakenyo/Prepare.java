package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class Prepare extends AppCompatActivity {

    ImageButton btnBackHome, imgFirstAid, imgSurvival;
    Button btnEQ, btnFire, btnFlood, btnLS, btnTy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_prepare);

        btnBackHome = (ImageButton) findViewById (R.id.btnBackHome);
        imgFirstAid = (ImageButton) findViewById (R.id.imgFirstAid);
        imgSurvival = (ImageButton) findViewById (R.id.imgSurvival);

        btnEQ = (Button) findViewById (R.id.btnEQ);
        btnFire = (Button) findViewById (R.id.btnFire);
        btnFlood = (Button) findViewById (R.id.btnFlood);
        btnLS = (Button) findViewById (R.id.btnLS);
        btnTy = (Button) findViewById (R.id.btnTy);

        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Prepare.this, HomeNav.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);
            }
        });

        imgFirstAid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Prepare.this, Prep_FA.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        imgSurvival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Prepare.this, Prep_SK.class);
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
        Intent intent = new Intent(Prepare.this, HomeNav.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
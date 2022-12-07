package com.activity.alertobulakenyo.Admins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.activity.alertobulakenyo.Prep_EQ;
import com.activity.alertobulakenyo.Prep_FA;
import com.activity.alertobulakenyo.Prep_Fire;
import com.activity.alertobulakenyo.Prep_Flood;
import com.activity.alertobulakenyo.Prep_LS;
import com.activity.alertobulakenyo.Prep_SK;
import com.activity.alertobulakenyo.Prep_Ty;
import com.activity.alertobulakenyo.Prepare;
import com.activity.alertobulakenyo.R;
import com.activity.alertobulakenyo.ResidentUsers.Home;

public class Admin_Prepare extends AppCompatActivity {

    ImageButton imgFirstAid, imgSurvival;
    Button btnEQ, btnFire, btnFlood, btnLS, btnTy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_prepare);

        imgFirstAid = (ImageButton) findViewById (R.id.imgFirstAid);
        imgSurvival = (ImageButton) findViewById (R.id.imgSurvival);

        btnEQ = (Button) findViewById (R.id.btnEQ);
        btnFire = (Button) findViewById (R.id.btnFire);
        btnFlood = (Button) findViewById (R.id.btnFlood);
        btnLS = (Button) findViewById (R.id.btnLS);
        btnTy = (Button) findViewById (R.id.btnTy);

        imgFirstAid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Prepare.this, Admin_PrepareFA.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        imgSurvival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Prepare.this, Admin_PrepareSK.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        btnEQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Prepare.this, Admin_PrepareEQ.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        btnFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Prepare.this, Admin_PrepareFire.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        btnFlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Prepare.this, Admin_PrepareFlood.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        btnLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Prepare.this, Admin_PrepareLS.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        btnTy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Prepare.this, Admin_PrepareTy.class);
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
        Intent intent = new Intent(Admin_Prepare.this, Admin_Home.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
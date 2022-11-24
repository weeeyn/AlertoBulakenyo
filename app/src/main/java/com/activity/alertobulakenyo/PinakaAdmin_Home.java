package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class PinakaAdmin_Home extends AppCompatActivity {

    int k = 0;
    ImageButton imgAdmin, imgReport;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_pinaka_admin_home);

        imgAdmin = (ImageButton) findViewById (R.id.imgAdmin);
        imgReport = (ImageButton) findViewById (R.id.imgReport);

        btnLogout = (Button) findViewById (R.id.btnLogout);

        imgAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PinakaAdmin_Home.this, PinakaAdmin_Admins.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        imgReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PinakaAdmin_Home.this, PinakaAdmin_Report.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PinakaAdmin_Home.this, login.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Log.e("My Tags", "onBackPressed");
        k++;
        if (k == 1)
        {
            Toast.makeText(PinakaAdmin_Home.this, "Please press again to exit.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            finishAffinity();
            finish();
        }

    }
}
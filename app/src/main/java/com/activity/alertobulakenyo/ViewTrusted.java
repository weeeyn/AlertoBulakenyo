package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewTrusted extends AppCompatActivity {

    ImageView imgProfile;
    TextView tvFullName, tvAccUser, tvAccEmail, tvAccConNum, tvAccAdd, tvRelationship;
    Button btnShowLoc, btnSendRecLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_view_trusted);

        imgProfile = (ImageView) findViewById (R.id.imgProfile);

        tvFullName = (TextView) findViewById (R.id.tvFullName);
        tvAccUser = (TextView) findViewById (R.id.tvAccUser);
        tvAccEmail = (TextView) findViewById (R.id.tvAccEmail);
        tvAccConNum = (TextView) findViewById (R.id.tvAccConNum);
        tvAccAdd = (TextView) findViewById (R.id.tvAccAdd);
        tvRelationship = (TextView) findViewById (R.id.tvRelationship);

        btnShowLoc = (Button) findViewById (R.id.btnShowLoc);
        btnSendRecLoc = (Button) findViewById (R.id.btnSendRecLoc);

        btnShowLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ViewTrusted.this, "Google Maps!", Toast.LENGTH_SHORT).show();

            }
        });

        btnSendRecLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ViewTrusted.this, "Recent Location Sent!", Toast.LENGTH_SHORT).show();

                finish();
                finishActivity(107);
                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);

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
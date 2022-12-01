package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class PinakaAdmin_ArchiveAncmt extends AppCompatActivity {

    TextView tvOffice, tvDateTime, tvTitle, tvBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_pinaka_admin_archive_ancmt);

        tvOffice = (TextView) findViewById (R.id.tvOffice);
        tvDateTime = (TextView) findViewById (R.id.tvDateTime);
        tvTitle = (TextView) findViewById (R.id.tvTitle);
        tvBody = (TextView) findViewById (R.id.tvBody);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(PinakaAdmin_ArchiveAncmt.this, PinakaAdmin_RepArchiveAncmt.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
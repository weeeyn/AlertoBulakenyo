package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ancmntBody extends AppCompatActivity {

    TextView tvOffice, tvDate, tvTitle, tvBody, tvCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_ancmnt_body);

        Announcements announcements = (Announcements) getIntent().getSerializableExtra("announcements");

        tvOffice = (TextView) findViewById (R.id.tvOffice);
        tvCity = (TextView) findViewById(R.id.tvCity);
        tvDate = (TextView) findViewById (R.id.tvDate);
        tvTitle = (TextView) findViewById (R.id.tvTitle);
        tvBody = (TextView) findViewById (R.id.tvBody);

        tvCity.setText(announcements.getAnncmntCity());
        tvOffice.setText(announcements.getAnncmntDept());
        tvDate.setText(announcements.getAnncmntDate());
        tvTitle.setText(announcements.getAnncmntTitle());
        tvBody.setText(announcements.getAnncmntBody());
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Announcements.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
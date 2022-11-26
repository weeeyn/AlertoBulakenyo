package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

public class SettingsNotif extends AppCompatActivity {

    View line1;
    LinearLayout layDisAlrt, layAncmt;
    Switch swAllNotif, swDisAlert, swAncmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_setting_notif);

        line1 = (View) findViewById (R.id.line1);

        layDisAlrt = (LinearLayout) findViewById (R.id.layDisAlert);
        layAncmt = (LinearLayout) findViewById (R.id.layAncmt);

        swAllNotif = (Switch) findViewById (R.id.swAllNotif);
        swDisAlert = (Switch) findViewById (R.id.swDisAlert);
        swAncmt = (Switch) findViewById (R.id.swAncmt);

        swAllNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    line1.setVisibility(View.VISIBLE);
                    layDisAlrt.setVisibility(View.VISIBLE);
                    layAncmt.setVisibility(View.VISIBLE);
                }
                else {
                    line1.setVisibility(View.INVISIBLE);
                    layDisAlrt.setVisibility(View.INVISIBLE);
                    layAncmt.setVisibility(View.INVISIBLE);
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
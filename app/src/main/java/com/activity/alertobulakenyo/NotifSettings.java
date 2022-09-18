package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

public class NotifSettings extends AppCompatActivity {

    View line1, line2;
    LinearLayout layDisAlrt, layAncmt, layReq, layCon, laySMS, layEmail;
    Switch swAllNotif, swDisAlert, swAncmt, swReq, swConLoc, swSMS, swEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_notif_settings);

        line1 = (View) findViewById (R.id.line1);
        line2 = (View) findViewById (R.id.line2);

        layDisAlrt = (LinearLayout) findViewById (R.id.layDisAlert);
        layAncmt = (LinearLayout) findViewById (R.id.layAncmt);
        layReq = (LinearLayout) findViewById (R.id.layReq);
        layCon = (LinearLayout) findViewById (R.id.layCon);
        laySMS = (LinearLayout) findViewById (R.id.laySMS);
        layEmail = (LinearLayout) findViewById (R.id.layEmail);

        swAllNotif = (Switch) findViewById (R.id.swAllNotif);
        swDisAlert = (Switch) findViewById (R.id.swDisAlert);
        swAncmt = (Switch) findViewById (R.id.swAncmt);
        swReq = (Switch) findViewById (R.id.swReq);
        swConLoc = (Switch) findViewById (R.id.swConLoc);
        swSMS = (Switch) findViewById (R.id.swSMS);
        swEmail = (Switch) findViewById (R.id.swEmail);

        swAllNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    line1.setVisibility(View.VISIBLE);
                    layDisAlrt.setVisibility(View.VISIBLE);
                    layAncmt.setVisibility(View.VISIBLE);
                    layReq.setVisibility(View.VISIBLE);
                    layCon.setVisibility(View.VISIBLE);
                    line2.setVisibility(View.VISIBLE);
                    laySMS.setVisibility(View.VISIBLE);
                    layEmail.setVisibility(View.VISIBLE);
                }
                else {
                    line1.setVisibility(View.INVISIBLE);
                    layDisAlrt.setVisibility(View.INVISIBLE);
                    layAncmt.setVisibility(View.INVISIBLE);
                    layReq.setVisibility(View.INVISIBLE);
                    layCon.setVisibility(View.INVISIBLE);
                    line2.setVisibility(View.INVISIBLE);
                    laySMS.setVisibility(View.INVISIBLE);
                    layEmail.setVisibility(View.INVISIBLE);
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
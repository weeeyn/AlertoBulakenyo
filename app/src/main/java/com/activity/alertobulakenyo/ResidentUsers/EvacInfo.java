package com.activity.alertobulakenyo.ResidentUsers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.activity.alertobulakenyo.ObjectClasses.EvacuationHolder;
import com.activity.alertobulakenyo.R;

public class EvacInfo extends AppCompatActivity {

    TextView tvCity, tvBrgy, tvEvacName, tvEvacAdd, tvLong, tvLat;
    Button btnNavi;

//    String latitude = "14.838252";
//    String longitude = "121.046097";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_evac_info);

        EvacuationHolder evacuationHolder = (EvacuationHolder) getIntent().getSerializableExtra("infoevac");

        tvCity = (TextView) findViewById (R.id.tvCity);
        tvBrgy = (TextView) findViewById (R.id.tvBrgy);
        tvEvacName = (TextView) findViewById (R.id.tvEvacName);
        tvEvacAdd = (TextView) findViewById (R.id.tvEvacAdd);
        tvLong = (TextView) findViewById (R.id.tvLong);
        tvLat = (TextView) findViewById (R.id.tvLat);


        tvCity.setText(evacuationHolder.getEvacuationCity());
        tvBrgy.setText(evacuationHolder.getEvacuationBrgy());
        tvEvacName.setText(evacuationHolder.getEvacuationName());
        tvEvacAdd.setText(evacuationHolder.getEvacuationAddress());
        tvLong.setText(evacuationHolder.getEvacuationLongitude());
        tvLat.setText(evacuationHolder.getEvacuationLatitude());

        String longitude = tvLong.getText().toString();
        String latitude = tvLat.getText().toString();

        btnNavi = (Button) findViewById (R.id.btnNavi);

        btnNavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(EvacInfo.this, "Directing to Google Maps.", Toast.LENGTH_LONG).show();

                // direct to gmaps

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("google.navigation:q=" + latitude + "," + longitude + "&mode=d"));
                intent.setPackage("com.google.android.apps.maps");
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
        Intent intent = new Intent(getApplicationContext(), EvacList.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
package com.activity.alertobulakenyo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Admin_EvacInfo extends AppCompatActivity {

    //TODO DELETE THIS

    TextView tvCity, tvBrgy, tvEvacName, tvEvacAdd, tvLong, tvLat;
    Button btnEditEvac, btnDeleteEvac, btnNavi;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_evac_info);

        EvacuationHolder evacuationHolder = (EvacuationHolder) getIntent().getSerializableExtra("evac");

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

        btnEditEvac = (Button) findViewById (R.id.btnEditEvac);
        btnDeleteEvac = (Button) findViewById (R.id.btnDeleteEvac);
        btnNavi = (Button) findViewById (R.id.btnNavi);

        AlertDialog.Builder build = new AlertDialog.Builder(Admin_EvacInfo.this);
        dialog = build.create();

        btnEditEvac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Admin_EvacInfo.this, Admin_EditEvac.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        btnDeleteEvac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Admin_EvacInfo.this);

                builder.setMessage("Are you sure you want to delete this evacuation center?");

                builder.setCancelable(true);

                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // When the user click yes button then app will close
                    finish();
                    overridePendingTransition(R.anim.slide_in_left,
                            R.anim.slide_out_right);

                    Toast.makeText(Admin_EvacInfo.this, "Evacuation Center Deleted!", Toast.LENGTH_SHORT).show();
                });

                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // If user click no then dialog box is canceled.
                    dialog.cancel();
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        btnNavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Admin_EvacInfo.this, "Directing to Google Maps.", Toast.LENGTH_LONG).show();

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
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
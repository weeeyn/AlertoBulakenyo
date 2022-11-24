package com.activity.alertobulakenyo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Admin_DisasterFireInfo extends AppCompatActivity {

    TextView tvDate, tvTime, tvFireAlert, tvLoc, tvIns, tvHot01, tvHot02, tvHot03, tvHot04, tvHot05,tvHot06, tvHot07, tvHot08, tvHot09, tvHot10;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_disaster_fire_info);

        tvDate = (TextView) findViewById (R.id.tvDate);
        tvTime = (TextView) findViewById (R.id.tvTime);
        tvFireAlert = (TextView) findViewById (R.id.tvFireAlert);
        tvLoc = (TextView) findViewById (R.id.tvLoc);
        tvIns = (TextView) findViewById (R.id.tvIns);
        tvHot01 = (TextView) findViewById (R.id.tvHot01);
        tvHot02 = (TextView) findViewById (R.id.tvHot02);
        tvHot03 = (TextView) findViewById (R.id.tvHot03);
        tvHot04 = (TextView) findViewById (R.id.tvHot04);
        tvHot05 = (TextView) findViewById (R.id.tvHot05);
        tvHot06 = (TextView) findViewById (R.id.tvHot06);
        tvHot07 = (TextView) findViewById (R.id.tvHot07);
        tvHot08 = (TextView) findViewById (R.id.tvHot08);
        tvHot09 = (TextView) findViewById (R.id.tvHot09);
        tvHot10 = (TextView) findViewById (R.id.tvHot10);

        btnDelete = (Button) findViewById (R.id.btnDelete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Admin_DisasterFireInfo.this);

                builder.setMessage("Are you sure you want to delete this warning?");

                builder.setCancelable(true);

                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // When the user click yes button then app will close
                    finish();
                    overridePendingTransition(R.anim.slide_in_left,
                            R.anim.slide_out_right);

                    Toast.makeText(Admin_DisasterFireInfo.this, "Warning Deleted!", Toast.LENGTH_SHORT).show();
                });

                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // If user click no then dialog box is canceled.
                    dialog.cancel();
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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
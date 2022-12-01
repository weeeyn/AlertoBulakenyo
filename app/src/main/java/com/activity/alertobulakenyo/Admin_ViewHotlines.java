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
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Admin_ViewHotlines extends AppCompatActivity {

    //TODO DELETE

    Button btnEditHot, btnDelHot;
    TextView tvAbAc, tvHotName, tvHot01, tvHot02, tvHot03, tvHot04, tvHot05, tvHot06, tvHot07, tvHot08, tvHot09, tvHot10;
    LinearLayout layHot01, layHot02, layHot03, layHot04, layHot05, layHot06, layHot07, layHot08, layHot09, layHot10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_view_hotlines);

        btnEditHot = (Button) findViewById (R.id.btnEditHot);
        btnDelHot = (Button) findViewById (R.id.btnDelHot);

        tvAbAc = (TextView) findViewById (R.id.tvAbAc);
        tvHotName = (TextView) findViewById (R.id.tvHotName);
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

        layHot01 = (LinearLayout) findViewById (R.id.layHot01);
        layHot02 = (LinearLayout) findViewById (R.id.layHot02);
        layHot03 = (LinearLayout) findViewById (R.id.layHot03);
        layHot04 = (LinearLayout) findViewById (R.id.layHot04);
        layHot05 = (LinearLayout) findViewById (R.id.layHot05);
        layHot06 = (LinearLayout) findViewById (R.id.layHot06);
        layHot07 = (LinearLayout) findViewById (R.id.layHot07);
        layHot08 = (LinearLayout) findViewById (R.id.layHot08);
        layHot09 = (LinearLayout) findViewById (R.id.layHot09);
        layHot10 = (LinearLayout) findViewById (R.id.layHot10);

        btnEditHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Admin_ViewHotlines.this, Admin_EditHotline.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        btnDelHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Admin_ViewHotlines.this);

                builder.setMessage("Are you sure you want to delete this hotline?");

                builder.setCancelable(true);

                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // When the user click yes button then app will close
                    finish();
                    overridePendingTransition(R.anim.slide_in_left,
                            R.anim.slide_out_right);
                });

                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
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
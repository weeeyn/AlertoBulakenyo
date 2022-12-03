package com.activity.alertobulakenyo;
//TODO DELETE
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Admin_AncmntBody extends AppCompatActivity {

    //TODO DELETE THIS

    Button btnEditAncmnt, btnDeleteAncmnt;
    Dialog dialog;
    TextView tvCity, tvOffice, tvDateTime, tvTitle, tvBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_ancmnt_body);

        Announcements announcements = (Announcements) getIntent().getSerializableExtra("announcements");

        btnEditAncmnt = (Button) findViewById (R.id.btnEditAncmnt);
        btnDeleteAncmnt = (Button) findViewById (R.id.btnDeleteAncmnt);

        tvCity = (TextView) findViewById (R.id.tvCity);
        tvOffice = (TextView) findViewById (R.id.tvOffice);
        tvDateTime = (TextView) findViewById (R.id.tvDateTime);
        tvTitle = (TextView) findViewById (R.id.tvTitle);
        tvBody = (TextView) findViewById (R.id.tvBody);

        tvOffice.setText(announcements.getAnncmntDept());
        tvDateTime.setText(announcements.getAnncmntDateTime());
        tvTitle.setText(announcements.getAnncmntTitle());
        tvBody.setText(announcements.getAnncmntBody());

        AlertDialog.Builder build = new AlertDialog.Builder(Admin_AncmntBody.this);
        dialog = build.create();

        btnEditAncmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_AncmntBody.this, Admin_EditAncmnt.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        btnDeleteAncmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Admin_AncmntBody.this);

                builder.setMessage("Are you sure you want to delete this announcement?");

                builder.setCancelable(true);

                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // When the user click yes button then app will close
                    finish();
                    overridePendingTransition(R.anim.slide_in_left,
                            R.anim.slide_out_right);

                    Toast.makeText(Admin_AncmntBody.this, "Announcemennt Deleted!", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(getApplicationContext(), Admin_Evacuation.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
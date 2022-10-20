package com.activity.alertobulakenyo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Admin_AncmntBody extends AppCompatActivity {

    Button btnEditAncmnt, btnDeleteAncmnt;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_ancmnt_body);

        btnEditAncmnt = (Button) findViewById (R.id.btnEditAncmnt);
        btnDeleteAncmnt = (Button) findViewById (R.id.btnDeleteAncmnt);

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
                final Dialog dialog = new Dialog(Admin_AncmntBody.this);
                dialog.setContentView(R.layout.dialog_delete_account);

                TextView tvNo = (TextView) dialog.findViewById(R.id.tvNo);
                TextView tvYes = (TextView) dialog.findViewById(R.id.tvYes);

                tvNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                tvYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(Admin_AncmntBody.this, "Announcement Deleted!", Toast.LENGTH_SHORT).show();


                        finish();
                        finishActivity(107);
                        overridePendingTransition(R.anim.slide_in_left,
                                R.anim.slide_out_right);
                    }
                });

                dialog.show();
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
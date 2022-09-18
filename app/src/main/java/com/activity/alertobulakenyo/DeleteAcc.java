package com.activity.alertobulakenyo;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class DeleteAcc extends AppCompatActivity {

    TextInputLayout tilPass;
    EditText etPass;
    Button btnDelAcc;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_delete_acc);

        tilPass = (TextInputLayout) findViewById (R.id.tilPass);
        etPass = (EditText) findViewById (R.id.etPass);
        btnDelAcc = (Button) findViewById (R.id.btnDelAcc);

        AlertDialog.Builder build = new AlertDialog.Builder(DeleteAcc.this);
        dialog = build.create();

        btnDelAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(DeleteAcc.this);
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
                        Intent intent = new Intent(DeleteAcc.this, login.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right,
                                R.anim.slide_out_left);
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
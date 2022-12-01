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
                AlertDialog.Builder builder = new AlertDialog.Builder(DeleteAcc.this);

                builder.setMessage("Are you sure you want to delete your account?");

                builder.setCancelable(true);

                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // When the user click yes button then app will close
                    Intent intent = new Intent(DeleteAcc.this, login.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right,
                            R.anim.slide_out_left);

                    Toast.makeText(DeleteAcc.this, "Account Deleted!", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(getApplicationContext(), Settings.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
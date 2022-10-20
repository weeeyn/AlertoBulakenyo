package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;

public class Admin_CreateAncmnt extends AppCompatActivity {

    TextInputLayout tilAncmntTitle, tilAncmnt;
    EditText etAncmntTitle, etAncmnt;
    Button btnUpload, btnRemove, btnPost;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_create_ancmnt);

        tilAncmntTitle = (TextInputLayout) findViewById (R.id.tilAncmntTitle);
        tilAncmnt = (TextInputLayout) findViewById (R.id.tilAncmnt);

        etAncmntTitle = (EditText) findViewById (R.id.etAncmntTitle);
        etAncmnt = (EditText) findViewById (R.id.etAncmnt);

        btnUpload = (Button) findViewById (R.id.btnUpload);
        btnRemove = (Button) findViewById (R.id.btnRemove);
        btnPost = (Button) findViewById (R.id.btnPost);

        img = (ImageView) findViewById (R.id.img);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
                finishActivity(107);
                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);
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
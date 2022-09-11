package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class Prep_LS extends AppCompatActivity {

    TextView tvIntro, tvCont, tvBull01, tvBull02, tvBull03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_prep_ls);

        tvIntro = (TextView) findViewById (R.id.tvIntro);
        tvCont = (TextView) findViewById (R.id.tvCont);
        tvBull01 = (TextView) findViewById (R.id.tvBull01);
        tvBull02 = (TextView) findViewById (R.id.tvBull02);
        tvBull03 = (TextView) findViewById (R.id.tvBull03);

        tvIntro.setText("       ");

        tvCont.setText("       ");

        tvBull01.setText("\u25CF  " +
                "\n\u25CF  " +
                "\n\u25CF  " +
                "\n\u25CF  " +
                "\n\u25CF  ");

        tvBull02.setText("\n\u25CF  " +
                "\n\u25CF  " +
                "\n\u25CF  " +
                "\n\u25CF  " +
                "\n\u25CF  ");

        tvBull03.setText("\n\u25CF  " +
                "\n\u25CF  " +
                "\n\u25CF  " +
                "\n\u25CF  " +
                "\n\u25CF  " +
                "\n\u25CF  " +
                "\n\u25CF  " +
                "\n\u25CF  " +
                "\n\u25CF  " +
                "\n\u25CF  " +
                "\n\u25CF  ");
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(Prep_LS.this, Prepare.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
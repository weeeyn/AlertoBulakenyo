package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class Prep_SK extends AppCompatActivity {

    TextView tvIntro, tvCont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_prep_sk);
        tvIntro = (TextView) findViewById (R.id.tvIntro);
        tvCont = (TextView) findViewById (R.id.tvCont);

        tvIntro.setText("     Survival Kit is a compact package of emergency equipment including food and other items that vary with climatic factors in the operational area for use by aircrew members who have descended in isolated or primitive territory.");

        tvCont.setText("     Survival kits typically include items such as a knife (or Swiss army knife), matches, tinder, bandanas, fish hooks, sewing kit, and a flashlight for a nightcap. An emergency kit makes it easy to prepare and protect your household from disasters, essential if you are going to survive within a short period of time. It supplies crucial items like food, fuel, and shelter for yourself, as well as your family. Your kit should always be kept in an area that everyone knows about in your household.");
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(Prep_SK.this, Prepare.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
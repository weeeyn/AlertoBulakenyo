package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class Prep_FA extends AppCompatActivity {

    TextView tvIntro, tvCont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_prep_fa);

        tvIntro = (TextView) findViewById (R.id.tvIntro);
        tvCont = (TextView) findViewById (R.id.tvCont);

        tvIntro.setText("     First aid supplies or first aid kits are collections of life saving equipment that are used by paramedic staff in cases of emergency to increase the life of a victim until a time when the injured can be transferred to a permanent hospital facility.\n");

        tvCont.setText("     First aid supplies or first aid kits are a combination of medical equipment, medicines and personal protective equipment. The first aid supplies or first aid kits can be designed as per the need, or based on the type of emergency the organization is dealing with. In terms of PPE one can add, but is not limited to, goggles, gloves, surgical masks etc. In terms of instruments, one can add scissors, tweezers, lights, thermometers, torches, cotton swabs, etc. In terms of medicines one can add oral rehydration salts, absorption, e.g. charcoal.\n");

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(Prep_FA.this, Prepare.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
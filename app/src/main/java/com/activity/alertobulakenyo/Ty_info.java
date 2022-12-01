package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class Ty_info extends AppCompatActivity {

    TextView tvDate, tvTime, tvTyName, tvSignal, tvLoc, tvIns;
    Button btnEvac;
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_ty_info);

        WarningHolder warningHolder = (WarningHolder) getIntent().getSerializableExtra("tyinfo");

        tvDate = (TextView) findViewById (R.id.tvDate);
        tvTime = (TextView) findViewById (R.id.tvTime);
        tvTyName = (TextView) findViewById (R.id.tvTyName);
        tvSignal = (TextView) findViewById (R.id.tvSignal);
        tvLoc = (TextView) findViewById (R.id.tvLoc);
        tvIns = (TextView) findViewById (R.id.tvIns);

        tvDate.setText(warningHolder.getDisasterDate());
        tvTime.setText(warningHolder.getDisasterTime());
        tvTyName.setText(warningHolder.getTyphoonName());
        tvSignal.setText(warningHolder.getTyphoonSignal());
        tvLoc.setText(warningHolder.getDisasterCity() + ", Bulacan");
        tvIns.setText(warningHolder.getDisasterInfo());

        btnEvac = (Button) findViewById (R.id.btnEvac);

        btnEvac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ty_info.this, Evacuation.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Typhoon.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
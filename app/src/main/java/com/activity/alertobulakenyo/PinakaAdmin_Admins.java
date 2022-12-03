package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;

public class  PinakaAdmin_Admins extends AppCompatActivity {

    CardView card_createAdmin, card_adminAcc;

//    private FirebaseAuth mAuth1;
//    private FirebaseAuth mAuth2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_pinaka_admin_admins);

//        mAuth1 = FirebaseAuth.getInstance();
//
//        FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
//                .setDatabaseUrl("alerto-bulakenyo-60ecf")
//                .setApiKey("AIzaSyDe3CsU_sguucQX-qXI3P7bpszuAPd1RHA")
//                .setApplicationId("alerto-bulakenyo-60ecf").build();
//
//        try { FirebaseApp myApp = FirebaseApp.initializeApp(getApplicationContext(), firebaseOptions, "Alerto Bulakenyo");
//            mAuth2 = FirebaseAuth.getInstance(myApp);
//        } catch (IllegalStateException e){
//            mAuth2 = FirebaseAuth.getInstance(FirebaseApp.getInstance("Alerto Bulakenyo"));
//        }

        card_createAdmin = (CardView) findViewById (R.id.card_createAdmin);
        card_adminAcc = (CardView) findViewById (R.id.card_adminAcc);

        card_createAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PinakaAdmin_Admins.this, PinakaAdmin_CreateAcc.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        card_adminAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PinakaAdmin_Admins.this, PinakaAdmin_ViewAcc.class);
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
        Intent intent = new Intent(PinakaAdmin_Admins.this, PinakaAdmin_Home.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
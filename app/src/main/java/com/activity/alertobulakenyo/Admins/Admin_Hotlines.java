package com.activity.alertobulakenyo.Admins;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.activity.alertobulakenyo.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Admin_Hotlines extends AppCompatActivity {

    Button addNewHot;
    CardView card_Boc, card_Mar, card_Mey, card_SJDM, card_SM;

    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private FirebaseUser user = fAuth.getCurrentUser();
    private String userId = user.getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_hotlines);

        addNewHot = (Button) findViewById (R.id.btnAddNewHot);

        addNewHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Hotlines.this, Admin_AddHotline.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        card_Boc = (CardView) findViewById (R.id.card_Boc);
        card_Mar = (CardView) findViewById (R.id.card_Mar);
        card_Mey = (CardView) findViewById (R.id.card_Mey);
        card_SJDM = (CardView) findViewById (R.id.card_SJDM);
        card_SM = (CardView) findViewById (R.id.card_SM);

        DocumentReference df = fStore.collection("UserData").document(userId);
        df.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.getString("adminCity").equals("Bocaue")) {
                            card_Boc.setVisibility(View.VISIBLE);
                            card_Mar.setVisibility(View.GONE);
                            card_Mey.setVisibility(View.GONE);
                            card_SJDM.setVisibility(View.GONE);
                            card_SM.setVisibility(View.GONE);

                            card_Boc.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent intent = new Intent(Admin_Hotlines.this, Admin_HotBocaue.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_right,
                                            R.anim.slide_out_left);
                                }
                            });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Marilao")) {
                            card_Boc.setVisibility(View.GONE);
                            card_Mar.setVisibility(View.VISIBLE);
                            card_Mey.setVisibility(View.GONE);
                            card_SJDM.setVisibility(View.GONE);
                            card_SM.setVisibility(View.GONE);

                            card_Mar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent intent = new Intent(Admin_Hotlines.this, Admin_HotMarilao.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_right,
                                            R.anim.slide_out_left);
                                }
                            });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Meycauayan")) {
                            card_Boc.setVisibility(View.GONE);
                            card_Mar.setVisibility(View.GONE);
                            card_Mey.setVisibility(View.VISIBLE);
                            card_SJDM.setVisibility(View.GONE);
                            card_SM.setVisibility(View.GONE);

                            card_Mey.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent intent = new Intent(Admin_Hotlines.this, Admin_HotMeycauayan.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_right,
                                            R.anim.slide_out_left);
                                }
                            });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("San Jose del Monte")) {
                            card_Boc.setVisibility(View.GONE);
                            card_Mar.setVisibility(View.GONE);
                            card_Mey.setVisibility(View.GONE);
                            card_SJDM.setVisibility(View.VISIBLE);
                            card_SM.setVisibility(View.GONE);

                            card_SJDM.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent intent = new Intent(Admin_Hotlines.this, Admin_HotSJDM.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_right,
                                            R.anim.slide_out_left);
                                }
                            });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Santa Maria")) {
                            card_Boc.setVisibility(View.GONE);
                            card_Mar.setVisibility(View.GONE);
                            card_Mey.setVisibility(View.GONE);
                            card_SJDM.setVisibility(View.GONE);
                            card_SM.setVisibility(View.VISIBLE);

                            card_SM.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent intent = new Intent(Admin_Hotlines.this, Admin_HotStaMaria.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_right,
                                            R.anim.slide_out_left);
                                }
                            });
                        }
                    }
                });

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Admin_Home.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
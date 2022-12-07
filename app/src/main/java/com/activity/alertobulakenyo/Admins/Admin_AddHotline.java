package com.activity.alertobulakenyo.Admins;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.activity.alertobulakenyo.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Admin_AddHotline extends AppCompatActivity {

    EditText etHotName, etHot01, etHot02, etHot03, etHot04, etHot05;
    Button btnSaveHot;
    ProgressBar progressBar;

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

        setContentView(R.layout.activity_admin_add_hotline);

        progressBar = (ProgressBar) findViewById (R.id.progressBar);

        etHotName = (EditText) findViewById (R.id.etHotName);
        etHot01 = (EditText) findViewById (R.id.etHot01);
        etHot02 = (EditText) findViewById (R.id.etHot02);
        etHot03 = (EditText) findViewById (R.id.etHot03);
        etHot04 = (EditText) findViewById (R.id.etHot04);
        etHot05 = (EditText) findViewById (R.id.etHot05);

        btnSaveHot = (Button) findViewById (R.id.btnSaveHot);
        btnSaveHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                addHotlines();
            }
        });
    }

    private void addHotlines() {

        DocumentReference df = fStore.collection("UserData").document(userId);
        df.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.getString("adminCity").equals("Bacaue")) {
                            DocumentReference df2 = fStore.collection("Hotlines").document();
                            Map<String, Object> hotlines = new HashMap<>();
                            hotlines.put("hotlineCity", "Bocaue");
                            hotlines.put("hotlineName", etHotName.getText().toString());
                            hotlines.put("hotlineOne", etHot01.getText().toString());
                            hotlines.put("hotlineTwo", etHot02.getText().toString());
                            hotlines.put("hotlineThree", etHot03.getText().toString());
                            hotlines.put("hotlineFour", etHot04.getText().toString());
                            hotlines.put("hotlineFive", etHot05.getText().toString());

                            df2.set(hotlines)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            //pb
                                            Toast.makeText(Admin_AddHotline.this, "Hotline Saved!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), Admin_Hotlines.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            //progress
                                            Toast.makeText(Admin_AddHotline.this, "Failed to save hotline.", Toast.LENGTH_SHORT).show();
                                            Log.e("TAG", "ANNOUNCEMENT POSTING FAIL"  + e.getMessage() );
                                        }
                                    });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Marilao")) {

                            DocumentReference df2 = fStore.collection("Hotlines").document();
                            Map<String, Object> hotlines = new HashMap<>();
                            hotlines.put("hotlineCity", "Marilao");
                            hotlines.put("hotlineName", etHotName.getText().toString());
                            hotlines.put("hotlineOne", etHot01.getText().toString());
                            hotlines.put("hotlineTwo", etHot02.getText().toString());
                            hotlines.put("hotlineThree", etHot03.getText().toString());
                            hotlines.put("hotlineFour", etHot04.getText().toString());
                            hotlines.put("hotlineFive", etHot05.getText().toString());

                            df2.set(hotlines)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            //pb
                                            Toast.makeText(Admin_AddHotline.this, "Hotline Saved!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), Admin_Hotlines.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            //progress
                                            Toast.makeText(Admin_AddHotline.this, "Failed to save hotline.", Toast.LENGTH_SHORT).show();
                                            Log.e("TAG", "ANNOUNCEMENT POSTING FAIL"  + e.getMessage() );
                                        }
                                    });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Meycauayan")) {

                            DocumentReference df2 = fStore.collection("Hotlines").document();
                            Map<String, Object> hotlines = new HashMap<>();
                            hotlines.put("hotlineCity", "Meycauayan");
                            hotlines.put("hotlineName", etHotName.getText().toString());
                            hotlines.put("hotlineOne", etHot01.getText().toString());
                            hotlines.put("hotlineTwo", etHot02.getText().toString());
                            hotlines.put("hotlineThree", etHot03.getText().toString());
                            hotlines.put("hotlineFour", etHot04.getText().toString());
                            hotlines.put("hotlineFive", etHot05.getText().toString());

                            df2.set(hotlines)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            //pb
                                            Toast.makeText(Admin_AddHotline.this, "Hotline Saved!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), Admin_Hotlines.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            //progress
                                            Toast.makeText(Admin_AddHotline.this, "Failed to save hotline.", Toast.LENGTH_SHORT).show();
                                            Log.e("TAG", "ANNOUNCEMENT POSTING FAIL"  + e.getMessage() );
                                        }
                                    });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("San Jose del Monte")) {

                            DocumentReference df2 = fStore.collection("Hotlines").document();
                            Map<String, Object> hotlines = new HashMap<>();
                            hotlines.put("hotlineCity", "San Jose del Monte");
                            hotlines.put("hotlineName", etHotName.getText().toString());
                            hotlines.put("hotlineOne", etHot01.getText().toString());
                            hotlines.put("hotlineTwo", etHot02.getText().toString());
                            hotlines.put("hotlineThree", etHot03.getText().toString());
                            hotlines.put("hotlineFour", etHot04.getText().toString());
                            hotlines.put("hotlineFive", etHot05.getText().toString());

                            df2.set(hotlines)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            //pb
                                            Toast.makeText(Admin_AddHotline.this, "Hotline Saved!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), Admin_Hotlines.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            //progress
                                            Toast.makeText(Admin_AddHotline.this, "Failed to save hotline.", Toast.LENGTH_SHORT).show();
                                            Log.e("TAG", "ANNOUNCEMENT POSTING FAIL"  + e.getMessage() );
                                        }
                                    });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Santa Maria")) {

                            DocumentReference df2 = fStore.collection("Hotlines").document();
                            Map<String, Object> hotlines = new HashMap<>();
                            hotlines.put("hotlineCity", "Santa Maria");
                            hotlines.put("hotlineName", etHotName.getText().toString());
                            hotlines.put("hotlineOne", etHot01.getText().toString());
                            hotlines.put("hotlineTwo", etHot02.getText().toString());
                            hotlines.put("hotlineThree", etHot03.getText().toString());
                            hotlines.put("hotlineFour", etHot04.getText().toString());
                            hotlines.put("hotlineFive", etHot05.getText().toString());

                            df2.set(hotlines)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            //pb
                                            Toast.makeText(Admin_AddHotline.this, "Hotline Saved!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), Admin_Hotlines.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            //progress
                                            Toast.makeText(Admin_AddHotline.this, "Failed to save hotline.", Toast.LENGTH_SHORT).show();
                                            Log.e("TAG", "ANNOUNCEMENT POSTING FAIL"  + e.getMessage() );
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
        Intent intent = new Intent(getApplicationContext(), Admin_Hotlines.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
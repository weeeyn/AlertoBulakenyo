package com.activity.alertobulakenyo.Admins;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Admin_CreateAncmnt extends AppCompatActivity {

    EditText etAncmntTitle, etAncmnt;
    Button btnPost;
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

        setContentView(R.layout.activity_admin_create_ancmnt);

        progressBar = (ProgressBar) findViewById (R.id.progressBar);

        etAncmntTitle = (EditText) findViewById (R.id.etAncmntTitle);
        etAncmnt = (EditText) findViewById (R.id.etAncmnt);

        btnPost = (Button) findViewById (R.id.btnPost);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                createAnnouncement();
            }
        });

    }

    private void createAnnouncement() {

        DocumentReference df = fStore.collection("UserData").document(userId);
        df.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.getString("adminCity").equals("Bocaue")) {
                            String title = etAncmntTitle.getText().toString();
                            String body = etAncmnt.getText().toString();
                            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyy - HH:mm aa", Locale.getDefault());
                            SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yy", Locale.getDefault());
                            String anncmntDate = sdf2.format(new Date());
                            String anncmntDateTime = sdf.format(new Date());
                            if(TextUtils.isEmpty(title)) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Admin_CreateAncmnt.this, "Please input an Announcement Title.", Toast.LENGTH_SHORT).show();
                                etAncmntTitle.setError("Announcement Title can't be empty!");
                                etAncmntTitle.requestFocus();
                            } else if(TextUtils.isEmpty(body)) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Admin_CreateAncmnt.this, "Please input an Announcement Body.", Toast.LENGTH_SHORT).show();
                                etAncmnt.setError("Announcement Body can't be empty!");
                                etAncmnt.requestFocus();
                            } else {
                                DocumentReference df2 = fStore.collection("Announcements").document();
                                Map<String, Object> anncmnt = new HashMap<>();
                                anncmnt.put("title", title);
                                anncmnt.put("body", body);
                                anncmnt.put("anncmntCity", "Bocaue");
                                anncmnt.put("anncmntDate", anncmntDate);
                                anncmnt.put("anncmntDateTime", anncmntDateTime);
                                df2.set(anncmnt)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(Admin_CreateAncmnt.this, "Announcement Posted!", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext(), Admin_Announcement.class);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.slide_in_left,
                                                        R.anim.slide_out_right);
                                                finish();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(Admin_CreateAncmnt.this, "Announcement Failed to Post.", Toast.LENGTH_SHORT).show();
                                                Log.e("TAG", "ANNOUNCEMENT POSTING FAIL"  + e.getMessage() );
                                            }
                                        });
                            }
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Marilao")) {
                            String title = etAncmntTitle.getText().toString();
                            String body = etAncmnt.getText().toString();
                            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyy - HH:mm aa", Locale.getDefault());
                            SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yy", Locale.getDefault());
                            String anncmntDate = sdf2.format(new Date());
                            String anncmntDateTime = sdf.format(new Date());
                            if(TextUtils.isEmpty(title)) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Admin_CreateAncmnt.this, "Please input an Announcement Title.", Toast.LENGTH_SHORT).show();
                                etAncmntTitle.setError("Announcement Title can't be empty!");
                                etAncmntTitle.requestFocus();
                            } else if(TextUtils.isEmpty(body)) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Admin_CreateAncmnt.this, "Please input an Announcement Body.", Toast.LENGTH_SHORT).show();
                                etAncmnt.setError("Announcement Body can't be empty!");
                                etAncmnt.requestFocus();
                            } else {
                                DocumentReference df2 = fStore.collection("Announcements").document();
                                Map<String, Object> anncmnt = new HashMap<>();
                                anncmnt.put("title", title);
                                anncmnt.put("body", body);
                                anncmnt.put("anncmntCity", "Marilao");
                                anncmnt.put("anncmntDate", anncmntDate);
                                anncmnt.put("anncmntDateTime", anncmntDateTime);
                                df2.set(anncmnt)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(Admin_CreateAncmnt.this, "Announcement Posted!", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext(), Admin_Announcement.class);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.slide_in_left,
                                                        R.anim.slide_out_right);
                                                finish();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(Admin_CreateAncmnt.this, "Announcement Failed to Post.", Toast.LENGTH_SHORT).show();
                                                Log.e("TAG", "ANNOUNCEMENT POSTING FAIL"  + e.getMessage() );
                                            }
                                        });
                            }
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Meycauayan")) {
                            String title = etAncmntTitle.getText().toString();
                            String body = etAncmnt.getText().toString();
                            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyy - HH:mm aa", Locale.getDefault());
                            SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yy", Locale.getDefault());
                            String anncmntDate = sdf2.format(new Date());
                            String anncmntDateTime = sdf.format(new Date());
                            if(TextUtils.isEmpty(title)) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Admin_CreateAncmnt.this, "Please input an Announcement Title.", Toast.LENGTH_SHORT).show();
                                etAncmntTitle.setError("Announcement Title can't be empty!");
                                etAncmntTitle.requestFocus();
                            } else if(TextUtils.isEmpty(body)) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Admin_CreateAncmnt.this, "Please input an Announcement Body.", Toast.LENGTH_SHORT).show();
                                etAncmnt.setError("Announcement Body can't be empty!");
                                etAncmnt.requestFocus();
                            } else {
                                DocumentReference df2 = fStore.collection("Announcements").document();
                                Map<String, Object> anncmnt = new HashMap<>();
                                anncmnt.put("title", title);
                                anncmnt.put("body", body);
                                anncmnt.put("anncmntCity", "Meycauayan");
                                anncmnt.put("anncmntDate", anncmntDate);
                                anncmnt.put("anncmntDateTime", anncmntDateTime);
                                df2.set(anncmnt)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(Admin_CreateAncmnt.this, "Announcement Posted!", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext(), Admin_Announcement.class);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.slide_in_left,
                                                        R.anim.slide_out_right);
                                                finish();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(Admin_CreateAncmnt.this, "Announcement Failed to Post.", Toast.LENGTH_SHORT).show();
                                                Log.e("TAG", "ANNOUNCEMENT POSTING FAIL"  + e.getMessage() );
                                            }
                                        });
                            }
                        }
                        else if(documentSnapshot.getString("adminCity").equals("San Jose del Monte")) {
                            String title = etAncmntTitle.getText().toString();
                            String body = etAncmnt.getText().toString();
                            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyy - HH:mm aa", Locale.getDefault());
                            SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yy", Locale.getDefault());
                            String anncmntDate = sdf2.format(new Date());
                            String anncmntDateTime = sdf.format(new Date());
                            if(TextUtils.isEmpty(title)) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Admin_CreateAncmnt.this, "Please input an Announcement Title.", Toast.LENGTH_SHORT).show();
                                etAncmntTitle.setError("Announcement Title can't be empty!");
                                etAncmntTitle.requestFocus();
                            } else if(TextUtils.isEmpty(body)) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Admin_CreateAncmnt.this, "Please input an Announcement Body.", Toast.LENGTH_SHORT).show();
                                etAncmnt.setError("Announcement Body can't be empty!");
                                etAncmnt.requestFocus();
                            } else {
                                DocumentReference df2 = fStore.collection("Announcements").document();
                                Map<String, Object> anncmnt = new HashMap<>();
                                anncmnt.put("title", title);
                                anncmnt.put("body", body);
                                anncmnt.put("anncmntCity", "San Jose del Monte");
                                anncmnt.put("anncmntDate", anncmntDate);
                                anncmnt.put("anncmntDateTime", anncmntDateTime);
                                df2.set(anncmnt)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(Admin_CreateAncmnt.this, "Announcement Posted!", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext(), Admin_Announcement.class);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.slide_in_left,
                                                        R.anim.slide_out_right);
                                                finish();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(Admin_CreateAncmnt.this, "Announcement Failed to Post.", Toast.LENGTH_SHORT).show();
                                                Log.e("TAG", "ANNOUNCEMENT POSTING FAIL"  + e.getMessage() );
                                            }
                                        });
                            }
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Santa Maria")) {
                            String title = etAncmntTitle.getText().toString();
                            String body = etAncmnt.getText().toString();
                            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyy - HH:mm aa", Locale.getDefault());
                            SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yy", Locale.getDefault());
                            String anncmntDate = sdf2.format(new Date());
                            String anncmntDateTime = sdf.format(new Date());
                            if(TextUtils.isEmpty(title)) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Admin_CreateAncmnt.this, "Please input an Announcement Title.", Toast.LENGTH_SHORT).show();
                                etAncmntTitle.setError("Announcement Title can't be empty!");
                                etAncmntTitle.requestFocus();
                            } else if(TextUtils.isEmpty(body)) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Admin_CreateAncmnt.this, "Please input an Announcement Body.", Toast.LENGTH_SHORT).show();
                                etAncmnt.setError("Announcement Body can't be empty!");
                                etAncmnt.requestFocus();
                            } else {
                                DocumentReference df2 = fStore.collection("Announcements").document();
                                Map<String, Object> anncmnt = new HashMap<>();
                                anncmnt.put("title", title);
                                anncmnt.put("body", body);
                                anncmnt.put("anncmntCity", "Santa Maria");
                                anncmnt.put("anncmntDate", anncmntDate);
                                anncmnt.put("anncmntDateTime", anncmntDateTime);
                                df2.set(anncmnt)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(Admin_CreateAncmnt.this, "Announcement Posted!", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext(), Admin_Announcement.class);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.slide_in_left,
                                                        R.anim.slide_out_right);
                                                finish();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(Admin_CreateAncmnt.this, "Announcement Failed to Post.", Toast.LENGTH_SHORT).show();
                                                Log.e("TAG", "ANNOUNCEMENT POSTING FAIL"  + e.getMessage() );
                                            }
                                        });
                            }
                        }
                    }
                });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Admin_Announcement.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
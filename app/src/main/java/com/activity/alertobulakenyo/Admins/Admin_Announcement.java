package com.activity.alertobulakenyo.Admins;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


import com.activity.alertobulakenyo.Adapters.Admin_AnnouncementRVAdapter;
import com.activity.alertobulakenyo.ObjectClasses.Announcements;
import com.activity.alertobulakenyo.ObjectClasses.WarningHolder;
import com.activity.alertobulakenyo.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Admin_Announcement extends AppCompatActivity {

    Button btnCreateAncmnt;

    private RecyclerView rvAncmt;
    private ArrayList<Announcements> announcementsArrayList;
    private Admin_AnnouncementRVAdapter admin_announcementRVAdapter;

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

        setContentView(R.layout.activity_admin_announcement);

        btnCreateAncmnt = (Button) findViewById (R.id.btnCreateAncmnt);
        rvAncmt = (RecyclerView) findViewById (R.id.rvAncmt);

        announcementsArrayList = new ArrayList<>();
        rvAncmt.setHasFixedSize(true);
        rvAncmt.setLayoutManager(new LinearLayoutManager(this));
        admin_announcementRVAdapter = new Admin_AnnouncementRVAdapter(announcementsArrayList, this);
        rvAncmt.setAdapter(admin_announcementRVAdapter);

        DocumentReference df = fStore.collection("UserData").document(userId);
        df.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.getString("adminCity").equals("Bocaue")) {

                            fStore.collection("Announcements")
                                    .whereEqualTo("anncmntCity", "Bocaue")
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            if(!queryDocumentSnapshots.isEmpty()) {
                                                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                                for (DocumentSnapshot d : list) {
                                                    Announcements p = d.toObject(Announcements.class);
                                                    p.setId(d.getId());
                                                    announcementsArrayList.add(p);
                                                }
                                                admin_announcementRVAdapter.notifyDataSetChanged();
                                            } else {
                                                Toast.makeText(Admin_Announcement.this, "No Announcements Posted", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e(TAG, "ANNOUNCEMENTS FAIL: " + e.getMessage());
                                        }
                                    });

                        } else if(documentSnapshot.getString("adminCity").equals("Marilao")) {

                            fStore.collection("Announcements")
                                    .whereEqualTo("anncmntCity", "Marilao")
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            if(!queryDocumentSnapshots.isEmpty()) {
                                                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                                for (DocumentSnapshot d : list) {
                                                    Announcements p = d.toObject(Announcements.class);
                                                    p.setId(d.getId());
                                                    announcementsArrayList.add(p);
                                                }
                                                admin_announcementRVAdapter.notifyDataSetChanged();
                                            } else {
                                                Toast.makeText(Admin_Announcement.this, "No Announcements Posted", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e(TAG, "ANNOUNCEMENTS FAIL: " + e.getMessage());
                                        }
                                    });

                        } else if(documentSnapshot.getString("adminCity").equals("Meycauayan")) {

                            fStore.collection("Announcements")
                                    .whereEqualTo("anncmntCity", "Meycauayan")
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            if(!queryDocumentSnapshots.isEmpty()) {
                                                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                                for (DocumentSnapshot d : list) {
                                                    Announcements p = d.toObject(Announcements.class);
                                                    p.setId(d.getId());
                                                    announcementsArrayList.add(p);
                                                }
                                                admin_announcementRVAdapter.notifyDataSetChanged();
                                            } else {
                                                Toast.makeText(Admin_Announcement.this, "No Announcements Posted", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e(TAG, "ANNOUNCEMENTS FAIL: " + e.getMessage());
                                        }
                                    });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("San Jose del Monte")) {

                            fStore.collection("Announcements")
                                    .whereEqualTo("anncmntCity", "San Jose del Monte")
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            if(!queryDocumentSnapshots.isEmpty()) {
                                                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                                for (DocumentSnapshot d : list) {
                                                    Announcements p = d.toObject(Announcements.class);
                                                    p.setId(d.getId());
                                                    announcementsArrayList.add(p);
                                                }
                                                admin_announcementRVAdapter.notifyDataSetChanged();
                                            } else {
                                                Toast.makeText(Admin_Announcement.this, "No Announcements Posted", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e(TAG, "ANNOUNCEMENTS FAIL: " + e.getMessage());
                                        }
                                    });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Santa Maria")) {

                            fStore.collection("Announcements")
                                    .whereEqualTo("anncmntCity", "Santa Maria")
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            if(!queryDocumentSnapshots.isEmpty()) {
                                                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                                for (DocumentSnapshot d : list) {
                                                    Announcements p = d.toObject(Announcements.class);
                                                    p.setId(d.getId());
                                                    announcementsArrayList.add(p);
                                                }
                                                admin_announcementRVAdapter.notifyDataSetChanged();
                                            } else {
                                                Toast.makeText(Admin_Announcement.this, "No Announcements Posted", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e(TAG, "ANNOUNCEMENTS FAIL: " + e.getMessage());
                                        }
                                    });
                        }
                    }
                });


        btnCreateAncmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Announcement.this, Admin_CreateAncmnt.class);
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
        Intent intent = new Intent(getApplicationContext(), Admin_Home.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
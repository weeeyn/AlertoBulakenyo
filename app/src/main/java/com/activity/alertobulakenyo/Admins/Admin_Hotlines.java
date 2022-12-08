package com.activity.alertobulakenyo.Admins;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.activity.alertobulakenyo.Adapters.Admin_HotlinesRVAdapter;
import com.activity.alertobulakenyo.ObjectClasses.HotlinesHolder;
import com.activity.alertobulakenyo.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Admin_Hotlines extends AppCompatActivity {

    Button addNewHot;
    private RecyclerView rvHotline;
    private ArrayList<HotlinesHolder> hotlinesHolderArrayList;
    private Admin_HotlinesRVAdapter admin_hotlinesRVAdapter;

    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private FirebaseUser user = fAuth.getCurrentUser();
    private String userId = user.getUid();


    @SuppressLint("MissingInflatedId")
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

        rvHotline = (RecyclerView) findViewById (R.id.rvHotline);

        hotlinesHolderArrayList = new ArrayList<>();
        rvHotline.setHasFixedSize(true);
        rvHotline.setLayoutManager(new LinearLayoutManager(this));
        admin_hotlinesRVAdapter = new Admin_HotlinesRVAdapter(hotlinesHolderArrayList, this);
        rvHotline.setAdapter(admin_hotlinesRVAdapter);

        DocumentReference df = fStore.collection("UserData").document(userId);
        df.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.getString("adminCity").equals("Bocaue")) {
                            fStore.collection("Hotlines")
                                    .whereEqualTo("hotlineCity", "Bocaue")
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            if (!queryDocumentSnapshots.isEmpty()) {
                                                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                                for (DocumentSnapshot d : list) {
                                                    HotlinesHolder p = d.toObject(HotlinesHolder.class);
                                                    p.setId(d.getId());
                                                    hotlinesHolderArrayList.add(p);
                                                }
                                                admin_hotlinesRVAdapter.notifyDataSetChanged();
                                            } else {
                                                Toast.makeText(Admin_Hotlines.this, "No Hotlines Posted", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e(TAG, "onFailure: MARILAO HOTLINE FAILED" + e.getMessage());
                                        }
                                    });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Marilao")) {
                            fStore.collection("Hotlines")
                                    .whereEqualTo("hotlineCity", "Marilao")
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            if (!queryDocumentSnapshots.isEmpty()) {
                                                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                                for (DocumentSnapshot d : list) {
                                                    HotlinesHolder p = d.toObject(HotlinesHolder.class);
                                                    p.setId(d.getId());
                                                    hotlinesHolderArrayList.add(p);
                                                }
                                                admin_hotlinesRVAdapter.notifyDataSetChanged();
                                            } else {
                                                Toast.makeText(Admin_Hotlines.this, "No Hotlines Posted", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e(TAG, "onFailure: MARILAO HOTLINE FAILED" + e.getMessage());
                                        }
                                    });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Meycauayan")) {
                            fStore.collection("Hotlines")
                                    .whereEqualTo("hotlineCity", "Meycauayan")
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            if (!queryDocumentSnapshots.isEmpty()) {
                                                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                                for (DocumentSnapshot d : list) {
                                                    HotlinesHolder p = d.toObject(HotlinesHolder.class);
                                                    p.setId(d.getId());
                                                    hotlinesHolderArrayList.add(p);
                                                }
                                                admin_hotlinesRVAdapter.notifyDataSetChanged();
                                            } else {
                                                Toast.makeText(Admin_Hotlines.this, "No Hotlines Posted", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e(TAG, "onFailure: MARILAO HOTLINE FAILED" + e.getMessage());
                                        }
                                    });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("San Jose del Monte")) {
                            fStore.collection("Hotlines")
                                    .whereEqualTo("hotlineCity", "San Jose del Monte")
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            if (!queryDocumentSnapshots.isEmpty()) {
                                                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                                for (DocumentSnapshot d : list) {
                                                    HotlinesHolder p = d.toObject(HotlinesHolder.class);
                                                    p.setId(d.getId());
                                                    hotlinesHolderArrayList.add(p);
                                                }
                                                admin_hotlinesRVAdapter.notifyDataSetChanged();
                                            } else {
                                                Toast.makeText(Admin_Hotlines.this, "No Hotlines Posted", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e(TAG, "onFailure: MARILAO HOTLINE FAILED" + e.getMessage());
                                        }
                                    });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Santa Maria")) {
                            fStore.collection("Hotlines")
                                    .whereEqualTo("hotlineCity", "Santa Maria")
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            if (!queryDocumentSnapshots.isEmpty()) {
                                                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                                for (DocumentSnapshot d : list) {
                                                    HotlinesHolder p = d.toObject(HotlinesHolder.class);
                                                    p.setId(d.getId());
                                                    hotlinesHolderArrayList.add(p);
                                                }
                                                admin_hotlinesRVAdapter.notifyDataSetChanged();
                                            } else {
                                                Toast.makeText(Admin_Hotlines.this, "No Hotlines Posted", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e(TAG, "onFailure: MARILAO HOTLINE FAILED" + e.getMessage());
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
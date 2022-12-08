package com.activity.alertobulakenyo.Admins;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.activity.alertobulakenyo.Adapters.Admin_FloodAdapter;
import com.activity.alertobulakenyo.R;
import com.activity.alertobulakenyo.ObjectClasses.WarningHolder;
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

public class Admin_DisasterFlood extends AppCompatActivity {

    private RecyclerView rvDisFlood;
    private ArrayList<WarningHolder> warningHolderArrayList;
    private Admin_FloodAdapter admin_floodAdapter;

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

        setContentView(R.layout.activity_admin_disaster_flood);

        rvDisFlood = (RecyclerView) findViewById (R.id.rvDisFlood);

        warningHolderArrayList = new ArrayList<>();
        rvDisFlood.setHasFixedSize(true);
        rvDisFlood.setLayoutManager(new LinearLayoutManager(this));
        admin_floodAdapter = new Admin_FloodAdapter(warningHolderArrayList, this);
        rvDisFlood.setAdapter(admin_floodAdapter);


        DocumentReference df = fStore.collection("UserData").document(userId);
        df.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.getString("adminCity").equals("Bocaue")) {

                            fStore.collection("Warning")
                                    .whereEqualTo("body", "Bocaue")
                                    .whereEqualTo("title", "FLOOD")
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            if (!queryDocumentSnapshots.isEmpty()) {
                                                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                                for (DocumentSnapshot d : list) {
                                                    WarningHolder p = d.toObject(WarningHolder.class);
                                                    p.setId(d.getId());
                                                    warningHolderArrayList.add(p);
                                                }
                                                admin_floodAdapter.notifyDataSetChanged();
                                            } else {
                                                Toast.makeText(getApplicationContext(), "No Warnings Posted", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e(TAG, "onFailure: FLOOD WARNING FAILED" + e.getMessage());
                                        }
                                    });

                        }
                        else if(documentSnapshot.getString("adminCity").equals("Marilao")) {

                            fStore.collection("Warning")
                                    .whereEqualTo("body", "Marilao")
                                    .whereEqualTo("title", "FLOOD")
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            if (!queryDocumentSnapshots.isEmpty()) {
                                                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                                for (DocumentSnapshot d : list) {
                                                    WarningHolder p = d.toObject(WarningHolder.class);
                                                    p.setId(d.getId());
                                                    warningHolderArrayList.add(p);
                                                }
                                                admin_floodAdapter.notifyDataSetChanged();
                                            } else {
                                                Toast.makeText(getApplicationContext(), "No Warnings Posted", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e(TAG, "onFailure: FLOOD WARNING FAILED" + e.getMessage());
                                        }
                                    });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Meycauayan")) {

                            fStore.collection("Warning")
                                    .whereEqualTo("body", "Meycauayan")
                                    .whereEqualTo("title", "FLOOD")
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            if (!queryDocumentSnapshots.isEmpty()) {
                                                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                                for (DocumentSnapshot d : list) {
                                                    WarningHolder p = d.toObject(WarningHolder.class);
                                                    p.setId(d.getId());
                                                    warningHolderArrayList.add(p);
                                                }
                                                admin_floodAdapter.notifyDataSetChanged();
                                            } else {
                                                Toast.makeText(getApplicationContext(), "No Warnings Posted", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e(TAG, "onFailure: FLOOD WARNING FAILED" + e.getMessage());
                                        }
                                    });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("San Jose del Monte")) {

                            fStore.collection("Warning")
                                    .whereEqualTo("body", "San Jose del Monte")
                                    .whereEqualTo("title", "FLOOD")
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            if (!queryDocumentSnapshots.isEmpty()) {
                                                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                                for (DocumentSnapshot d : list) {
                                                    WarningHolder p = d.toObject(WarningHolder.class);
                                                    p.setId(d.getId());
                                                    warningHolderArrayList.add(p);
                                                }
                                                admin_floodAdapter.notifyDataSetChanged();
                                            } else {
                                                Toast.makeText(getApplicationContext(), "No Warnings Posted", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e(TAG, "onFailure: FLOOD WARNING FAILED" + e.getMessage());
                                        }
                                    });

                        }
                        else if(documentSnapshot.getString("adminCity").equals("Santa Maria")) {

                            fStore.collection("Warning")
                                    .whereEqualTo("body", "Santa Maria")
                                    .whereEqualTo("title", "FLOOD")
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            if (!queryDocumentSnapshots.isEmpty()) {
                                                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                                for (DocumentSnapshot d : list) {
                                                    WarningHolder p = d.toObject(WarningHolder.class);
                                                    p.setId(d.getId());
                                                    warningHolderArrayList.add(p);
                                                }
                                                admin_floodAdapter.notifyDataSetChanged();
                                            } else {
                                                Toast.makeText(getApplicationContext(), "No Warnings Posted", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e(TAG, "onFailure: FLOOD WARNING FAILED" + e.getMessage());
                                        }
                                    });

                        }
                    }
                });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();Intent intent = new Intent(getApplicationContext(), Admin_Disaster.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
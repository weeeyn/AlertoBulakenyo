package com.activity.alertobulakenyo.ResidentUsers;

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

import com.activity.alertobulakenyo.Adapters.AdapterTy;
import com.activity.alertobulakenyo.R;
import com.activity.alertobulakenyo.ObjectClasses.WarningHolder;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Typhoon extends AppCompatActivity {

    private RecyclerView rvDisTy;
    private ArrayList<WarningHolder> warningHolderArrayList;
    private AdapterTy adapterTy;
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_typhoon);

        rvDisTy = (RecyclerView) findViewById (R.id.rvDisTy);
        warningHolderArrayList = new ArrayList<>();
        rvDisTy.setHasFixedSize(true);
        rvDisTy.setLayoutManager(new LinearLayoutManager(this));
        adapterTy = new AdapterTy(warningHolderArrayList, this);
        rvDisTy.setAdapter(adapterTy);

        fStore.collection("Warning")
                .whereEqualTo("title", "TYPHOON")
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
                            adapterTy.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getApplicationContext(), "No Warnings Posted", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: TYPHOON WARNING FAILED" + e.getMessage());
                    }
                });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Disaster.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
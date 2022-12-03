package com.activity.alertobulakenyo;

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

//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class EvacMeycauayan extends AppCompatActivity {

    private RecyclerView rvEvac;
    private ArrayList<EvacuationHolder> evacuationHolderArrayList;
    private EvacuationAdapter evacuationAdapter;
//    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_evac_meycauayan);

        rvEvac = (RecyclerView) findViewById (R.id.rvEvac);

        evacuationHolderArrayList = new ArrayList<>();
        rvEvac.setHasFixedSize(true);
        rvEvac.setLayoutManager(new LinearLayoutManager(this));
        evacuationAdapter = new EvacuationAdapter(evacuationHolderArrayList, this);
        rvEvac.setAdapter(evacuationAdapter);

//        fStore.collection("Evacuation")
//                .whereEqualTo("evacuationCity", "Meycauayan")
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//                        if (!queryDocumentSnapshots.isEmpty()) {
//                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
//                            for (DocumentSnapshot d : list) {
//                                EvacuationHolder p = d.toObject(EvacuationHolder.class);
//                                p.setId(d.getId());
//                                evacuationHolderArrayList.add(p);
//                            }
//                            evacuationAdapter.notifyDataSetChanged();
//                        } else {
//                            Toast.makeText(EvacMeycauayan.this, "No Evacuation Centers Listed.", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e("TAG", "onFailure: " + e.getMessage());
//                    }
//                });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Evacuation.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
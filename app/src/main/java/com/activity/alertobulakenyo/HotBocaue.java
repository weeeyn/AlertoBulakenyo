package com.activity.alertobulakenyo;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HotBocaue extends AppCompatActivity {

    private RecyclerView rvHotBoc;
    private ArrayList<HotlinesHolder> hotlinesHolderArrayList;
    private HotlineAdapter hotlineAdapter;
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_hot_bocaue);

        rvHotBoc = (RecyclerView) findViewById (R.id.rvHotBoc);

        hotlinesHolderArrayList = new ArrayList<>();
        rvHotBoc.setHasFixedSize(true);
        rvHotBoc.setLayoutManager(new LinearLayoutManager(this));
        hotlineAdapter = new HotlineAdapter(hotlinesHolderArrayList, this);
        rvHotBoc.setAdapter(hotlineAdapter);

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
                            hotlineAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getApplicationContext(), "No Hotlines Posted", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: BOCAUE HOTLINE FAILED" + e.getMessage());
                    }
                });
    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Hotlines.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
package com.activity.alertobulakenyo;

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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Admin_HotBocaue extends AppCompatActivity {

    Button btnAddHotline;
    private RecyclerView rvHotBoc;
    private ArrayList<HotlinesHolder> hotlinesHolderArrayList;
    private Admin_HotlinesRVAdapter admin_hotlinesRVAdapter;

    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_hot_bocaue);

        btnAddHotline = (Button) findViewById (R.id.btnAddHotline);

        rvHotBoc = (RecyclerView) findViewById (R.id.rvHotBoc);

        hotlinesHolderArrayList = new ArrayList<>();
        rvHotBoc.setHasFixedSize(true);
        rvHotBoc.setLayoutManager(new LinearLayoutManager(this));
        admin_hotlinesRVAdapter = new Admin_HotlinesRVAdapter(hotlinesHolderArrayList, this);
        rvHotBoc.setAdapter(admin_hotlinesRVAdapter);

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
                            Toast.makeText(Admin_HotBocaue.this, "No Hotlines Posted", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: BOCAUE HOTLINE FAILED" + e.getMessage());
                    }
                });

        btnAddHotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Admin_HotBocaue.this, Admin_AddHotline.class);
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
        Intent intent = new Intent(getApplicationContext(), Admin_Hotlines.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
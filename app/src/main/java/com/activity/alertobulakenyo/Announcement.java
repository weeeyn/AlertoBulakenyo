package com.activity.alertobulakenyo;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Announcement extends AppCompatActivity {

    CardView cardAncmt;
    TextView tvCity;
    private RecyclerView rvAncmt;
    private ArrayList<Announcements> announcementArrayList;
    private AnnouncementRVAdapter announcementRVAdapter;
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_announcement);

        cardAncmt = (CardView) findViewById (R.id.cardAncmt);
        rvAncmt = (RecyclerView) findViewById (R.id.rvAncmt);

        announcementArrayList = new ArrayList<>();
        rvAncmt.setHasFixedSize(true);
        rvAncmt.setLayoutManager(new LinearLayoutManager(this));
        announcementRVAdapter = new AnnouncementRVAdapter(announcementArrayList, this);
        rvAncmt.setAdapter(announcementRVAdapter);

        tvCity = (TextView) findViewById (R.id.tvCity);

        fStore.collection("Announcements")
                .orderBy("anncmntDateTime", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                Announcements p = d.toObject(Announcements.class);
                                p.setId(d.getId());
                                announcementArrayList.add(p);
                            }
                            announcementRVAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(Announcement.this, "No Announcements Posted", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("TAG", "onFailure: " + e.getMessage());
                    }
                });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
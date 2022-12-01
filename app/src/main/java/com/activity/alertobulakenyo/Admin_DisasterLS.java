package com.activity.alertobulakenyo;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Admin_DisasterLS extends AppCompatActivity {

    private RecyclerView rvDisLS;
    private ArrayList<WarningHolder> warningHolderArrayList;
    private Admin_LSAdapter admin_lsAdapter;
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_disaster_ls);

        rvDisLS = (RecyclerView) findViewById (R.id.rvDisLS);
        warningHolderArrayList = new ArrayList<>();
        rvDisLS.setHasFixedSize(true);
        rvDisLS.setLayoutManager(new LinearLayoutManager(this));
        admin_lsAdapter = new Admin_LSAdapter(warningHolderArrayList, this);
        rvDisLS.setAdapter(admin_lsAdapter);

        fStore.collection("Warning")
                .whereEqualTo("disasterType", "LANDSLIDE")
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
                            admin_lsAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getApplicationContext(), "No Warnings Posted", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: EQ WARNING FAILED" + e.getMessage());
                    }
                });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Admin_Disaster.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
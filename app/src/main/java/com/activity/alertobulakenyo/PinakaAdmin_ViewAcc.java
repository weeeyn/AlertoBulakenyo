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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PinakaAdmin_ViewAcc extends AppCompatActivity {

    CardView cardAdminAcc;
    TextView tvDeptAbbv, tvDeptName, tvAdminName;

    private RecyclerView rvAdminAcc;
    private ArrayList<AdminHolder> adminHolderArrayList;
    private PinakaAdmin_ViewAccRVAdapter pinakaAdmin_viewAccRVAdapter;

    //firebase authentication
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private DocumentReference anncmntRef;

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String userId = user.getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_pinaka_admin_view_acc);

        cardAdminAcc = (CardView) findViewById(R.id.cardAdminAcc);

        tvDeptAbbv = (TextView) findViewById(R.id.tvDeptAbbv);
        tvDeptName = (TextView) findViewById(R.id.tvDeptName);
        tvAdminName = (TextView) findViewById(R.id.tvAdminName);

        rvAdminAcc = (RecyclerView) findViewById(R.id.rvAdminAcc);

        adminHolderArrayList = new ArrayList<>();
        rvAdminAcc.setHasFixedSize(true);
        rvAdminAcc.setLayoutManager(new LinearLayoutManager(this));
        pinakaAdmin_viewAccRVAdapter = new PinakaAdmin_ViewAccRVAdapter(adminHolderArrayList, this);
        rvAdminAcc.setAdapter(pinakaAdmin_viewAccRVAdapter);


        fStore.collection("AdminData")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d : list) {
                                    AdminHolder p = d.toObject(AdminHolder.class);
                                    p.setId(d.getId());
                                    adminHolderArrayList.add(p);
                                }
                                pinakaAdmin_viewAccRVAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(PinakaAdmin_ViewAcc.this, "No data found.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "ADMIN ACCOUNTS FAIL: " + e.getMessage());
                    }
                });

    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(PinakaAdmin_ViewAcc.this, PinakaAdmin_Admins.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
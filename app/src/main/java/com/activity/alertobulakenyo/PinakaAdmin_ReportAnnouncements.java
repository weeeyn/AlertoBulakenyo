package com.activity.alertobulakenyo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class PinakaAdmin_ReportAnnouncements extends AppCompatActivity {

    TextView tvNumBoc, tvNumMar, tvNumMey, tvNumSJDM, tvNumSM;
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_pinaka_admin_report_announcements);

        tvNumBoc = (TextView) findViewById(R.id.tvNumBoc);
        tvNumMar = (TextView) findViewById(R.id.tvNumMar);
        tvNumMey = (TextView) findViewById(R.id.tvNumMey);
        tvNumSJDM = (TextView) findViewById(R.id.tvNumSJDM);
        tvNumSM = (TextView) findViewById(R.id.tvNumSM);

        fStore.collection("Announcements")
                .whereEqualTo("anncmntCity", "Bocaue")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumBoc.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Announcements")
                .whereEqualTo("anncmntCity", "Marilao")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numMar = String.valueOf(task.getResult().size());
                            tvNumMar.setText(numMar);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            tvNumMar.setText("NO USERS");
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Announcements")
                .whereEqualTo("anncmntCity", "Meycauayan")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numMey = String.valueOf(task.getResult().size());
                            tvNumMey.setText(numMey);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            tvNumMey.setText("NO USERS");
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Announcements")
                .whereEqualTo("anncmntCity", "San Jose del Monte")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numSJDM = String.valueOf(task.getResult().size());
                            tvNumSJDM.setText(numSJDM);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            tvNumSJDM.setText("NO USERS");
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Announcements")
                .whereEqualTo("anncmntCity", "Santa Maria")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numSM = String.valueOf(task.getResult().size());
                            tvNumSM.setText(numSM);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            tvNumSM.setText("NO USERS");
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(PinakaAdmin_ReportAnnouncements.this, PinakaAdmin_Report.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
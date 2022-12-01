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

public class PinakaAdmin_ReportWarning extends AppCompatActivity {

    TextView tvNumBoc1, tvNumBoc2, tvNumBoc3, tvNumBoc4, tvNumBoc5,
             tvNumMar1, tvNumMar2, tvNumMar3, tvNumMar4, tvNumMar5,
             tvNumMey1, tvNumMey2, tvNumMey3, tvNumMey4, tvNumMey5,
             tvNumSJDM1, tvNumSJDM2, tvNumSJDM3, tvNumSJDM4, tvNumSJDM5,
             tvNumSM1, tvNumSM2, tvNumSM3, tvNumSM4, tvNumSM5;


    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_pinaka_admin_report_warning);

        tvNumBoc1 = (TextView) findViewById(R.id.tvNumBoc1);
        tvNumBoc2 = (TextView) findViewById(R.id.tvNumBoc2);
        tvNumBoc3 = (TextView) findViewById(R.id.tvNumBoc3);
        tvNumBoc4 = (TextView) findViewById(R.id.tvNumBoc4);
        tvNumBoc5 = (TextView) findViewById(R.id.tvNumBoc5);

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "Bocaue")
                .whereEqualTo("disasterType", "EARTHQUAKE")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumBoc1.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "Bocaue")
                .whereEqualTo("disasterType", "FLOOD")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumBoc2.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "Bocaue")
                .whereEqualTo("disasterType", "FIRE")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumBoc3.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "Bocaue")
                .whereEqualTo("disasterType", "LANDSLIDE")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumBoc4.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "Bocaue")
                .whereEqualTo("disasterType", "TYPHOON")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumBoc5.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });


        tvNumMar1 = (TextView) findViewById(R.id.tvNumMar1);
        tvNumMar2 = (TextView) findViewById(R.id.tvNumMar2);
        tvNumMar3 = (TextView) findViewById(R.id.tvNumMar3);
        tvNumMar4 = (TextView) findViewById(R.id.tvNumMar4);
        tvNumMar5 = (TextView) findViewById(R.id.tvNumMar5);

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "Marilao")
                .whereEqualTo("disasterType", "EARTHQUAKE")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumMar1.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "Marilao")
                .whereEqualTo("disasterType", "FLOOD")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumMar2.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "Marilao")
                .whereEqualTo("disasterType", "FIRE")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumMar3.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "Marilao")
                .whereEqualTo("disasterType", "LANDSLIDE")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumMar4.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "Marilao")
                .whereEqualTo("disasterType", "TYPHOON")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumMar5.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });


        tvNumMey1 = (TextView) findViewById(R.id.tvNumMey1);
        tvNumMey2 = (TextView) findViewById(R.id.tvNumMey2);
        tvNumMey3 = (TextView) findViewById(R.id.tvNumMey3);
        tvNumMey4 = (TextView) findViewById(R.id.tvNumMey4);
        tvNumMey5 = (TextView) findViewById(R.id.tvNumMey5);

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "Meycauayan")
                .whereEqualTo("disasterType", "EARTHQUAKE")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumMey1.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "Meycauayan")
                .whereEqualTo("disasterType", "FLOOD")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumMey2.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "Meycauayan")
                .whereEqualTo("disasterType", "FIRE")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumMey3.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "Meycauayan")
                .whereEqualTo("disasterType", "LANDSLIDE")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumMey4.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "Meycauayan")
                .whereEqualTo("disasterType", "TYPHOON")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumMey5.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        tvNumSJDM1 = (TextView) findViewById(R.id.tvNumSJDM1);
        tvNumSJDM2 = (TextView) findViewById(R.id.tvNumSJDM2);
        tvNumSJDM3 = (TextView) findViewById(R.id.tvNumSJDM3);
        tvNumSJDM4 = (TextView) findViewById(R.id.tvNumSJDM4);
        tvNumSJDM5 = (TextView) findViewById(R.id.tvNumSJDM5);

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "San Jose del Monte")
                .whereEqualTo("disasterType", "EARTHQUAKE")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumSJDM1.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "San Jose del Monte")
                .whereEqualTo("disasterType", "FLOOD")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumSJDM2.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "San Jose del Monte")
                .whereEqualTo("disasterType", "FIRE")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumSJDM3.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "San Jose del Monte")
                .whereEqualTo("disasterType", "LANDSLIDE")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumSJDM4.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "San Jose del Monte")
                .whereEqualTo("disasterType", "TYPHOON")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumSJDM5.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        tvNumSM1 = (TextView) findViewById(R.id.tvNumSM1);
        tvNumSM2 = (TextView) findViewById(R.id.tvNumSM2);
        tvNumSM3 = (TextView) findViewById(R.id.tvNumSM3);
        tvNumSM4 = (TextView) findViewById(R.id.tvNumSM4);
        tvNumSM5 = (TextView) findViewById(R.id.tvNumSM5);

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "Santa Maria")
                .whereEqualTo("disasterType", "EARTHQUAKE")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumSM1.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "Santa Maria")
                .whereEqualTo("disasterType", "FLOOD")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumSM2.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "Santa Maria")
                .whereEqualTo("disasterType", "FIRE")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumSM3.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "Santa Maria")
                .whereEqualTo("disasterType", "LANDSLIDE")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumSM4.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("Warning")
                .whereEqualTo("disasterCity", "Santa Maria")
                .whereEqualTo("disasterType", "TYPHOON")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String numBo = String.valueOf(task.getResult().size());
                            tvNumSM5.setText(numBo);
                            Log.d("TAG", task.getResult().size() + "");
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(PinakaAdmin_ReportWarning.this, PinakaAdmin_Report.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
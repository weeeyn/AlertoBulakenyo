package com.activity.alertobulakenyo;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;

public class Admin_Profile extends AppCompatActivity {

    BottomNavigationView bottomNav;
    int k = 0;
    TextView tvDeptAbbre, tvDeptName;
    CardView card_account, card_report;

    //firebase authentication
//    FirebaseAuth fAuth = FirebaseAuth.getInstance();
//    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
//    DocumentReference df;

//    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//    String userId = user.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_profile);

       // df = fStore.collection("UserData").document(userId);

        bottomNav = (BottomNavigationView) findViewById (R.id.bottomNav);

        tvDeptAbbre = (TextView) findViewById (R.id.tvDeptAbbre);
        tvDeptName = (TextView) findViewById (R.id.tvDeptName);

        card_account = (CardView) findViewById (R.id.card_account);
        card_report = (CardView) findViewById (R.id.card_report);

        // Set Home selected
        bottomNav.setSelectedItemId(R.id.profile);

        // Perform item selected listener
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.profile:
                        return true;

                    case R.id.home:
                        startActivity(new Intent (getApplicationContext(), Admin_Home.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.settings:
                        startActivity(new Intent (getApplicationContext(), Admin_Settings.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        card_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Profile.this, Admin_AccountInfo.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        card_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Profile.this, Admin_Report.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

//        DocumentReference docRef = fStore.collection("AdminData").document(userId);
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//
//                    if (document.exists()) {
//                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                        tvDeptAbbre.setText(document.getString("adminCity") + " " + document.getString("adminDeptAbv"));
//                        tvDeptName.setText(document.getString("adminDept"));
//                    }
//                    else {
//                        Log.d(TAG, "No such document");
//                    }
//                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
//                }
//            }
//        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Admin_Home.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
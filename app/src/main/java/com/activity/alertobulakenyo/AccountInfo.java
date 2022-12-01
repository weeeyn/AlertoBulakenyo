package com.activity.alertobulakenyo;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AccountInfo extends AppCompatActivity {

    Button btnEditInfo;
    TextView tvAccFname, tvAccLname, tvAccUser, tvAccEmail, tvAccConNum, tvAccAdd;

    //firebase authentication
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_account_info);

        tvAccFname = (TextView) findViewById (R.id.tvAccFname);
        tvAccLname = (TextView) findViewById (R.id.tvAccLname);
        tvAccUser = (TextView) findViewById (R.id.tvAccUser);
        tvAccEmail = (TextView) findViewById (R.id.tvAccEmail);
        tvAccConNum = (TextView) findViewById (R.id.tvAccConNum);
        tvAccAdd = (TextView) findViewById (R.id.tvAccAdd);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        DocumentReference docRef = fStore.collection("UserData").document(userId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                        tvAccFname.setText(document.getString("FirstName"));
                        tvAccLname.setText(document.getString("LastName"));
                        tvAccUser.setText(document.getString("Username"));
                        tvAccEmail.setText(document.getString("Email"));
                        tvAccConNum.setText(document.getString("Contact"));
                        tvAccAdd.setText(document.getString("HouseAddress") + " "
                                + document.getString("Barangay") + " "
                                + document.getString("City") + " "
                                + document.getString("Province"));
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        btnEditInfo = (Button) findViewById (R.id.btnEditInfo);

        btnEditInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountInfo.this, EditInfo.class);
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
        Intent intent = new Intent(getApplicationContext(), Profile.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
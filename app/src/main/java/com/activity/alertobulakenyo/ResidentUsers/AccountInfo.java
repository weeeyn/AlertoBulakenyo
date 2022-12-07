package com.activity.alertobulakenyo.ResidentUsers;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.activity.alertobulakenyo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AccountInfo extends AppCompatActivity {

    Button btnEditInfo;
    TextView tvAccFname, tvAccLname, tvAccUser, tvAccEmail, tvAccConNum, tvAccAdd;

    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private FirebaseUser user = fAuth.getCurrentUser();
    private String userId = user.getUid();

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


        DocumentReference docRef = fStore.collection("UserData").document(userId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                        tvAccFname.setText(document.getString("resFname"));
                        tvAccLname.setText(document.getString("resLname"));
                        tvAccUser.setText(document.getString("resUsername"));
                        tvAccEmail.setText(document.getString("resEmail"));
                        tvAccConNum.setText(document.getString("resContact"));
                        tvAccAdd.setText(document.getString("resHouse") + ", Brgy. "
                                + document.getString("resBrgy") + ", "
                                + document.getString("resCity"));
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
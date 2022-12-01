package com.activity.alertobulakenyo;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
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
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class PinakaAdmin_AccountInfo extends AppCompatActivity {

    Button btnEdit, btnDelete;
    TextView tvDeptAbbre, tvDeptName, tvCity, tvEmail, tvAdminName;
    Dialog dialog;

    //firebase authentication
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private DocumentReference df;

    private FirebaseAuth mAuth1;
    private FirebaseAuth mAuth2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_pinaka_admin_account_info);

        AdminHolder adminHolder = (AdminHolder) getIntent().getSerializableExtra("admin");

        mAuth1 = FirebaseAuth.getInstance();

        FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                .setDatabaseUrl("alerto-bulakenyo-60ecf")
                .setApiKey("AIzaSyDe3CsU_sguucQX-qXI3P7bpszuAPd1RHA")
                .setApplicationId("alerto-bulakenyo-60ecf").build();

        try { FirebaseApp myApp = FirebaseApp.initializeApp(getApplicationContext(), firebaseOptions, "Alerto Bulakenyo");
            mAuth2 = FirebaseAuth.getInstance(myApp);
        } catch (IllegalStateException e){
            mAuth2 = FirebaseAuth.getInstance(FirebaseApp.getInstance("Alerto Bulakenyo"));
        }

        FirebaseUser user = mAuth2.getCurrentUser();
        String userId = user.getUid();

        df = fStore.collection("AdminData").document(userId);

        btnEdit = (Button) findViewById (R.id.btnEdit);
        btnDelete = (Button) findViewById (R.id.btnDelete);

        tvDeptAbbre = (TextView) findViewById (R.id.tvDeptAbbre);
        tvDeptName = (TextView) findViewById (R.id.tvDeptName);
        tvCity = (TextView) findViewById (R.id.tvCity);
        tvEmail = (TextView) findViewById (R.id.tvEmail);
        tvAdminName = (TextView) findViewById (R.id.tvAdminName);

        tvDeptAbbre.setText(adminHolder.getAdminDeptAbv());
        tvDeptName.setText(adminHolder.getAdminDept());
        tvCity.setText(adminHolder.getAdminCity());
        tvEmail.setText(adminHolder.getAdminEmail());
        tvAdminName.setText(adminHolder.getAdminName());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PinakaAdmin_AccountInfo.this, PinakaAdmin_EditInfo.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAccount(adminHolder);
            }
        });

    }

    private void deleteAccount(AdminHolder adminHolder) {

        FirebaseUser user = mAuth2.getCurrentUser();
        String userId = user.getUid();

        fStore.collection("AdminData").document(userId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "onSuccess: ADMIN ACCOUNT DELETED");
                        Toast.makeText(PinakaAdmin_AccountInfo.this, "Admin Account Deleted Successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), PinakaAdmin_ViewAcc.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: ADMIN ACCOUNT DELETION FAILED" + e.getMessage());
                    }
                });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(PinakaAdmin_AccountInfo.this, PinakaAdmin_ViewAcc.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
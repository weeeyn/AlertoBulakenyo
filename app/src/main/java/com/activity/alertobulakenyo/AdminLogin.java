package com.activity.alertobulakenyo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminLogin extends AppCompatActivity {

    EditText email, password;
    Button btnMain, btnAdmin;
    TextView tvForgotPass, tvSignup;
    ProgressBar progressBar;

    boolean valid = true;

    //firebase authentication
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_login);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        email = (EditText) findViewById (R.id.etLoginEmail);
        password = (EditText) findViewById (R.id.etLoginPass);

        btnAdmin = (Button) findViewById (R.id.btnAdmin);
        btnMain = (Button) findViewById (R.id.btnMainAdmin);

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                checkField(email);
                checkField(password);

                if (valid) {

                    fAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    checkAdminAccessLevel(authResult.getUser().getUid());
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdminLogin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                checkField(email);
                checkField(password);

                if (valid) {

                    fAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    checkMainAdminAccessLevel(authResult.getUser().getUid());
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdminLogin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }

    private void checkAdminAccessLevel(String uid) {
        DocumentReference dfAdmin = fStore.collection("AdminData").document(uid);
        // extract the data from document
        dfAdmin.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG","onSuccess: " + documentSnapshot.getData());

                if (documentSnapshot.getString("Admin") != null) {
                    progressBar.setVisibility(View.GONE);
                    Log.d("TAG", "onSuccess: ADMIN LOGGED IN");
                    Toast.makeText(AdminLogin.this, "ADMIN LOGGED IN", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Admin_Home.class);
                    startActivity(intent);
                    finish();
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(AdminLogin.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onSuccess: NO USER LOGGED IN");
                }
            }
        });
    }

    private void checkMainAdminAccessLevel(String uid) {
        DocumentReference dfAdmin = fStore.collection("MainAdmin").document(uid);
        // extract the data from document
        dfAdmin.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG","onSuccess: " + documentSnapshot.getData());

                if (documentSnapshot.getString("MainAdmin") != null) {
                    progressBar.setVisibility(View.GONE);
                    Log.d("TAG", "onSuccess: MAIN ADMIN LOGGED IN");
                    Toast.makeText(AdminLogin.this, "WELCOME ADMINISTRATOR", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), PinakaAdmin_Home.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(AdminLogin.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    Log.d("TAG", "onSuccess: NO USER LOGGED IN");
                }
            }
        });
    }

    private boolean checkField(EditText textField) {
        if(textField.getText().toString().isEmpty()) {
            textField.setError("Field Cannot be Empty.");
            Toast.makeText(this, "Fields cannot be Empty!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            valid = false;
        } else {
            progressBar.setVisibility(View.GONE);
            valid = true;
        }
        progressBar.setVisibility(View.GONE);
        return valid;
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
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


import com.activity.alertobulakenyo.Admins.Admin_Home;
import com.activity.alertobulakenyo.GetStarted;
import com.activity.alertobulakenyo.R;
import com.activity.alertobulakenyo.ResidentUsers.ForgotPass;
import com.activity.alertobulakenyo.ResidentUsers.Home;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class login extends AppCompatActivity {

    int k = 0;
    EditText email, password;
    Button btnLogin;
    TextView tvForgotPass, tvSignup;
    ProgressBar progressBar;


    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_login);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        email = (EditText) findViewById (R.id.etLoginEmail);
        password = (EditText) findViewById (R.id.etLoginPass);

        btnLogin = (Button) findViewById (R.id.btnLogin);

        tvForgotPass = (TextView) findViewById (R.id.tvForgotPass);
        tvSignup = (TextView) findViewById (R.id.tvSignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                String loginEmail = email.getText().toString();
                String loginPass = password.getText().toString();

                if (loginEmail.isEmpty()) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(login.this, "Please enter your verified Email.", Toast.LENGTH_SHORT).show();
                    email.setError("Email can't be empty!");
                    email.requestFocus();
                    return;
                } else if (loginPass.isEmpty()) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(login.this, "Please enter your password.", Toast.LENGTH_SHORT).show();
                    password.setError("Password can't be empty!");
                    password.requestFocus();
                    return;
                }

                fAuth.signInWithEmailAndPassword(loginEmail, loginPass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = fAuth.getCurrentUser();
                                    if(user.isEmailVerified()) {
                                        DocumentReference dRef = fStore.collection("UserData").document(user.getUid());
                                        dRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                            @Override
                                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                if(documentSnapshot.getString("userType").equals("resident")) {
                                                    progressBar.setVisibility(View.GONE);
                                                    Toast.makeText(login.this, "User Resident Logged In.", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(getApplicationContext(), Home.class);
                                                    startActivity(intent);
                                                    overridePendingTransition(R.anim.slide_in_right,
                                                            R.anim.slide_out_left);
                                                    finish();
                                                } else if (documentSnapshot.getString("userType").equals("admin")) {
                                                    progressBar.setVisibility(View.GONE);
                                                    Toast.makeText(login.this, "Admin Logged In.", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(getApplicationContext(), Admin_Home.class);
                                                    startActivity(intent);
                                                    overridePendingTransition(R.anim.slide_in_right,
                                                            R.anim.slide_out_left);
                                                    finish();
                                                } else {
                                                    progressBar.setVisibility(View.GONE);
                                                    Toast.makeText(login.this, "User not registered or don't have the access. Please sign up to continue logging in.", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    } else {
                                        progressBar.setVisibility(View.GONE);
                                        fAuth.getCurrentUser().sendEmailVerification()
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful()) {
                                                            progressBar.setVisibility(View.GONE);
                                                            Toast.makeText(login.this, "Please verify your email to continue.", Toast.LENGTH_SHORT).show();
                                                        } else {
                                                            progressBar.setVisibility(View.GONE);
                                                            Toast.makeText(login.this, "Email: " + task.getException(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    }
                                }
                                else
                                {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(login.this, "Trouble logging you in. Please check your credentials and/or sign up to continue logging in.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


        tvForgotPass = (TextView) findViewById (R.id.tvForgotPass);
        tvSignup = (TextView) findViewById (R.id.tvSignup);

        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, ForgotPass.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(login.this, GetStarted.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

    }


    @Override
    public void onBackPressed()
    {
        Log.e("My Tags", "onBackPressed");
        k++;
        if (k == 1)
        {
            Toast.makeText(login.this, "Please press again to exit.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            finishAffinity();
            finish();
        }

    }
}
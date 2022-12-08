package com.activity.alertobulakenyo.ResidentUsers;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.activity.alertobulakenyo.R;
import com.activity.alertobulakenyo.login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DeleteAcc extends AppCompatActivity {

    TextView tvAuthenticate;
    EditText etPass;
    Button btnDelAcc, btnAuthenticate;
    ProgressBar progressBar;

    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private DocumentReference df;
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private FirebaseUser user = fAuth.getCurrentUser();
    private String userPwd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_delete_acc);

        progressBar = (ProgressBar) findViewById (R.id.progressBar);

        tvAuthenticate = (TextView) findViewById (R.id.tvAuthenticate);
        etPass = (EditText) findViewById (R.id.etPass);
        btnDelAcc = (Button) findViewById (R.id.btnDelAcc);
        btnAuthenticate = (Button) findViewById (R.id.btnAuthenticate);

        btnDelAcc.setEnabled(false);

        if(user.equals("")) {
            Toast.makeText(DeleteAcc.this, "Something went wrong! User's details is not available.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
            finish();
        } else {
            validateUser(user);
        }
    }

    private void validateUser(FirebaseUser user) {

        btnAuthenticate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPwd = etPass.getText().toString();

                if(TextUtils.isEmpty(userPwd)) {
                    Toast.makeText(DeleteAcc.this, "Please enter your current password to authenticate.", Toast.LENGTH_SHORT).show();
                    etPass.setError("Current password is needed!");
                    etPass.requestFocus();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), userPwd);
                    user.reauthenticate(credential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        progressBar.setVisibility(View.GONE);

                                        etPass.setEnabled(false);

                                        btnAuthenticate.setEnabled(false);
                                        btnDelAcc.setEnabled(true);

                                        tvAuthenticate.setText("Password has been verified. " +
                                                "You can now delete your account. Be careful, this action is irreversible.");

                                        Toast.makeText(DeleteAcc.this, "Password has been verified. " +
                                                "You can now delete your account. Be careful, this action is irreversible.",
                                                Toast.LENGTH_SHORT).show();

                                        btnDelAcc.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.orange));

                                        btnDelAcc.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                showAlertDialog();
                                            }
                                        });
                                    } else {
                                        try {
                                            throw task.getException();
                                        } catch (Exception e) {
                                            Toast.makeText(DeleteAcc.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                }
            }
        });
    }

    private void showAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(DeleteAcc.this);
        builder.setTitle("Delete User and Related Data?");
        builder.setMessage("Do you really want to delete your profile and related data? This action is irreversible.");

        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteUser(user);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.orange));
            }
        });
        alertDialog.show();
    }

    private void deleteUser(FirebaseUser user) {

        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            deleteUserData(user);
                            fAuth.signOut();
                            Toast.makeText(DeleteAcc.this, "User has been deleted!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), login.class);
                            startActivity(intent);
                            finish();
                        } else {
                            try {
                                throw task.getException();
                            } catch (Exception e) {
                                Toast.makeText(DeleteAcc.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void deleteUserData(FirebaseUser user) {

        fStore.collection("UserData").document(user.getUid())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), SettingsAccount.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
package com.activity.alertobulakenyo.ResidentUsers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.activity.alertobulakenyo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePass extends AppCompatActivity {

    EditText etOldPass, etNewPass, etConNewPass;
    Button btnValidatePass, btnChangePass;
    ProgressBar progressBar;

    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private FirebaseUser user = fAuth.getCurrentUser();
    private String userPwdCurr;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_change_pass);

        progressBar = (ProgressBar) findViewById (R.id.progressBar);

        etOldPass = (EditText) findViewById (R.id.etOldPass);
        etNewPass = (EditText) findViewById (R.id.etNewPass);
        etConNewPass = (EditText) findViewById (R.id.etConNewPass);

        btnValidatePass = (Button) findViewById (R.id.btnValidatePass);
        btnChangePass = (Button) findViewById (R.id.btnChangePass);

        etNewPass.setEnabled(false);
        etConNewPass.setEnabled(false);
        btnChangePass.setEnabled(false);

        if(user.equals("")) {
            Toast.makeText(this, "Something went wrong! User's details is not available.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
            finish();
        } else {
            validateUser(user);
        }

    }

    private void validateUser(FirebaseUser user) {

        btnValidatePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPwdCurr = etOldPass.getText().toString();

                if(TextUtils.isEmpty(userPwdCurr)) {
                    Toast.makeText(ChangePass.this, "Please enter your current password to authenticate.", Toast.LENGTH_SHORT).show();
                    etOldPass.setError("Current password is needed!");
                    etOldPass.requestFocus();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), userPwdCurr);
                    user.reauthenticate(credential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        progressBar.setVisibility(View.GONE);

                                        etOldPass.setEnabled(false);
                                        etNewPass.setEnabled(true);
                                        etConNewPass.setEnabled(true);

                                        btnValidatePass.setEnabled(false);
                                        btnChangePass.setEnabled(true);

                                        Toast.makeText(ChangePass.this, "Password has been verified." + "You can change password now.", Toast.LENGTH_SHORT).show();

                                        btnChangePass.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.orange));

                                        btnChangePass.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                changePwd(user);
                                            }
                                        });
                                    } else {
                                        try {
                                            throw task.getException();
                                        } catch (Exception e) {
                                            Toast.makeText(ChangePass.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                }
            }
        });
    }

    private void changePwd(FirebaseUser user) {
        String userPwdNew = etNewPass.getText().toString();
        String userConPwdNew = etConNewPass.getText().toString();

        if (TextUtils.isEmpty(userPwdNew)) {
            Toast.makeText(getApplicationContext(), "Please enter a new password.", Toast.LENGTH_SHORT).show();
            etNewPass.setError("New Password is needed!");
            etNewPass.requestFocus();
        } else if (userPwdNew.length() <= 8) {
            Toast.makeText(getApplicationContext(), "Please enter a password with 8 characters or longer.", Toast.LENGTH_SHORT).show();
            etNewPass.setError("Password is too short!");
            etNewPass.requestFocus();
        } else if (TextUtils.isEmpty(userConPwdNew)) {
            Toast.makeText(getApplicationContext(), "Please confirm your new password.", Toast.LENGTH_SHORT).show();
            etConNewPass.setError("New Password do not match!");
            etConNewPass.requestFocus();
        } else if (!userPwdNew.matches(userConPwdNew)) {
            Toast.makeText(getApplicationContext(), "Please re-enter your new password.", Toast.LENGTH_SHORT).show();
            etConNewPass.setError("Password did not match!");
            etConNewPass.requestFocus();
        } else if (userPwdCurr.matches(userPwdNew)) {
            Toast.makeText(getApplicationContext(), "New password cannot be the same as old password.", Toast.LENGTH_SHORT).show();
            etNewPass.setError("Please enter a new password.");
            etNewPass.requestFocus();
        } else {
            progressBar.setVisibility(View.VISIBLE);

            user.updatePassword(userPwdNew)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(ChangePass.this, "Password changed successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ChangePass.this, Profile.class);
                                startActivity(intent);
                                finish();
                            } else {
                                try {
                                    throw task.getException();
                                } catch (Exception e) {
                                    Toast.makeText(ChangePass.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    });
        }
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
package com.activity.alertobulakenyo;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import org.w3c.dom.Text;

import java.net.Inet4Address;

public class login extends AppCompatActivity {

    EditText email, password;
    Button btnLogin, btnAdmin;
    TextView tvForgotPass, tvSignup;
   // ProgressBar progressBar;

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

        setContentView(R.layout.activity_login);

       // progressBar = (ProgressBar) findViewById(R.id.progressBar);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        email = (EditText) findViewById (R.id.etLoginEmail);
        password = (EditText) findViewById (R.id.etLoginPass);

        btnLogin = (Button) findViewById (R.id.btnLogin);
        btnAdmin = (Button) findViewById (R.id.btnAdmin);

        tvForgotPass = (TextView) findViewById (R.id.tvForgotPass);
        tvSignup = (TextView) findViewById (R.id.tvSignup);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // progressBar.setVisibility(View.VISIBLE);

                checkField(email);
                checkField(password);

                if (valid) {

                    fAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    checkUserAccessLevel(authResult.getUser().getUid());
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
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

                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });


    }


    private void checkUserAccessLevel(String uid) {
        DocumentReference df = fStore.collection("UserData").document(uid);
        // extract the data from document
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG","onSuccess: " + documentSnapshot.getData());

                if (documentSnapshot.getString("User") != null) {
                   // progressBar.setVisibility(View.GONE);
                    Log.d("TAG", "onSuccess: USER LOGGED IN");
                    Toast.makeText(login.this, "USER LOGGED IN", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                    finish();
                }
                else {
                   // progressBar.setVisibility(View.GONE);
                    Toast.makeText(login.this, "Not a User. Please register to Login.", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onSuccess: NO USER LOGGED IN");
                }
            }
        });
    }

    private boolean checkField(EditText textField) {
        if(textField.getText().toString().isEmpty()) {
            textField.setError("Field Cannot be Empty.");
            Toast.makeText(this, "Fields cannot be Empty!", Toast.LENGTH_SHORT).show();
         //   progressBar.setVisibility(View.GONE);
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Login_Choose.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
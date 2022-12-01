package com.activity.alertobulakenyo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PinakaAdmin_CreateAcc extends AppCompatActivity {

    TextInputLayout tilCity;
    AutoCompleteTextView actCity;
    EditText etDeptName, etDeptAbbv, etEmail, etPass, etAdminName;
    Button btnSave;

    private FirebaseFirestore fStore;
    private FirebaseAuth mAuth1;
    private FirebaseAuth mAuth2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_pinaka_admin_create_acc);

        //fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

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

        tilCity = (TextInputLayout) findViewById (R.id.tilCity);

        actCity = (AutoCompleteTextView) findViewById (R.id.actCity);

        etDeptAbbv = (EditText) findViewById (R.id.etDeptAbbv);
        etDeptName = (EditText) findViewById (R.id.etDeptName);
        etEmail = (EditText) findViewById (R.id.etEmail);
        etPass = (EditText) findViewById (R.id.etPass);
        etAdminName = (EditText) findViewById (R.id.etAdminName);

        btnSave = (Button) findViewById (R.id.btnSave);

        String [] city = {"Bocaue", "Marilao", "Meycauayan", "San Jose del Monte", "Santa Maria"};

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(PinakaAdmin_CreateAcc.this, R.layout.dropdown_items, city);
        actCity.setDropDownBackgroundResource(R.color.white);
        actCity.setAdapter(cityAdapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mAuth2.createUserWithEmailAndPassword(etEmail.getText().toString(),etPass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                FirebaseUser user = mAuth2.getCurrentUser();
                                String userId = user.getUid();

                                DocumentReference df = fStore.collection("AdminData").document(userId);
                                Map<String,Object> userInfo = new HashMap<>();
                                userInfo.put("adminName",etAdminName.getText().toString());
                                userInfo.put("adminEmail",etEmail.getText().toString());
                                userInfo.put("adminDept",etDeptName.getText().toString());
                                userInfo.put("adminDeptAbv",etDeptAbbv.getText().toString());
                                userInfo.put("adminCity",actCity.getText().toString());
                                userInfo.put("adminPassword",etPass.getText().toString());

                                //specify user access (if user or admin)
                                userInfo.put("Admin","1");

                                df.set(userInfo);

                                Toast.makeText(PinakaAdmin_CreateAcc.this, "Admin Account Created Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), PinakaAdmin_ViewAcc.class);
                                startActivity(intent);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PinakaAdmin_CreateAcc.this, "Registration Failed" , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(PinakaAdmin_CreateAcc.this, PinakaAdmin_Admins.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
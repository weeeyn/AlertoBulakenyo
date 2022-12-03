package com.activity.alertobulakenyo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.FirebaseFirestoreException;
//import com.google.firebase.firestore.Transaction;

public class PinakaAdmin_EditInfo extends AppCompatActivity {

    TextInputLayout tilCity;
    AutoCompleteTextView actCity;
    EditText etDeptName, etDeptAbbv, etEmail, etAdminName;
    Button btnSave;

    //firebase authentication
//    FirebaseAuth fAuth = FirebaseAuth.getInstance();
//    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
//    DocumentReference df;


//    private FirebaseAuth mAuth1;
//    private FirebaseAuth mAuth2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_pinaka_admin_edit_info);

//        mAuth1 = FirebaseAuth.getInstance();
//
//        FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
//                .setDatabaseUrl("alerto-bulakenyo-60ecf")
//                .setApiKey("AIzaSyDe3CsU_sguucQX-qXI3P7bpszuAPd1RHA")
//                .setApplicationId("alerto-bulakenyo-60ecf").build();
//
//        try { FirebaseApp myApp = FirebaseApp.initializeApp(getApplicationContext(), firebaseOptions, "Alerto Bulakenyo");
//            mAuth2 = FirebaseAuth.getInstance(myApp);
//        } catch (IllegalStateException e){
//            mAuth2 = FirebaseAuth.getInstance(FirebaseApp.getInstance("Alerto Bulakenyo"));
//        }
//
//        FirebaseUser user = mAuth2.getCurrentUser();
//        String userId = user.getUid();
//
//        df = fStore.collection("AdminData").document(userId);

        tilCity = (TextInputLayout) findViewById (R.id.tilCity);

        actCity = (AutoCompleteTextView) findViewById (R.id.actCity);

        etDeptName = (EditText) findViewById (R.id.etDeptAbbv);
        etDeptAbbv = (EditText) findViewById (R.id.etDeptName);
        etEmail = (EditText) findViewById (R.id.etEmail);
        etAdminName = (EditText) findViewById (R.id.etAdminName);

        btnSave = (Button) findViewById (R.id.btnSave);

        String [] city = {"Bocaue", "Marilao", "Meycauayan", "San Jose del Monte", "Santa Maria"};

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(PinakaAdmin_EditInfo.this, R.layout.dropdown_items, city);
        actCity.setDropDownBackgroundResource(R.color.white);
        actCity.setAdapter(cityAdapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 editProfile();
            }
        });
    }

//    private void editProfile() {
//
//        String editCity = actCity.getText().toString();
//        String editDept = etDeptName.getText().toString();
//        String editDeptAbv = etDeptAbbv.getText().toString();
//        String editEmail = etEmail.getText().toString();
//        String editName = etAdminName.getText().toString();
//
//        FirebaseUser user = mAuth2.getCurrentUser();
//        String userId = user.getUid();
//
//        final DocumentReference sDoc = fStore.collection("AdminData").document(userId);
//
//                fStore.runTransaction(new Transaction.Function<Void>() {
//                    @Override
//                    public Void apply(Transaction transaction) throws FirebaseFirestoreException {
//                        DocumentSnapshot snapshot = transaction.get(sDoc);
//
//                        transaction.update(sDoc, "adminCity", editCity);
//                        transaction.update(sDoc, "adminDept", editDept);
//                        transaction.update(sDoc, "adminDeptAbv", editDeptAbv);
//                        transaction.update(sDoc, "adminEmail", editEmail);
//                        transaction.update(sDoc, "adminName", editName);
//
//                        return null;
//                    }
//                })
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.d("TAG", "Transaction success!");
//                        Toast.makeText(PinakaAdmin_EditInfo.this, "Profile Updated!", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getApplicationContext(), PinakaAdmin_ViewAcc.class);
//                        startActivity(intent);
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.d("TAG", "Transaction failure.", e);
//                        Toast.makeText(PinakaAdmin_EditInfo.this, "Profile DID NOT Update.", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        df.get()
//                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//
//                        if(task.getResult().exists()) {
//
//                            String adminName = task.getResult().getString("adminName");
//                            String adminCity = task.getResult().getString("adminCity");
//                            String adminDept = task.getResult().getString("adminDept");
//                            String adminDeptAbv = task.getResult().getString("adminDeptAbv");
//                            String adminEmail = task.getResult().getString("adminEmail");
//
//                            etAdminName.setText(adminName);
//                            actCity.setText(adminCity);
//                            etDeptName.setText(adminDept);
//                            etDeptAbbv.setText(adminDeptAbv);
//                            etEmail.setText(adminEmail);
//
//                        }
//                        else {
//                            Toast.makeText(PinakaAdmin_EditInfo.this, "User do no Exist.", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                });
//    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(PinakaAdmin_EditInfo.this, PinakaAdmin_AccountInfo.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
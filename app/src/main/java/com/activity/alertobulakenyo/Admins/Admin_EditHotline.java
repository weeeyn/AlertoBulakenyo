package com.activity.alertobulakenyo.Admins;

import static android.content.ContentValues.TAG;

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

import com.activity.alertobulakenyo.ObjectClasses.Announcements;
import com.activity.alertobulakenyo.ObjectClasses.HotlinesHolder;
import com.activity.alertobulakenyo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Admin_EditHotline extends AppCompatActivity {

    EditText etHotName, etHot01, etHot02, etHot03, etHot04, etHot05;
    Button btnSaveChanges, btnDeleteHot;
    TextInputLayout tilCity;
    AutoCompleteTextView actCity;

    private String hotlineCity, hotlineName, hotlineOne, hotlineTwo, hotlineThree, hotlineFour, hotlineFive;

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

        setContentView(R.layout.activity_admin_edit_hotline);

        HotlinesHolder hotlinesHolder = (HotlinesHolder) getIntent().getSerializableExtra("hotline");

        etHotName = (EditText) findViewById (R.id.etHotName);
        etHot01 = (EditText) findViewById (R.id.etHot01);
        etHot02 = (EditText) findViewById (R.id.etHot02);
        etHot03 = (EditText) findViewById (R.id.etHot03);
        etHot04 = (EditText) findViewById (R.id.etHot04);
        etHot05 = (EditText) findViewById (R.id.etHot05);

        etHotName.setText(hotlinesHolder.getHotlineName());
        etHot01.setText(hotlinesHolder.getHotlineOne());
        etHot02.setText(hotlinesHolder.getHotlineTwo());
        etHot03.setText(hotlinesHolder.getHotlineThree());
        etHot04.setText(hotlinesHolder.getHotlineFour());
        etHot05.setText(hotlinesHolder.getHotlineFive());

        tilCity = (TextInputLayout) findViewById (R.id.tilCity);
        actCity = (AutoCompleteTextView) findViewById (R.id.actCity);

        String [] city = {"Bocaue", "Marilao", "Meycauayan", "San Jose del Monte", "Santa Maria"};
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(Admin_EditHotline.this, R.layout.dropdown_items, city);
        actCity.setDropDownBackgroundResource(R.color.white);
        actCity.setAdapter(cityAdapter);

        btnSaveChanges = (Button) findViewById (R.id.btnSaveChanges);
        btnDeleteHot = (Button) findViewById (R.id.btnDeleteHot);

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editHotline(hotlinesHolder, hotlineCity, hotlineName, hotlineOne, hotlineTwo, hotlineThree, hotlineFour, hotlineFive);

            }
        });

        btnDeleteHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteHotline(hotlinesHolder);
            }
        });
    }

    private void deleteHotline(HotlinesHolder hotlinesHolder) {

        fStore.collection("Hotlines")
                .document(hotlinesHolder.getId())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Admin_EditHotline.this, "Hotline Deleted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Admin_EditHotline.this, Admin_Hotlines.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(R.anim.slide_in_left,
                                    R.anim.slide_out_right);
                        } else {
                            Toast.makeText(Admin_EditHotline.this, "Hotline Not Deleted!", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "onFailure: FAILED TO DELETE HOTLINE");
                        }
                    }
                });
    }

    private void editHotline(@NonNull HotlinesHolder hotlinesHolder, String hotlineCity, String hotlineName, String hotlineOne, String hotlineTwo, String hotlineThree, String hotlineFour, String hotlineFive) {

        DocumentReference df1 = fStore.collection("UserData").document(userId);
        df1.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.getString("adminCity").equals("Bacaue")) {

                            String hotlineCity = "Bocaue";
                            String hotlineName = etHotName.getText().toString();
                            String hotlineOne = etHot01.getText().toString();
                            String hotlineTwo = etHot02.getText().toString();
                            String hotlineThree = etHot03.getText().toString();
                            String hotlineFour = etHot04.getText().toString();
                            String hotlineFive = etHot05.getText().toString();

                            HotlinesHolder editHotlines = new HotlinesHolder(hotlineCity, hotlineName, hotlineOne, hotlineTwo, hotlineThree, hotlineFour, hotlineFive);

                            fStore.collection("Hotlines")
                                    .document(hotlinesHolder.getId())
                                    .set(editHotlines)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Admin_EditHotline.this, "Hotline Updated", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Admin_EditHotline.this, Admin_Hotlines.class);
                                            startActivity(intent);
                                            finish();
                                            overridePendingTransition(R.anim.slide_in_left,
                                                    R.anim.slide_out_right);
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Admin_EditHotline.this, "Hotlines Failed to Update", Toast.LENGTH_SHORT).show();
                                            Log.e("TAG", "onFailure: HOTLINE UPDATE FAIL" + e.getMessage());
                                        }
                                    });

                        }
                        else if(documentSnapshot.getString("adminCity").equals("Marilao")) {

                            String hotlineCity = "Marilao";
                            String hotlineName = etHotName.getText().toString();
                            String hotlineOne = etHot01.getText().toString();
                            String hotlineTwo = etHot02.getText().toString();
                            String hotlineThree = etHot03.getText().toString();
                            String hotlineFour = etHot04.getText().toString();
                            String hotlineFive = etHot05.getText().toString();

                            HotlinesHolder editHotlines = new HotlinesHolder(hotlineCity, hotlineName, hotlineOne, hotlineTwo, hotlineThree, hotlineFour, hotlineFive);

                            fStore.collection("Hotlines")
                                    .document(hotlinesHolder.getId())
                                    .set(editHotlines)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Admin_EditHotline.this, "Hotline Updated", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Admin_EditHotline.this, Admin_Hotlines.class);
                                            startActivity(intent);
                                            finish();
                                            overridePendingTransition(R.anim.slide_in_left,
                                                    R.anim.slide_out_right);
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Admin_EditHotline.this, "Hotlines Failed to Update", Toast.LENGTH_SHORT).show();
                                            Log.e("TAG", "onFailure: HOTLINE UPDATE FAIL" + e.getMessage());
                                        }
                                    });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Meycauayan")) {

                            String hotlineCity = "Meycauayan";
                            String hotlineName = etHotName.getText().toString();
                            String hotlineOne = etHot01.getText().toString();
                            String hotlineTwo = etHot02.getText().toString();
                            String hotlineThree = etHot03.getText().toString();
                            String hotlineFour = etHot04.getText().toString();
                            String hotlineFive = etHot05.getText().toString();

                            HotlinesHolder editHotlines = new HotlinesHolder(hotlineCity, hotlineName, hotlineOne, hotlineTwo, hotlineThree, hotlineFour, hotlineFive);

                            fStore.collection("Hotlines")
                                    .document(hotlinesHolder.getId())
                                    .set(editHotlines)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Admin_EditHotline.this, "Hotline Updated", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Admin_EditHotline.this, Admin_Hotlines.class);
                                            startActivity(intent);
                                            finish();
                                            overridePendingTransition(R.anim.slide_in_left,
                                                    R.anim.slide_out_right);
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Admin_EditHotline.this, "Hotlines Failed to Update", Toast.LENGTH_SHORT).show();
                                            Log.e("TAG", "onFailure: HOTLINE UPDATE FAIL" + e.getMessage());
                                        }
                                    });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("San Jose del Monte")) {

                            String hotlineCity = "San Jose del Monte";
                            String hotlineName = etHotName.getText().toString();
                            String hotlineOne = etHot01.getText().toString();
                            String hotlineTwo = etHot02.getText().toString();
                            String hotlineThree = etHot03.getText().toString();
                            String hotlineFour = etHot04.getText().toString();
                            String hotlineFive = etHot05.getText().toString();

                            HotlinesHolder editHotlines = new HotlinesHolder(hotlineCity, hotlineName, hotlineOne, hotlineTwo, hotlineThree, hotlineFour, hotlineFive);

                            fStore.collection("Hotlines")
                                    .document(hotlinesHolder.getId())
                                    .set(editHotlines)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Admin_EditHotline.this, "Hotline Updated", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Admin_EditHotline.this, Admin_Hotlines.class);
                                            startActivity(intent);
                                            finish();
                                            overridePendingTransition(R.anim.slide_in_left,
                                                    R.anim.slide_out_right);
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Admin_EditHotline.this, "Hotlines Failed to Update", Toast.LENGTH_SHORT).show();
                                            Log.e("TAG", "onFailure: HOTLINE UPDATE FAIL" + e.getMessage());
                                        }
                                    });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Santa Maria")) {

                            String hotlineCity = "Santa Maria";
                            String hotlineName = etHotName.getText().toString();
                            String hotlineOne = etHot01.getText().toString();
                            String hotlineTwo = etHot02.getText().toString();
                            String hotlineThree = etHot03.getText().toString();
                            String hotlineFour = etHot04.getText().toString();
                            String hotlineFive = etHot05.getText().toString();

                            HotlinesHolder editHotlines = new HotlinesHolder(hotlineCity, hotlineName, hotlineOne, hotlineTwo, hotlineThree, hotlineFour, hotlineFive);

                            fStore.collection("Hotlines")
                                    .document(hotlinesHolder.getId())
                                    .set(editHotlines)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Admin_EditHotline.this, "Hotline Updated", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Admin_EditHotline.this, Admin_Hotlines.class);
                                            startActivity(intent);
                                            finish();
                                            overridePendingTransition(R.anim.slide_in_left,
                                                    R.anim.slide_out_right);
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Admin_EditHotline.this, "Hotlines Failed to Update", Toast.LENGTH_SHORT).show();
                                            Log.e("TAG", "onFailure: HOTLINE UPDATE FAIL" + e.getMessage());
                                        }
                                    });
                        }
                    }
                });


    }

    @Override
    protected void onStart() {
        super.onStart();

        DocumentReference df = fStore.collection("UserData").document(userId);
        df.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if(task.getResult().exists()) {
                            String cityRes = task.getResult().getString("adminCity");

                            actCity.setText(cityRes);
                        }
                        else {
                            Toast.makeText(Admin_EditHotline.this, "User do no Exist.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Admin_Hotlines.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
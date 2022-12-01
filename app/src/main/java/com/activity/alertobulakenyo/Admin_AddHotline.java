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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Admin_AddHotline extends AppCompatActivity {

    EditText etHotName, etHotAbAc, etHot01, etHot02, etHot03, etHot04, etHot05, etHot06, etHot07, etHot08, etHot09, etHot10;
    Button btnSaveHot;
    TextInputLayout tilCity;
    AutoCompleteTextView actCity;

    //firebase firestore
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_add_hotline);

        fStore = FirebaseFirestore.getInstance();

        etHotName = (EditText) findViewById (R.id.etHotName);
        etHotAbAc = (EditText) findViewById (R.id.etHotAbAc);
        etHot01 = (EditText) findViewById (R.id.etHot01);
        etHot02 = (EditText) findViewById (R.id.etHot02);
        etHot03 = (EditText) findViewById (R.id.etHot03);
        etHot04 = (EditText) findViewById (R.id.etHot04);
        etHot05 = (EditText) findViewById (R.id.etHot05);
        etHot06 = (EditText) findViewById (R.id.etHot06);
        etHot07 = (EditText) findViewById (R.id.etHot07);
        etHot08 = (EditText) findViewById (R.id.etHot08);
        etHot09 = (EditText) findViewById (R.id.etHot09);
        etHot10 = (EditText) findViewById (R.id.etHot10);

        tilCity = (TextInputLayout) findViewById (R.id.tilCity);

        actCity = (AutoCompleteTextView) findViewById (R.id.actCity);

        String [] city = {"Bocaue", "Marilao", "Meycauayan", "San Jose del Monte", "Santa Maria"};

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(Admin_AddHotline.this, R.layout.dropdown_items, city);
        actCity.setDropDownBackgroundResource(R.color.white);
        actCity.setAdapter(cityAdapter);

        btnSaveHot = (Button) findViewById (R.id.btnSaveHot);

        btnSaveHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //progressbar visible
                addHotlines();
            }
        });
    }

    private void addHotlines() {

        DocumentReference df = fStore.collection("Hotlines").document();
        Map<String, Object> hotlines = new HashMap<>();
        hotlines.put("hotlineCity", actCity.getText().toString());
        hotlines.put("hotlineName", etHotName.getText().toString());
        hotlines.put("hotlineNameAbv", etHotAbAc.getText().toString());
        hotlines.put("hotlineOne", etHot01.getText().toString());
        hotlines.put("hotlineTwo", etHot02.getText().toString());
        hotlines.put("hotlineThree", etHot03.getText().toString());
        hotlines.put("hotlineFour", etHot04.getText().toString());
        hotlines.put("hotlineFive", etHot05.getText().toString());
        hotlines.put("hotlineSix", etHot06.getText().toString());
        hotlines.put("hotlineSeven", etHot07.getText().toString());
        hotlines.put("hotlineEight", etHot08.getText().toString());
        hotlines.put("hotlineNine", etHot09.getText().toString());
        hotlines.put("hotlineTen", etHot10.getText().toString());

        df.set(hotlines)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //pb
                        Toast.makeText(Admin_AddHotline.this, "Hotline Saved!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Admin_Hotlines.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //progress
                        Toast.makeText(Admin_AddHotline.this, "Failed to save hotline.", Toast.LENGTH_SHORT).show();
                        Log.e("TAG", "ANNOUNCEMENT POSTING FAIL"  + e.getMessage() );
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
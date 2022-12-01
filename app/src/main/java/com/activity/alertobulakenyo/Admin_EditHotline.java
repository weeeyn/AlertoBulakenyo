package com.activity.alertobulakenyo;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Admin_EditHotline extends AppCompatActivity {

    TextInputLayout tilCity;
    EditText etHotName, etHotAbAc, etHot01, etHot02, etHot03, etHot04, etHot05, etHot06, etHot07, etHot08, etHot09, etHot10;
    Button btnSaveChanges, btnDeleteHot;
    AutoCompleteTextView actCity;

    private String hotlineCity, hotlineName, hotlineNameAbv, hotlineOne, hotlineTwo, hotlineThree, hotlineFour, hotlineFive, hotlineSix, hotlineSeven, hotlineEight, hotlineNine, hotlineTen;
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private DocumentReference df;

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

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(Admin_EditHotline.this, R.layout.dropdown_items, city);
        actCity.setDropDownBackgroundResource(R.color.white);
        actCity.setAdapter(cityAdapter);

        actCity.setText(hotlinesHolder.getHotlineCity());
        etHotName.setText(hotlinesHolder.getHotlineName());
        etHotAbAc.setText(hotlinesHolder.getHotlineNameAbv());
        etHot01.setText(hotlinesHolder.getHotlineOne());
        etHot02.setText(hotlinesHolder.getHotlineTwo());
        etHot03.setText(hotlinesHolder.getHotlineThree());
        etHot04.setText(hotlinesHolder.getHotlineFour());
        etHot05.setText(hotlinesHolder.getHotlineFive());
        etHot06.setText(hotlinesHolder.getHotlineSix());
        etHot07.setText(hotlinesHolder.getHotlineSeven());
        etHot08.setText(hotlinesHolder.getHotlineEight());
        etHot09.setText(hotlinesHolder.getHotlineNine());
        etHot10.setText(hotlinesHolder.getHotlineTen());

        btnSaveChanges = (Button) findViewById (R.id.btnSaveChanges);
        btnDeleteHot = (Button) findViewById (R.id.btnDeleteHot);

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hotlineCity = actCity.getText().toString();
                hotlineName = etHotName.getText().toString();
                hotlineNameAbv = etHotAbAc.getText().toString();
                hotlineOne = etHot01.getText().toString();
                hotlineTwo = etHot02.getText().toString();
                hotlineThree = etHot03.getText().toString();
                hotlineFour = etHot04.getText().toString();
                hotlineFive = etHot05.getText().toString();
                hotlineSix = etHot06.getText().toString();
                hotlineSeven = etHot07.getText().toString();
                hotlineEight = etHot08.getText().toString();
                hotlineNine = etHot09.getText().toString();
                hotlineTen = etHot10.getText().toString();

                editHotline(hotlinesHolder, hotlineCity, hotlineName, hotlineNameAbv, hotlineOne, hotlineTwo, hotlineThree, hotlineFour, hotlineFive, hotlineSix, hotlineSeven, hotlineEight, hotlineNine, hotlineTen);

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

    private void editHotline(HotlinesHolder hotlinesHolder, String hotlineCity, String hotlineName, String hotlineNameAbv, String hotlineOne, String hotlineTwo, String hotlineThree, String hotlineFour, String hotlineFive, String hotlineSix, String hotlineSeven, String hotlineEight, String hotlineNine, String hotlineTen) {

        HotlinesHolder editHotlines = new HotlinesHolder(hotlineCity, hotlineName, hotlineNameAbv, hotlineOne, hotlineTwo, hotlineThree, hotlineFour, hotlineFive, hotlineSix, hotlineSeven, hotlineEight, hotlineNine, hotlineTen);

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

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
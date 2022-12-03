package com.activity.alertobulakenyo;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.FirebaseFirestoreException;
//import com.google.firebase.firestore.Transaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Admin_EditAncmnt extends AppCompatActivity {

    EditText etAncmntTitle, etAncmnt;
    Button btnSave, btnDelete;

    private String anncmntCity, anncmntDept, anncmntTitle, anncmntBody, anncmntDate, anncmntDateTime, anncmntStatus;
//    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
//    private DocumentReference df;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_edit_ancmnt);

        Announcements announcements = (Announcements) getIntent().getSerializableExtra("announcements");


        etAncmntTitle = (EditText) findViewById (R.id.etAncmntTitle);
        etAncmnt = (EditText) findViewById (R.id.etAncmnt);

        etAncmntTitle.setText(announcements.getAnncmntTitle());
        etAncmnt.setText(announcements.getAnncmntBody());

        btnSave = (Button) findViewById (R.id.btnSave);
        btnDelete = (Button) findViewById (R.id.btnDelete);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                anncmntCity = actCity.getText().toString();
//                anncmntDept = etDeptName.getText().toString();
//                anncmntTitle = etAncmntTitle.getText().toString();
//                anncmntBody = etAncmnt.getText().toString();
//
//                editAnnouncement(announcements, anncmntCity, anncmntDept, anncmntTitle, anncmntBody, anncmntDate, anncmntDateTime, anncmntStatus);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                deleteAnnouncement(announcements);
            }
        });

    }


//    private void editAnnouncement(@NonNull Announcements announcements, String anncmntCity, String anncmntDept, String anncmntTitle, String anncmntBody, String anncmntDate, String anncmntDateTime, String anncmntStatus) {
//
//
//        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyy - HH:mm aa", Locale.getDefault());
//        SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yy", Locale.getDefault());
//        anncmntDate = sdf2.format(new Date());
//        anncmntDateTime = sdf.format(new Date());
//
//        Announcements editAnnouncement = new Announcements(anncmntCity, anncmntDept, anncmntTitle, anncmntBody, anncmntDate, anncmntDateTime, anncmntStatus);
//
//        fStore.collection("Announcements")
//                .document(announcements.getId())
//                .set(editAnnouncement)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(Admin_EditAncmnt.this, "Announcement has been EDITED!", Toast.LENGTH_SHORT).show();
//
//                        Intent intent = new Intent(Admin_EditAncmnt.this, Admin_Announcement.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e(TAG, "onFailure: ANNOUNCEMENT NOT EDITED" + e.getMessage() );
//                    }
//                });
//    }
//
//    private void deleteAnnouncement(Announcements announcements) {
//
//        fStore.collection("Announcements")
//                .document(announcements.getId())
//                .update("anncmntStatus", "archived")
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(Admin_EditAncmnt.this, "Announcement Archived Successfully!", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(Admin_EditAncmnt.this, Admin_Announcement.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e(TAG, "onFailure:e " + e.getMessage() );
//                    }
//                });
//
////        fStore.collection("Announcements")
////                .document(announcements.getId())
////                .delete()
////                .addOnCompleteListener(new OnCompleteListener<Void>() {
////                    @Override
////                    public void onComplete(@NonNull Task<Void> task) {
////                        if (task.isSuccessful()) {
////                            Toast.makeText(Admin_EditAncmnt.this, "Announcement Deleted!", Toast.LENGTH_SHORT).show();
////                            Intent intent = new Intent(Admin_EditAncmnt.this, Admin_Announcement.class);
////                            startActivity(intent);
////                            finish();
////                        } else {
////                            Toast.makeText(Admin_EditAncmnt.this, "Announcement Not Deleted!", Toast.LENGTH_SHORT).show();
////                            Log.e(TAG, "onFailure: FAILED TO DELETE ANNOUNCEMENT");
////                        }
////                    }
////                });
//    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Admin_Announcement.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
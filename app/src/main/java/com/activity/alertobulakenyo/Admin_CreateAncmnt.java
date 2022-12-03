package com.activity.alertobulakenyo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Admin_CreateAncmnt extends AppCompatActivity {

    TextInputLayout tilCity;
    AutoCompleteTextView actCity;
    EditText etDeptName, etAncmntTitle, etAncmnt;
    Button btnPost;

    //firebase authentication
//    FirebaseAuth fAuth;
//    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_create_ancmnt);


//        fAuth = FirebaseAuth.getInstance();
//        fStore = FirebaseFirestore.getInstance();

        tilCity = (TextInputLayout) findViewById (R.id.tilCity);

        actCity = (AutoCompleteTextView) findViewById (R.id.actCity);
        etDeptName = (EditText) findViewById (R.id.etDeptName);
        etAncmntTitle = (EditText) findViewById (R.id.etAncmntTitle);
        etAncmnt = (EditText) findViewById (R.id.etAncmnt);

        btnPost = (Button) findViewById (R.id.btnPost);

        String [] city = {"Bocaue", "Marilao", "Meycauayan", "San Jose del Monte", "Santa Maria"};

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(Admin_CreateAncmnt.this, R.layout.dropdown_items, city);
        actCity.setDropDownBackgroundResource(R.color.white);
        actCity.setAdapter(cityAdapter);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //progress bar visible
//                createAnnouncement();

            }
        });


    }

//    private void createAnnouncement() {
//        String anncmntTitle = etAncmntTitle.getText().toString();
//        String anncmntDept = etDeptName.getText().toString();
//        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyy - HH:mm aa", Locale.getDefault());
//        SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yy", Locale.getDefault());
//        String anncmntDate = sdf2.format(new Date());
//        String anncmntDateTime = sdf.format(new Date());
//
//        DocumentReference df = fStore.collection("Announcements").document();
//        Map<String, Object> anncmnt = new HashMap<>();
//        anncmnt.put("anncmntTitle", anncmntTitle);
//        anncmnt.put("anncmntBody", etAncmnt.getText().toString());
//        anncmnt.put("anncmntDept", anncmntDept);
//        anncmnt.put("anncmntCity", actCity.getText().toString());
//        anncmnt.put("anncmntDate", anncmntDate);
//        anncmnt.put("anncmntDateTime", anncmntDateTime);
//        anncmnt.put("anncmntStatus", "active");
//
//        df.set(anncmnt)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        //progress
//
//                        NotificationCompat.Builder builder = new NotificationCompat.Builder(Admin_CreateAncmnt.this, "New Announcement");
//                        builder.setContentTitle(anncmntTitle);
//                        builder.setContentText(anncmntDept);
//                        builder.setSmallIcon(R.drawable.logo2);
//                        builder.setAutoCancel(true);
//
//                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Admin_CreateAncmnt.this);
//                        managerCompat.notify(1,builder.build());
//
//                        Toast.makeText(Admin_CreateAncmnt.this, "Announcement Posted!", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getApplicationContext(), Admin_Announcement.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        //progress
//                        Toast.makeText(Admin_CreateAncmnt.this, "Announcement Failed to Post.", Toast.LENGTH_SHORT).show();
//                        Log.e("TAG", "ANNOUNCEMENT POSTING FAIL"  + e.getMessage() );
//                    }
//                });
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
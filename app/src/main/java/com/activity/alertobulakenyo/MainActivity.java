package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); //enable full screen

        setContentView(R.layout.activity_main);

        logo = (ImageView)findViewById (R.id.imgLogo);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
                Intent intent = new Intent(getApplicationContext(), GetStarted.class);
                startActivity(intent);
//
//                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
//                    DocumentReference df = FirebaseFirestore.getInstance().collection("UserData")
//                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid());
//                    df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                        @Override
//                        public void onSuccess(DocumentSnapshot documentSnapshot) {
//
//                            if (documentSnapshot.getString("User") != null) {
//                                Log.d("TAG", "onSuccess: USER LOGGED IN");
//                                Intent intent = new Intent(getApplicationContext(), Home.class);
//                                startActivity(intent);
//                                finish();
//                                overridePendingTransition(R.anim.slide_in_left,
//                                        R.anim.slide_out_right);
//                            }
//                            else {
//                                Intent intent = new Intent(getApplicationContext(), Login_Choose.class);
//                                startActivity(intent);
//                                finish();
//                                overridePendingTransition(R.anim.slide_in_left,
//                                        R.anim.slide_out_right);
//                                Log.d("TAG", "onSuccess: NO ONE LOGGED IN");
//                            }
//                        }
//                    });
//                }
//                else {
//                    Log.d("TAG", " NO ONE LOGGED IN");
//                    Intent intent = new Intent(getApplicationContext(), Login_Choose.class);
//                    startActivity(intent);
//                    finish();
//                    overridePendingTransition(R.anim.slide_in_left,
//                            R.anim.slide_out_right);
//                }
//            }
//        }, 3000);
    }

}
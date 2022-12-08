package com.activity.alertobulakenyo.ResidentUsers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.activity.alertobulakenyo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class SettingsNotif extends AppCompatActivity {

    View line1;
    LinearLayout layDisAlrt, layAncmt;
    Switch swAllNotif, swDisAlert, swAncmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_setting_notif);

        line1 = (View) findViewById (R.id.line1);

        layDisAlrt = (LinearLayout) findViewById (R.id.layDisAlert);
        layAncmt = (LinearLayout) findViewById (R.id.layAncmt);

        swAllNotif = (Switch) findViewById (R.id.swAllNotif);
        swDisAlert = (Switch) findViewById (R.id.swDisAlert);
        swAncmt = (Switch) findViewById (R.id.swAncmt);

        //TODO check this

        swAllNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    line1.setVisibility(View.VISIBLE);
                    layDisAlrt.setVisibility(View.VISIBLE);
                    layAncmt.setVisibility(View.VISIBLE);

                    FirebaseMessaging.getInstance().subscribeToTopic("announcements")
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    String msg = "Subscribed to Announcements";
                                    if (!task.isSuccessful()) {
                                        msg = "Subscription failed";
                                    }
//                    System.out.println(msg);
//                    Toast.makeText(GetStarted.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            });

                    FirebaseMessaging.getInstance().subscribeToTopic("warnings")
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    String msg = "Subscribed to Warnings";
                                    if (!task.isSuccessful()) {
                                        msg = "Subscription failed";
                                    }
//                    System.out.println(msg);
//                    Toast.makeText(GetStarted.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                else {
                    line1.setVisibility(View.INVISIBLE);
                    layDisAlrt.setVisibility(View.INVISIBLE);
                    layAncmt.setVisibility(View.INVISIBLE);

                    FirebaseMessaging.getInstance().unsubscribeFromTopic("announcements")
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    String msg = "Subscribed to Announcements";
                                    if (!task.isSuccessful()) {
                                        msg = "Subscription failed";
                                    }
//                    System.out.println(msg);
//                    Toast.makeText(GetStarted.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            });

                    FirebaseMessaging.getInstance().unsubscribeFromTopic("warnings")
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    String msg = "Subscribed to Warnings";
                                    if (!task.isSuccessful()) {
                                        msg = "Subscription failed";
                                    }
//                    System.out.println(msg);
//                    Toast.makeText(GetStarted.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            });

                }
            }
        });

        swDisAlert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    FirebaseMessaging.getInstance().subscribeToTopic("warnings")
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    String msg = "Subscribed to Warnings";
                                    if (!task.isSuccessful()) {
                                        msg = "Subscription failed";
                                    }
//                    System.out.println(msg);
//                    Toast.makeText(GetStarted.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("warnings")
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    String msg = "Subscribed to Warnings";
                                    if (!task.isSuccessful()) {
                                        msg = "Subscription failed";
                                    }
//                    System.out.println(msg);
//                    Toast.makeText(GetStarted.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

        swAncmt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    FirebaseMessaging.getInstance().subscribeToTopic("announcements")
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    String msg = "Subscribed to Announcements";
                                    if (!task.isSuccessful()) {
                                        msg = "Subscription failed";
                                    }
//                    System.out.println(msg);
//                    Toast.makeText(GetStarted.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            });

                } else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("announcements")
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    String msg = "Subscribed to Announcements";
                                    if (!task.isSuccessful()) {
                                        msg = "Subscription failed";
                                    }
//                    System.out.println(msg);
//                    Toast.makeText(GetStarted.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });


    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Settings.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
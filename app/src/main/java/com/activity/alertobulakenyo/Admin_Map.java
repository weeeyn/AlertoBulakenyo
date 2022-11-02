package com.activity.alertobulakenyo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Admin_Map extends AppCompatActivity {

    BottomNavigationView mapNav;
    EditText etSearchLoc;
    ImageButton btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_map);

        mapNav = (BottomNavigationView) findViewById (R.id.mapNav);

        etSearchLoc = (EditText) findViewById (R.id.etSearchLoc);
        btnSearch = (ImageButton) findViewById (R.id.btnSearch);

        // Set Home selected
        mapNav.setSelectedItemId(R.id.map);

        // Perform item selected listener
        mapNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.map:
                        return true;

                    case R.id.evac:
                        startActivity(new Intent (getApplicationContext(), Admin_Evac.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.saved:
                        startActivity(new Intent (getApplicationContext(), Admin_Saved.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(getApplicationContext(), Admin_Home.class));
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
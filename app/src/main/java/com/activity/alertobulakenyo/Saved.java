package com.activity.alertobulakenyo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Saved extends AppCompatActivity {

    BottomNavigationView mapNav;
    RecyclerView rvMapSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_saved);

        mapNav = (BottomNavigationView) findViewById (R.id.mapNav);

        rvMapSaved = (RecyclerView) findViewById (R.id.rvMapSaved);

        // Set Home selected
        mapNav.setSelectedItemId(R.id.saved);

        // Perform item selected listener
        mapNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.map:
                        startActivity(new Intent(getApplicationContext(), Map.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.evac:
                        startActivity(new Intent(getApplicationContext(), Evac.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.saved:
                        return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(getApplicationContext(), Map.class));
        overridePendingTransition(0,0);
    }
}
package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Admin_Map extends AppCompatActivity {

    BottomNavigationView mapNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_map);

        mapNav = (BottomNavigationView) findViewById (R.id.mapNav);

        mapNav.setOnNavigationItemSelectedListener(navListener);

        // as soon as the application opens the first
        // fragment should be shown to the user
        // in this case it is algorithm fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragMapContainer, new Admin_MapFrag()).commit();

    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        // By using switch we can easily get
        // the selected fragment
        // by using there id.
        Fragment selectedFragment = null;
        int itemId = item.getItemId();

        if (itemId == R.id.map) {
            selectedFragment = new Admin_MapFrag();
        }
        else if (itemId == R.id.evac) {
            selectedFragment = new Admin_EvacFrag();
        }
        else if (itemId == R.id.saved) {
            selectedFragment = new Admin_SavedFrag();
        }
        // It will help to replace the
        // one fragment to other.
        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragMapContainer, selectedFragment).commit();
        }
        return true;
    };

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(Admin_Map.this, Admin_Homenav.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
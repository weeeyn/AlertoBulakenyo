package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputLayout;

public class Admin_CreateWarning extends AppCompatActivity {

    TextInputLayout tilType, tilCity;
    AutoCompleteTextView actType, actCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_create_warning);

        tilType = (TextInputLayout) findViewById (R.id.tilType);
        actType = (AutoCompleteTextView) findViewById (R.id.actType);

        tilCity = (TextInputLayout) findViewById (R.id.tilCity);
        actCity = (AutoCompleteTextView) findViewById (R.id.actCity);

        String [] type = {"EARTHQUAKE", "FIRE", "FLOOD", "LANDSLIDE", "TYPHOON"};

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(Admin_CreateWarning.this, R.layout.dropdown_items, type);
        actType.setDropDownBackgroundResource(R.color.white);
        actType.setAdapter(typeAdapter);

        ((AutoCompleteTextView)tilType.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selType = typeAdapter.getItem(position);

                if (selType == "EARTHQUAKE")
                {


                }
                else if (selType == "FIRE")
                {


                }
                else if (selType == "FLOOD")
                {


                }
                else if (selType == "LANDSLIDE")
                {


                }
                else if (selType == "TYPHOON")
                {


                }
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
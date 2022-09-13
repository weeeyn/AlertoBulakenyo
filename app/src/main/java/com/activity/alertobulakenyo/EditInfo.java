package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;

public class EditInfo extends AppCompatActivity {

    ImageView imgAvatar;
    Button btnUpload, btnRemove, btnSave;
    TextInputLayout tilFname, tilLname, tilUsername, tilEmail, tilCon, tilHouse, tilCity, tilBrgy, tilProvince;
    EditText etFname, etLname, etUsername, etEmail, etCon, etHouse;
    AutoCompleteTextView actBrgy, actCity, actProvince;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_edit_info);

        imgAvatar = (ImageView) findViewById (R.id.imgAvatar);

        btnUpload = (Button) findViewById (R.id.btnUpload);
        btnRemove = (Button) findViewById (R.id.btnRemove);
        btnSave = (Button) findViewById (R.id.btnSave);

        tilFname = (TextInputLayout) findViewById (R.id.tilFname);
        tilLname = (TextInputLayout) findViewById (R.id.tilLname);
        tilUsername = (TextInputLayout) findViewById (R.id.tilUsername);
        tilEmail = (TextInputLayout) findViewById (R.id.tilEmail);
        tilCon = (TextInputLayout) findViewById (R.id.tilCon);
        tilHouse = (TextInputLayout) findViewById (R.id.tilHouse);
        tilCity = (TextInputLayout) findViewById (R.id.tilCity);
        tilBrgy = (TextInputLayout) findViewById (R.id.tilBrgy);
        tilProvince = (TextInputLayout) findViewById (R.id.tilProvince);

        etFname = (EditText) findViewById (R.id.etFname);
        etLname = (EditText) findViewById (R.id.etLname);
        etUsername = (EditText) findViewById (R.id.etUsername);
        etEmail= (EditText) findViewById (R.id.etEmail);
        etCon = (EditText) findViewById (R.id.etCon);
        etHouse = (EditText) findViewById (R.id.etHouse);

        actBrgy = (AutoCompleteTextView) findViewById (R.id.actBrgy);
        actCity = (AutoCompleteTextView) findViewById (R.id.actCity);
        actProvince = (AutoCompleteTextView) findViewById (R.id.actProvince);

        String [] brgySJDM = {"Assumption", "Bagong Buhay I", "Bagong Buhay II", "Bagong Buhay III",
                "Citrus", "Ciudad Real", "Dulong Bayan", "Fatima I", "Fatima II", "Fatima III",
                "Fatima IV", "Fatima V", "Francisco Homes - Guijo", "Francisco Homes - Mulawin",
                "Francisco Homes - Narra", "Francisco Homes - Yakal", "Gaya-Gaya", "Graceville",
                "Gumaoc - Central", "Gumaoc - East", "Gumaoc - West", "Kaybanban", "Kaypian",
                "Lawang Pari", "Maharlika", "Minuyan I", "Minuyan II", "Minuyan III", "Minuyan IV",
                "Minuyan Proper", "Minuyan V", "Muzon", "Paradise III", "Poblacion", "Poblacion I",
                "San Isidro", "San Manuel", "San Martin I", "San Martin II", "San Martin III",
                "San Martin IV", "San Pedro", "San Rafael I", "San Rafael II", "San Rafael III",
                "San Rafael IV", "San Rafael V", "San Roque", "Santa Cruz I", "Santa Cruz II",
                "Santa Cruz III", "Santa Cruz IV", "Santa Cruz V", "Santo Cristo", "Santo Niño I",
                "Santo Niño II", "Sapang Palay Proper", "St. Martin de Porres", "Tungkong Mangga"};

        String [] city = {"San Jose del Monte"};

        String [] province = {"Bulacan"};

        ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(EditInfo.this, R.layout.dropdown_items, brgySJDM);
        actBrgy.setDropDownBackgroundResource(R.color.white);
        actBrgy.setAdapter(brgyAdapter);

        ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedBrgy = brgyAdapter.getItem(position);
            }
        });

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(EditInfo.this, R.layout.dropdown_items, city);
        actCity.setDropDownBackgroundResource(R.color.white);
        actCity.setAdapter(cityAdapter);

        ArrayAdapter<String> provAdapter = new ArrayAdapter<>(EditInfo.this, R.layout.dropdown_items, province);
        actProvince.setDropDownBackgroundResource(R.color.white);
        actProvince.setAdapter(provAdapter);

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
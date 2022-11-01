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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class ViewToAdd extends AppCompatActivity {

    ImageView imgProfile;
    TextView tvFullName, tvAccUser, tvAccEmail, tvAccConNum, tvAccAdd;
    TextInputLayout tilRelationship;
    AutoCompleteTextView actRelationship;
    Button btnSendReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_view_to_add);

        imgProfile = (ImageView) findViewById (R.id.imgProfile);

        tvFullName = (TextView) findViewById (R.id.tvFullName);
        tvAccUser = (TextView) findViewById (R.id.tvAccUser);
        tvAccEmail = (TextView) findViewById (R.id.tvAccEmail);
        tvAccConNum = (TextView) findViewById (R.id.tvAccConNum);
        tvAccAdd = (TextView) findViewById (R.id.tvAccAdd);

        tilRelationship = (TextInputLayout) findViewById (R.id.tilRelationship);
        actRelationship = (AutoCompleteTextView) findViewById (R.id.actRelationship);

        btnSendReq = (Button) findViewById (R.id.btnSendReq);

        String [] relationship = {"Wife", "Husband", "Mother", "Father", "Daughter", "Son", "Brother", "Sister",
                "Aunt", "Uncle", "Cousin", "Grandmother", "Grandfather", "Granddaugther",
                "Grandson", "Bestfriend", "Friend", "Classmate", "Co-worker"};

        ArrayAdapter<String> relationshipAdapter = new ArrayAdapter<>(ViewToAdd.this, R.layout.dropdown_items, relationship);
        actRelationship.setDropDownBackgroundResource(R.color.white);
        actRelationship.setAdapter(relationshipAdapter);

        ((AutoCompleteTextView)tilRelationship.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedRelationship = relationshipAdapter.getItem(position);
            }
        });

        btnSendReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSendReq.setText("REQUEST SENT");
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
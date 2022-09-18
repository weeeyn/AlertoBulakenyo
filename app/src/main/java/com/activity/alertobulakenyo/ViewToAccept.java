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

import com.google.android.material.textfield.TextInputLayout;

public class ViewToAccept extends AppCompatActivity {

    TextInputLayout tilRelationship;
    AutoCompleteTextView actRelationship;
    Button btnAcceptReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_view_to_accept);

        tilRelationship = (TextInputLayout) findViewById (R.id.tilRelationship);
        actRelationship = (AutoCompleteTextView) findViewById (R.id.actRelationship);

        btnAcceptReq = (Button) findViewById (R.id.btnAcceptReq);

        String [] relationship = {"Wife", "Husband", "Mother", "Father", "Daughter", "Son", "Brother", "Sister",
                "Aunt", "Uncle", "Cousin", "Grandmother", "Grandfather", "Granddaugther",
                "Grandson", "Bestfriend", "Friend", "Classmate", "Co-worker"};

        ArrayAdapter<String> relationshipAdapter = new ArrayAdapter<>(ViewToAccept.this, R.layout.dropdown_items, relationship);
        actRelationship.setDropDownBackgroundResource(R.color.white);
        actRelationship.setAdapter(relationshipAdapter);

        ((AutoCompleteTextView)tilRelationship.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedRelationship = relationshipAdapter.getItem(position);
            }
        });

        btnAcceptReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_view_trusted);
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
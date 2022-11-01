package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class Request extends AppCompatActivity {

    CardView cardRequest;
    ImageView imgContact;
    TextView tvFullName, tvBrgyCity;
    Button btnAccept, btnRemoveReq;
    RecyclerView rvContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_request);

        cardRequest = (CardView) findViewById (R.id.cardRequest);

        imgContact = (ImageView) findViewById (R.id.imgContact);
        tvFullName = (TextView) findViewById (R.id.tvFullName);
        tvBrgyCity = (TextView) findViewById (R.id.tvBrgyCity);

        btnAccept = (Button) findViewById (R.id.btnAccept);
        btnRemoveReq = (Button) findViewById (R.id.btnRemoveReq);

        rvContacts = (RecyclerView) findViewById (R.id.rvContacts);

        cardRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Request.this, ViewToAccept.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardRequest.setVisibility(View.GONE);
                Toast.makeText(Request.this, "Request Accepted!", Toast.LENGTH_LONG).show();
            }
        });

        btnRemoveReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardRequest.setVisibility(View.GONE);
                Toast.makeText(Request.this, "Request Removed!", Toast.LENGTH_LONG).show();
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
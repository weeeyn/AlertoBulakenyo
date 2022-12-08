package com.activity.alertobulakenyo.Admins;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activity.alertobulakenyo.ObjectClasses.EvacuationHolder;
import com.activity.alertobulakenyo.R;
import com.activity.alertobulakenyo.ResidentUsers.Evacuation;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class Admin_EditEvac extends AppCompatActivity {

    TextInputLayout tilBrgy;
    EditText etEvacName, etEvacLoc, etLong, etLat;
    AutoCompleteTextView actBrgy;
    Button btnSave, btnDelete;

    private String evacuationName, evacuationAddress, evacuationLongitude, evacuationLatitude, evacuationCity, evacuationBrgy;
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private FirebaseUser user = fAuth.getCurrentUser();
    private String userId = user.getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_edit_evac);

        EvacuationHolder evacuationHolder = (EvacuationHolder) getIntent().getSerializableExtra("evac");

        tilBrgy = (TextInputLayout) findViewById (R.id.tilBrgy);

        etEvacName = (EditText) findViewById (R.id.etEvacName);
        etEvacLoc = (EditText) findViewById (R.id.etEvacLoc);
        etLong = (EditText) findViewById (R.id.etLong);
        etLat = (EditText) findViewById (R.id.etLat);

        actBrgy = (AutoCompleteTextView) findViewById (R.id.actBrgy);

        etEvacName.setText(evacuationHolder.getEvacuationName());
        etEvacLoc.setText(evacuationHolder.getEvacuationAddress());
        etLong.setText(evacuationHolder.getEvacuationLongitude());
        etLat.setText(evacuationHolder.getEvacuationLatitude());
        actBrgy.setText(evacuationHolder.getEvacuationBrgy());

        btnSave = (Button) findViewById (R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        String [] brgyBoc = {"Antipona", "Bagumbayan", "Bambang", "Batia", "Biñang 1st", "Biñang 2nd",
                "Bolacan", "Bundukan", "Bunlo", "Caingin", "Duhat", "Igulot", "Lolomboy", "Poblacion",
                "Sulucan", "Taal", "Tambobong", "Turo", "Wakas"};

        String [] brgyMar = {"Abangan Norte", "Abangan Sur", "Ibayo", "Lambakin", "Lias", "Loma de Gato",
                "Nagbalon", "Patubig", "Poblacion I", "Poblacion II", "Prenza I", "Prenza II",
                "Santa Rosa I", "Santa Rosa II", "Saog", "Tabing Ilog"};

        String [] brgyMey = {"Bagbaguin", "Bahay Pare", "Bancal", "Banga", "Bayugo", "Caingin",
                "Calvario", "Camalig", "Hulo", "Iba", "Langka", "Lawa", "Libtong", "Liputan", "Longos",
                "Malhacan", "Pajo", "Pandayan", "Pantoc", "Perez", "Poblacion", "Saluysoy",
                "Saint Francis (Gasak)", "Tugatog", "Ubihan", "Zamora"};

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

        String [] brgySanMa = {"Bagbaguin", "Balasing", "Buenavista", "Bulac", "Camangyanan", "Catmon",
                "Cay Pombo", "Caysio", "Guyong", "Lalakhan", "Mag-asawang Sapa", "Mahabang Parang",
                "Manggahan", "Parada", "Poblacion", "Pulong Buhangin", "San Gabriel", "San Jose Patag",
                "San Vicente", "Santa Clara", "Santa Cruz", "Silangan", "Tabing Bakod", "Tumana"};

        DocumentReference df = fStore.collection("UserData").document(userId);
        df.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.getString("adminCity").equals("Bocaue")) {

                            ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(Admin_EditEvac.this, R.layout.dropdown_items, brgyBoc);
                            actBrgy.setDropDownBackgroundResource(R.color.white);
                            actBrgy.setAdapter(brgyAdapter);

                            ((AutoCompleteTextView) Objects.requireNonNull(tilBrgy.getEditText())).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String selectedBrgy = brgyAdapter.getItem(position);
                                }
                            });

                            btnSave.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    evacuationName = etEvacName.getText().toString();
                                    evacuationAddress = etEvacLoc.getText().toString();
                                    evacuationLongitude = etLong.getText().toString();
                                    evacuationLatitude = etLat.getText().toString();
                                    evacuationCity = "Bocaue";
                                    evacuationBrgy = actBrgy.getText().toString();

                                    editEvacuation(evacuationHolder, evacuationName, evacuationAddress, evacuationLongitude, evacuationLatitude, evacuationCity, evacuationBrgy);
                                }
                            });

                        }
                        else if(documentSnapshot.getString("adminCity").equals("Marilao")) {

                            ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(Admin_EditEvac.this, R.layout.dropdown_items, brgyMar);
                            actBrgy.setDropDownBackgroundResource(R.color.white);
                            actBrgy.setAdapter(brgyAdapter);

                            ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String selectedBrgy = brgyAdapter.getItem(position);
                                }
                            });

                            btnSave.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    evacuationName = etEvacName.getText().toString();
                                    evacuationAddress = etEvacLoc.getText().toString();
                                    evacuationLongitude = etLong.getText().toString();
                                    evacuationLatitude = etLat.getText().toString();
                                    evacuationCity = "Marilao";
                                    evacuationBrgy = actBrgy.getText().toString();

                                    editEvacuation(evacuationHolder, evacuationName, evacuationAddress, evacuationLongitude, evacuationLatitude, evacuationCity, evacuationBrgy);
                                }
                            });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Meycauayan")) {


                            ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(Admin_EditEvac.this, R.layout.dropdown_items, brgyMey);
                            actBrgy.setDropDownBackgroundResource(R.color.white);
                            actBrgy.setAdapter(brgyAdapter);

                            ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String selectedBrgy = brgyAdapter.getItem(position);
                                }
                            });

                            btnSave.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    evacuationName = etEvacName.getText().toString();
                                    evacuationAddress = etEvacLoc.getText().toString();
                                    evacuationLongitude = etLong.getText().toString();
                                    evacuationLatitude = etLat.getText().toString();
                                    evacuationCity = "Meycauayan";
                                    evacuationBrgy = actBrgy.getText().toString();

                                    editEvacuation(evacuationHolder, evacuationName, evacuationAddress, evacuationLongitude, evacuationLatitude, evacuationCity, evacuationBrgy);
                                }
                            });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("San Jose del Monte")) {

                            ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(Admin_EditEvac.this, R.layout.dropdown_items, brgySJDM);
                            actBrgy.setDropDownBackgroundResource(R.color.white);
                            actBrgy.setAdapter(brgyAdapter);

                            ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String selectedBrgy = brgyAdapter.getItem(position);
                                }
                            });

                            btnSave.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    evacuationName = etEvacName.getText().toString();
                                    evacuationAddress = etEvacLoc.getText().toString();
                                    evacuationLongitude = etLong.getText().toString();
                                    evacuationLatitude = etLat.getText().toString();
                                    evacuationCity = "San Jose del Monte";
                                    evacuationBrgy = actBrgy.getText().toString();

                                    editEvacuation(evacuationHolder, evacuationName, evacuationAddress, evacuationLongitude, evacuationLatitude, evacuationCity, evacuationBrgy);
                                }
                            });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Santa Maria")) {

                            ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(Admin_EditEvac.this, R.layout.dropdown_items, brgySanMa);
                            actBrgy.setDropDownBackgroundResource(R.color.white);
                            actBrgy.setAdapter(brgyAdapter);

                            ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String selectedBrgy = brgyAdapter.getItem(position);
                                }
                            });

                            btnSave.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    evacuationName = etEvacName.getText().toString();
                                    evacuationAddress = etEvacLoc.getText().toString();
                                    evacuationLongitude = etLong.getText().toString();
                                    evacuationLatitude = etLat.getText().toString();
                                    evacuationCity = "Santa Maria";
                                    evacuationBrgy = actBrgy.getText().toString();

                                    editEvacuation(evacuationHolder, evacuationName, evacuationAddress, evacuationLongitude, evacuationLatitude, evacuationCity, evacuationBrgy);
                                }
                            });
                        }
                    }
                });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEvac(evacuationHolder);
            }
        });


    }

    private void deleteEvac(EvacuationHolder evacuationHolder) {
        fStore.collection("Evacuation")
                .document(evacuationHolder.getId())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Admin_EditEvac.this, "Evacuation Deleted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Admin_EditEvac.this, Admin_Evacuation.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(R.anim.slide_in_left,
                                    R.anim.slide_out_right);
                        } else {
                            Toast.makeText(Admin_EditEvac.this, "Evacuation Not Deleted!", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "onFailure: FAILED TO DELETE HOTLINE");
                        }
                    }
                });

    }

    private void editEvacuation(@NonNull EvacuationHolder evacuationHolder, String evacuationName, String evacuationAddress, String evacuationLongitude, String evacuationLatitude, String evacuationCity, String evacuationBrgy) {

        EvacuationHolder editEvac = new EvacuationHolder(evacuationName, evacuationAddress, evacuationLongitude, evacuationLatitude, evacuationCity, evacuationBrgy);

        fStore.collection("Evacuation")
                .document(evacuationHolder.getId())
                .set(editEvac)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Admin_EditEvac.this, "Evacuation has been EDITED!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Admin_EditEvac.this, Admin_Evacuation.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("TAG", "onFailure: " + e.getMessage());
                    }
                });
    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Admin_Evacuation.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }

}
package com.activity.alertobulakenyo.Admins;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.activity.alertobulakenyo.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Admin_AddEvac extends AppCompatActivity {

    private TextInputLayout tilBrgy;
    private EditText etEvacName, etEvacLoc, etLong, etLat;
    private AutoCompleteTextView actBrgy;
    private Button btnSaveEvac;
    private ProgressBar progressBar;

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

        setContentView(R.layout.activity_admin_add_evac);

        progressBar = (ProgressBar) findViewById (R.id.progressBar);

        tilBrgy = (TextInputLayout) findViewById (R.id.tilBrgy);

        etEvacName = (EditText) findViewById (R.id.etEvacName);
        etEvacLoc = (EditText) findViewById (R.id.etEvacLoc);

        etLong=findViewById(R.id.etLong);
        etLat=findViewById(R.id.etLat);

        actBrgy = (AutoCompleteTextView) findViewById (R.id.actBrgy);

        btnSaveEvac = (Button) findViewById (R.id.btnSaveEvac);

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
                            ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(Admin_AddEvac.this, R.layout.dropdown_items, brgyBoc);
                            actBrgy.setDropDownBackgroundResource(R.color.white);
                            actBrgy.setAdapter(brgyAdapter);
                            ((AutoCompleteTextView) Objects.requireNonNull(tilBrgy.getEditText())).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String selectedBrgy = brgyAdapter.getItem(position);
                                }
                            });
                            btnSaveEvac.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    String evacuationBrgy = actBrgy.getText().toString();
                                    String evacuationName = etEvacName.getText().toString();
                                    String evacuationAddress = etEvacLoc.getText().toString();
                                    String evacuationLongitude = etLong.getText().toString();
                                    String evacuationLatitude = etLat.getText().toString();
                                    if (TextUtils.isEmpty(evacuationBrgy)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please select Barangay Address.", Toast.LENGTH_SHORT).show();
                                        actBrgy.setError("Barangay can't be empty!");
                                        actBrgy.requestFocus();
                                    } else if (TextUtils.isEmpty(evacuationName)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please input Evacuation Name.", Toast.LENGTH_SHORT).show();
                                        etEvacName.setError("Evacuation Name can't be empty!");
                                        etEvacName.requestFocus();
                                    } else if (TextUtils.isEmpty(evacuationAddress)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please input Evacuation Address.", Toast.LENGTH_SHORT).show();
                                        etEvacLoc.setError("Evacuation Address can't be empty!");
                                        etEvacLoc.requestFocus();
                                    } else if (TextUtils.isEmpty(evacuationLongitude)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please input Evacuation Longitude.", Toast.LENGTH_SHORT).show();
                                        etLong.setError("Longitude can't be empty!");
                                        etLong.requestFocus();
                                    } else if (TextUtils.isEmpty(evacuationLatitude)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please input Evacuation Latitude.", Toast.LENGTH_SHORT).show();
                                        etLat.setError("Longitude can't be empty!");
                                        etLat.requestFocus();
                                    } else {
                                        DocumentReference df2 = fStore.collection("Evacuation").document();
                                        Map<String, Object> evac = new HashMap<>();
                                        evac.put("evacuationCity", "Bocaue");
                                        evac.put("evacuationBrgy", evacuationBrgy);
                                        evac.put("evacuationName", evacuationName);
                                        evac.put("evacuationAddress", evacuationAddress);
                                        evac.put("evacuationLongitude", evacuationLongitude);
                                        evac.put("evacuationLatitude", evacuationLatitude);
                                        df2.set(evac)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        progressBar.setVisibility(View.GONE);
                                                        Toast.makeText(Admin_AddEvac.this, "Evacuation Saved!", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(Admin_AddEvac.this, Admin_Evacuation.class);
                                                        startActivity(intent);
                                                        finish();
                                                        overridePendingTransition(R.anim.slide_in_left,
                                                                R.anim.slide_out_right);
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        progressBar.setVisibility(View.GONE);
                                                        Toast.makeText(Admin_AddEvac.this, "Evacuation not saved!", Toast.LENGTH_SHORT).show();
                                                        Log.e("TAG", "onFailure: EVAC FAILED TO POST" + e.getMessage());
                                                    }
                                                });
                                    }
                                }
                            });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Marilao")) {
                            ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(Admin_AddEvac.this, R.layout.dropdown_items, brgyMar);
                            actBrgy.setDropDownBackgroundResource(R.color.white);
                            actBrgy.setAdapter(brgyAdapter);
                            ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String selectedBrgy = brgyAdapter.getItem(position);
                                }
                            });
                            btnSaveEvac.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    String evacuationBrgy = actBrgy.getText().toString();
                                    String evacuationName = etEvacName.getText().toString();
                                    String evacuationAddress = etEvacLoc.getText().toString();
                                    String evacuationLongitude = etLong.getText().toString();
                                    String evacuationLatitude = etLat.getText().toString();
                                    if (TextUtils.isEmpty(evacuationBrgy)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please select Barangay Address.", Toast.LENGTH_SHORT).show();
                                        actBrgy.setError("Barangay can't be empty!");
                                        actBrgy.requestFocus();
                                    } else if (TextUtils.isEmpty(evacuationName)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please input Evacuation Name.", Toast.LENGTH_SHORT).show();
                                        etEvacName.setError("Evacuation Name can't be empty!");
                                        etEvacName.requestFocus();
                                    } else if (TextUtils.isEmpty(evacuationAddress)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please input Evacuation Address.", Toast.LENGTH_SHORT).show();
                                        etEvacLoc.setError("Evacuation Address can't be empty!");
                                        etEvacLoc.requestFocus();
                                    } else if (TextUtils.isEmpty(evacuationLongitude)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please input Evacuation Longitude.", Toast.LENGTH_SHORT).show();
                                        etLong.setError("Longitude can't be empty!");
                                        etLong.requestFocus();
                                    } else if (TextUtils.isEmpty(evacuationLatitude)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please input Evacuation Latitude.", Toast.LENGTH_SHORT).show();
                                        etLat.setError("Longitude can't be empty!");
                                        etLat.requestFocus();
                                    } else {
                                        DocumentReference df2 = fStore.collection("Evacuation").document();
                                        Map<String, Object> evac = new HashMap<>();
                                        evac.put("evacuationCity", "Marilao");
                                        evac.put("evacuationBrgy", evacuationBrgy);
                                        evac.put("evacuationName", evacuationName);
                                        evac.put("evacuationAddress", evacuationAddress);
                                        evac.put("evacuationLongitude", evacuationLongitude);
                                        evac.put("evacuationLatitude", evacuationLatitude);
                                        df2.set(evac)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        progressBar.setVisibility(View.GONE);
                                                        Toast.makeText(Admin_AddEvac.this, "Evacuation Saved!", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(Admin_AddEvac.this, Admin_Evacuation.class);
                                                        startActivity(intent);
                                                        finish();
                                                        overridePendingTransition(R.anim.slide_in_left,
                                                                R.anim.slide_out_right);
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        progressBar.setVisibility(View.GONE);
                                                        Toast.makeText(Admin_AddEvac.this, "Evacuation not saved!", Toast.LENGTH_SHORT).show();
                                                        Log.e("TAG", "onFailure: EVAC FAILED TO POST" + e.getMessage());
                                                    }
                                                });
                                    }
                                }
                            });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Meycauayan")) {
                            ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(Admin_AddEvac.this, R.layout.dropdown_items, brgyMey);
                            actBrgy.setDropDownBackgroundResource(R.color.white);
                            actBrgy.setAdapter(brgyAdapter);
                            ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String selectedBrgy = brgyAdapter.getItem(position);
                                }
                            });
                            btnSaveEvac.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    String evacuationBrgy = actBrgy.getText().toString();
                                    String evacuationName = etEvacName.getText().toString();
                                    String evacuationAddress = etEvacLoc.getText().toString();
                                    String evacuationLongitude = etLong.getText().toString();
                                    String evacuationLatitude = etLat.getText().toString();
                                    if (TextUtils.isEmpty(evacuationBrgy)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please select Barangay Address.", Toast.LENGTH_SHORT).show();
                                        actBrgy.setError("Barangay can't be empty!");
                                        actBrgy.requestFocus();
                                    } else if (TextUtils.isEmpty(evacuationName)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please input Evacuation Name.", Toast.LENGTH_SHORT).show();
                                        etEvacName.setError("Evacuation Name can't be empty!");
                                        etEvacName.requestFocus();
                                    } else if (TextUtils.isEmpty(evacuationAddress)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please input Evacuation Address.", Toast.LENGTH_SHORT).show();
                                        etEvacLoc.setError("Evacuation Address can't be empty!");
                                        etEvacLoc.requestFocus();
                                    } else if (TextUtils.isEmpty(evacuationLongitude)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please input Evacuation Longitude.", Toast.LENGTH_SHORT).show();
                                        etLong.setError("Longitude can't be empty!");
                                        etLong.requestFocus();
                                    } else if (TextUtils.isEmpty(evacuationLatitude)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please input Evacuation Latitude.", Toast.LENGTH_SHORT).show();
                                        etLat.setError("Longitude can't be empty!");
                                        etLat.requestFocus();
                                    } else {
                                        DocumentReference df2 = fStore.collection("Evacuation").document();
                                        Map<String, Object> evac = new HashMap<>();
                                        evac.put("evacuationCity", "Meycauayan");
                                        evac.put("evacuationBrgy", evacuationBrgy);
                                        evac.put("evacuationName", evacuationName);
                                        evac.put("evacuationAddress", evacuationAddress);
                                        evac.put("evacuationLongitude", evacuationLongitude);
                                        evac.put("evacuationLatitude", evacuationLatitude);
                                        df2.set(evac)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        progressBar.setVisibility(View.GONE);
                                                        Toast.makeText(Admin_AddEvac.this, "Evacuation Saved!", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(Admin_AddEvac.this, Admin_Evacuation.class);
                                                        startActivity(intent);
                                                        finish();
                                                        overridePendingTransition(R.anim.slide_in_left,
                                                                R.anim.slide_out_right);
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        progressBar.setVisibility(View.GONE);
                                                        Toast.makeText(Admin_AddEvac.this, "Evacuation not saved!", Toast.LENGTH_SHORT).show();
                                                        Log.e("TAG", "onFailure: EVAC FAILED TO POST" + e.getMessage());
                                                    }
                                                });
                                    }
                                }
                            });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("San Jose del Monte")) {
                            ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(Admin_AddEvac.this, R.layout.dropdown_items, brgySJDM);
                            actBrgy.setDropDownBackgroundResource(R.color.white);
                            actBrgy.setAdapter(brgyAdapter);
                            ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String selectedBrgy = brgyAdapter.getItem(position);
                                }
                            });
                            btnSaveEvac.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    String evacuationBrgy = actBrgy.getText().toString();
                                    String evacuationName = etEvacName.getText().toString();
                                    String evacuationAddress = etEvacLoc.getText().toString();
                                    String evacuationLongitude = etLong.getText().toString();
                                    String evacuationLatitude = etLat.getText().toString();
                                    if (TextUtils.isEmpty(evacuationBrgy)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please select Barangay Address.", Toast.LENGTH_SHORT).show();
                                        actBrgy.setError("Barangay can't be empty!");
                                        actBrgy.requestFocus();
                                    } else if (TextUtils.isEmpty(evacuationName)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please input Evacuation Name.", Toast.LENGTH_SHORT).show();
                                        etEvacName.setError("Evacuation Name can't be empty!");
                                        etEvacName.requestFocus();
                                    } else if (TextUtils.isEmpty(evacuationAddress)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please input Evacuation Address.", Toast.LENGTH_SHORT).show();
                                        etEvacLoc.setError("Evacuation Address can't be empty!");
                                        etEvacLoc.requestFocus();
                                    } else if (TextUtils.isEmpty(evacuationLongitude)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please input Evacuation Longitude.", Toast.LENGTH_SHORT).show();
                                        etLong.setError("Longitude can't be empty!");
                                        etLong.requestFocus();
                                    } else if (TextUtils.isEmpty(evacuationLatitude)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please input Evacuation Latitude.", Toast.LENGTH_SHORT).show();
                                        etLat.setError("Longitude can't be empty!");
                                        etLat.requestFocus();
                                    } else {
                                        DocumentReference df2 = fStore.collection("Evacuation").document();
                                        Map<String, Object> evac = new HashMap<>();
                                        evac.put("evacuationCity", "San Jose del Monte");
                                        evac.put("evacuationBrgy", evacuationBrgy);
                                        evac.put("evacuationName", evacuationName);
                                        evac.put("evacuationAddress", evacuationAddress);
                                        evac.put("evacuationLongitude", evacuationLongitude);
                                        evac.put("evacuationLatitude", evacuationLatitude);
                                        df2.set(evac)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        progressBar.setVisibility(View.GONE);
                                                        Toast.makeText(Admin_AddEvac.this, "Evacuation Saved!", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(Admin_AddEvac.this, Admin_Evacuation.class);
                                                        startActivity(intent);
                                                        finish();
                                                        overridePendingTransition(R.anim.slide_in_left,
                                                                R.anim.slide_out_right);
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        progressBar.setVisibility(View.GONE);
                                                        Toast.makeText(Admin_AddEvac.this, "Evacuation not saved!", Toast.LENGTH_SHORT).show();
                                                        Log.e("TAG", "onFailure: EVAC FAILED TO POST" + e.getMessage());
                                                    }
                                                });
                                    }
                                }
                            });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Santa Maria")) {
                            ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(Admin_AddEvac.this, R.layout.dropdown_items, brgySanMa);
                            actBrgy.setDropDownBackgroundResource(R.color.white);
                            actBrgy.setAdapter(brgyAdapter);
                            ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String selectedBrgy = brgyAdapter.getItem(position);
                                }
                            });
                            btnSaveEvac.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    String evacuationBrgy = actBrgy.getText().toString();
                                    String evacuationName = etEvacName.getText().toString();
                                    String evacuationAddress = etEvacLoc.getText().toString();
                                    String evacuationLongitude = etLong.getText().toString();
                                    String evacuationLatitude = etLat.getText().toString();
                                    if (TextUtils.isEmpty(evacuationBrgy)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please select Barangay Address.", Toast.LENGTH_SHORT).show();
                                        actBrgy.setError("Barangay can't be empty!");
                                        actBrgy.requestFocus();
                                    } else if (TextUtils.isEmpty(evacuationName)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please input Evacuation Name.", Toast.LENGTH_SHORT).show();
                                        etEvacName.setError("Evacuation Name can't be empty!");
                                        etEvacName.requestFocus();
                                    } else if (TextUtils.isEmpty(evacuationAddress)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please input Evacuation Address.", Toast.LENGTH_SHORT).show();
                                        etEvacLoc.setError("Evacuation Address can't be empty!");
                                        etEvacLoc.requestFocus();
                                    } else if (TextUtils.isEmpty(evacuationLongitude)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please input Evacuation Longitude.", Toast.LENGTH_SHORT).show();
                                        etLong.setError("Longitude can't be empty!");
                                        etLong.requestFocus();
                                    } else if (TextUtils.isEmpty(evacuationLatitude)) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Admin_AddEvac.this, "Please input Evacuation Latitude.", Toast.LENGTH_SHORT).show();
                                        etLat.setError("Longitude can't be empty!");
                                        etLat.requestFocus();
                                    } else {
                                        DocumentReference df2 = fStore.collection("Evacuation").document();
                                        Map<String, Object> evac = new HashMap<>();
                                        evac.put("evacuationCity", "Santa Maria");
                                        evac.put("evacuationBrgy", evacuationBrgy);
                                        evac.put("evacuationName", evacuationName);
                                        evac.put("evacuationAddress", evacuationAddress);
                                        evac.put("evacuationLongitude", evacuationLongitude);
                                        evac.put("evacuationLatitude", evacuationLatitude);
                                        df2.set(evac)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        progressBar.setVisibility(View.GONE);
                                                        Toast.makeText(Admin_AddEvac.this, "Evacuation Saved!", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(Admin_AddEvac.this, Admin_Evacuation.class);
                                                        startActivity(intent);
                                                        finish();
                                                        overridePendingTransition(R.anim.slide_in_left,
                                                                R.anim.slide_out_right);
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        progressBar.setVisibility(View.GONE);
                                                        Toast.makeText(Admin_AddEvac.this, "Evacuation not saved!", Toast.LENGTH_SHORT).show();
                                                        Log.e("TAG", "onFailure: EVAC FAILED TO POST" + e.getMessage());
                                                    }
                                                });
                                    }
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
        Intent intent = new Intent(getApplicationContext(), Admin_Evacuation.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }

}
package com.activity.alertobulakenyo.Admins;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
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

import com.activity.alertobulakenyo.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Admin_CreateWarning extends AppCompatActivity {

    TextInputLayout tilType, tilMag, tilFire, tilRain, tilFlood, tilTy, tilSig, tilIns;
    AutoCompleteTextView actType, actFire, actRain, actFlood, actSig;
    EditText etMag, etTy, etIns;
    Dialog dialog;
    Button btnPost;

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

        setContentView(R.layout.activity_admin_create_warning);

        tilType = (TextInputLayout) findViewById (R.id.tilType);
        tilMag = (TextInputLayout) findViewById (R.id.tilMag);
        tilFire = (TextInputLayout) findViewById (R.id.tilFire);
        tilRain = (TextInputLayout) findViewById (R.id.tilRain);
        tilFlood = (TextInputLayout) findViewById (R.id.tilFlood);
        tilTy = (TextInputLayout) findViewById (R.id.tilTy);
        tilSig = (TextInputLayout) findViewById (R.id.tilSig);
        tilIns = (TextInputLayout) findViewById (R.id.tilIns);

        actType = (AutoCompleteTextView) findViewById (R.id.actType);
        actFire = (AutoCompleteTextView) findViewById (R.id.actFire);
        actRain = (AutoCompleteTextView) findViewById (R.id.actRain);
        actFlood = (AutoCompleteTextView) findViewById (R.id.actFlood);
        actSig = (AutoCompleteTextView) findViewById (R.id.actSig);

        etMag = (EditText) findViewById (R.id.etMag);
        etTy = (EditText) findViewById (R.id.etTy);
        etIns = (EditText) findViewById (R.id.etIns);

        btnPost = (Button) findViewById (R.id.btnPost);

        AlertDialog.Builder build = new AlertDialog.Builder(Admin_CreateWarning.this);
        dialog = build.create();

        String [] type = {"EARTHQUAKE", "FIRE", "FLOOD", "LANDSLIDE", "TYPHOON"};

        String [] fire = {"First Alarm", "Second Alarm", "Third Alarm", "Fourth Alarm", "Fifth Alarm"};

        String [] rain = {"Yellow Rainfall Warning", "Orange Rainfaill Warning", "Red Raindfall Warning"};

        String [] flood = {"Yellow Warning", "Orange Warning", "Red Warning"};

        String [] signal = {"Signal No. 1", "Signal No. 2", "Signal No. 3", "Signal No. 4", "Signal No. 5"};

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(Admin_CreateWarning.this, R.layout.dropdown_items, type);
        actType.setDropDownBackgroundResource(R.color.white);
        actType.setAdapter(typeAdapter);

        ((AutoCompleteTextView)tilType.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selType = typeAdapter.getItem(position);

                if (selType == "EARTHQUAKE")
                {
                    tilMag.setVisibility(View.VISIBLE);
                    tilFire.setVisibility(View.GONE);
                    tilRain.setVisibility(View.GONE);
                    tilFlood.setVisibility(View.GONE);
                    tilTy.setVisibility(View.GONE);
                    tilSig.setVisibility(View.GONE);

                        tilIns.setVisibility(View.VISIBLE);
                        etIns.setText("Isang Magnitude *5.7* na lindol ang naganap sa *San Jose del Monte, Bulacan* kaning *1:15 PM.* Aftershocks ay inaasahan. Pinaaalalahan ang lahat na mag-ingat.");
                }
                else if (selType == "FIRE")
                {
                    tilMag.setVisibility(View.GONE);
                    tilFire.setVisibility(View.VISIBLE);
                    tilRain.setVisibility(View.GONE);
                    tilFlood.setVisibility(View.GONE);
                    tilTy.setVisibility(View.GONE);
                    tilSig.setVisibility(View.GONE);
                    tilIns.setVisibility(View.GONE);

                    ArrayAdapter<String> fireAdapter = new ArrayAdapter<>(Admin_CreateWarning.this, R.layout.dropdown_items, fire);
                    actFire.setDropDownBackgroundResource(R.color.white);
                    actFire.setAdapter(fireAdapter);

                    ((AutoCompleteTextView)tilFire.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selFire = fireAdapter.getItem(position);

                            if (selFire == "First Alarm")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);
                                etIns.setText("Kasalukuyang nakasailalim sa FIRST ALARM ang sunog na nangyayari sa *Brgy. Tungkong Managa, Lungsod ng San Jose del Monte.* Patuloy ang mga bumbero sa pag-apula sa sunog. Ang mga tao sa kalapit-bahay ay pansamantalang pinalilikas. Manatiling alerto, at ang lahat ay pinag-iingat.");
                            }
                            else if (selFire == "Second Alarm")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);
                                etIns.setText("Kasalukuyang nakasailalim sa SECOND ALARM ang sunog na nangyayari sa *Brgy. Tungkong Managa, Lungsod ng San Jose del Monte.* Patuloy ang mga bumbero sa pag-apula sa sunog. Ang mga tao sa kalapit-bahay ay pansamantalang pinalilikas. Manatiling alerto, at ang lahat ay pinag-iingat.");
                            }
                            else if (selFire == "Third Alarm")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);
                                etIns.setText("Kasalukuyang nakasailalim sa THIRD ALARM ang sunog na nangyayari sa *Brgy. Tungkong Managa, Lungsod ng San Jose del Monte.* Patuloy ang mga bumbero sa pag-apula sa sunog. Ang mga tao sa kalapit-bahay ay pansamantalang pinalilikas. Manatiling alerto, at ang lahat ay pinag-iingat.");
                            }
                            else if (selFire == "Fourth Alarm")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);
                                etIns.setText("Kasalukuyang nakasailalim sa FOURTH ALARM ang sunog na nangyayari sa *Brgy. Tungkong Managa, Lungsod ng San Jose del Monte.* Patuloy ang mga bumbero sa pag-apula sa sunog. Ang mga tao sa kalapit-bahay ay pansamantalang pinalilikas. Manatiling alerto, at ang lahat ay pinag-iingat.");
                            }
                            else if (selFire == "Fifth Alarm")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);
                                etIns.setText("Kasalukuyang nakasailalim sa FIFTH ALARM ang sunog na nangyayari sa *Brgy. Tungkong Managa, Lungsod ng San Jose del Monte.* Patuloy ang mga bumbero sa pag-apula sa sunog. Ang mga tao sa kalapit-bahay ay pansamantalang pinalilikas. Manatiling alerto, at ang lahat ay pinag-iingat.");
                            }
                        }
                    });
                }
                else if (selType == "FLOOD")
                {
                    tilMag.setVisibility(View.GONE);
                    tilFire.setVisibility(View.GONE);
                    tilRain.setVisibility(View.VISIBLE);
                    tilFlood.setVisibility(View.VISIBLE);
                    tilTy.setVisibility(View.GONE);
                    tilSig.setVisibility(View.GONE);
                    tilIns.setVisibility(View.GONE);

                    ArrayAdapter<String> rainAdapter = new ArrayAdapter<>(Admin_CreateWarning.this, R.layout.dropdown_items, rain);
                    actRain.setDropDownBackgroundResource(R.color.white);
                    actRain.setAdapter(rainAdapter);

                    ((AutoCompleteTextView)tilRain.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selRain = rainAdapter.getItem(position);

                        }
                    });

                    ArrayAdapter<String> floodAdapter = new ArrayAdapter<>(Admin_CreateWarning.this, R.layout.dropdown_items, flood);
                    actFlood.setDropDownBackgroundResource(R.color.white);
                    actFlood.setAdapter(floodAdapter);

                    ((AutoCompleteTextView)tilFlood.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selFlood = floodAdapter.getItem(position);

                            if (selFlood == "Yellow Warning")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);
                                etIns.setText("Ang lebel ng tubig baha ay nananatiling 'Below Alarm Level' at patuloy na binabantayan. Maliit ang tyansa ngunit posible ang matinding pagbaha. Manatiling alerto, at ang lahat ay pinag-iingat.");
                            }
                            else if (selFlood == "Orange Warning")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);
                                etIns.setText("Ang lebel ng tubig baha ay unti-unting tumataas at patuloy na binabantayan. Mataas ang tyansa ng matinding pagbaha, landslide at flashflood. Maging handa sa posibleng paglikas. Manatiling alerto, at ang lahat ay pinag-iingat.");
                            }
                            else if (selFlood == "Red Warning")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);
                                etIns.setText("Ang tubig baha ay kasalukuyang nasa kritikal na lebel at may dulot na matinding pagbaha. Ang mga mga apektadong mamamayang ang inaabisuhan sa agarang paglikas. Manatiling alerto, at ang lahat ay pinag-iingat.");
                            }
                        }
                    });
                }
                else if (selType == "LANDSLIDE")
                {
                    tilMag.setVisibility(View.GONE);
                    tilFire.setVisibility(View.GONE);
                    tilRain.setVisibility(View.GONE);
                    tilFlood.setVisibility(View.GONE);
                    tilTy.setVisibility(View.GONE);
                    tilSig.setVisibility(View.GONE);

                    // palitawin yung instructions pag nakapili na nung level - magset text lang
                    tilIns.setVisibility(View.VISIBLE);
                    etIns.setText("Isang matinding pagguho ng lupa ang naganap sa Brgy. ***, inaasahan ang matinding pag bigat ng trapiko sa naturang lugar at pinapaalalahanan na mag-ingat ang lahat.");
                }
                else if (selType == "TYPHOON")
                {
                    tilMag.setVisibility(View.GONE);
                    tilFire.setVisibility(View.GONE);
                    tilRain.setVisibility(View.GONE);
                    tilFlood.setVisibility(View.GONE);
                    tilTy.setVisibility(View.VISIBLE);
                    tilSig.setVisibility(View.VISIBLE);
                    tilIns.setVisibility(View.GONE);

                    ArrayAdapter<String> sigAdapter = new ArrayAdapter<>(Admin_CreateWarning.this, R.layout.dropdown_items, signal);
                    actSig.setDropDownBackgroundResource(R.color.white);
                    actSig.setAdapter(sigAdapter);

                    ((AutoCompleteTextView)tilSig.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selSig = sigAdapter.getItem(position);

                            if (selSig == "Signal No. 1")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);
                                etIns.setText("Bahagya o halos walang pinsala. Malakas na hangin ang inaasahan sa loob ng 36 oras. Manatiling alerto, at ang lahat ay pinag-iingat.");
                            }
                            else if (selSig == "Signal No. 2")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);
                                etIns.setText("Bahagya hanggang katamtamang pinsala. Malakas na hangin ang inaasahan sa loob ng 24 oras. Manatiling alerto, at ang lahat ay pinag-iingat.");
                            }
                            else if (selSig == "Signal No. 3")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);
                                etIns.setText("Katamtaman hanggang malubhang pinsala. Malakas na hangin ang inaasahan sa loob ng 18 oras. Manatiling alerto, at ang lahat ay pinag-iingat.");
                            }
                            else if (selSig == "Signal No. 4")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);
                                etIns.setText("Malubhang pinsala. Malakas na hangin ang inaasahan sa loob ng 12 oras. Manatiling alerto, at ang lahat ay pinag-iingat. Pagguho ng lupa ay inaasahan ang mga nasa tabing-ilog ay pinalilikas.");
                            }
                            else if (selSig == "Signal No. 5")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);
                                etIns.setText("Matindi at malawakang pinsala. Malakas na hangin ang inaasahan sa loob ng 12 oras. Manatiling alerto, at ang lahat ay pinag-iingat. Matinding pagguho ng lupa ay inaasahan ang mga nasa tabing-ilog ay pinalilikas.");
                            }
                        }
                    });
                }
            }
        });


        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createWarning();

            }
        });
    }

    private void createWarning() {

        DocumentReference df = fStore.collection("UserData").document(userId);
        df.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.getString("adminCity").equals("Bocaue")) {

                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm aa - MMM dd, yyy", Locale.getDefault());
                            SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yy", Locale.getDefault());
                            SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm aa", Locale.getDefault());
                            String disasterDate = sdf2.format(new Date());
                            String disasterDateTime = sdf.format(new Date());
                            String disasterTime = sdf3.format(new Date());

                            DocumentReference df2 = fStore.collection("Warning").document();
                            Map<String, Object> warn = new HashMap<>();
                            warn.put("title", actType.getText().toString());
                            warn.put("body", "Bocaue");
                            warn.put("disasterInfo", etIns.getText().toString());
                            warn.put("eqMagnitude", etMag.getText().toString());
                            warn.put("fireLevel", actFire.getText().toString());
                            warn.put("floodLevel", actFlood.getText().toString());
                            warn.put("floodRain", actRain.getText().toString());
                            warn.put("typhoonName", etTy.getText().toString());
                            warn.put("typhoonSignal", actSig.getText().toString());
                            warn.put("disasterDate", disasterDate);
                            warn.put("disasterDateTime", disasterDateTime);
                            warn.put("disasterTime", disasterTime);

                            df2.set(warn)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Admin_CreateWarning.this, "Warning Posted!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), Admin_Disaster.class);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.slide_in_left,
                                                    R.anim.slide_out_right);
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Admin_CreateWarning.this, "Warning Failed to Post.", Toast.LENGTH_SHORT).show();
                                            Log.e("TAG", "onFailure: WARNING POST FAIL" + e.getMessage() );
                                        }
                                    });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Marilao")) {

                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm aa - MMM dd, yyy", Locale.getDefault());
                            SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yy", Locale.getDefault());
                            SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm aa", Locale.getDefault());
                            String disasterDate = sdf2.format(new Date());
                            String disasterDateTime = sdf.format(new Date());
                            String disasterTime = sdf3.format(new Date());

                            DocumentReference df2 = fStore.collection("Warning").document();
                            Map<String, Object> warn = new HashMap<>();
                            warn.put("title", actType.getText().toString());
                            warn.put("body", "Marilao");
                            warn.put("disasterInfo", etIns.getText().toString());
                            warn.put("eqMagnitude", etMag.getText().toString());
                            warn.put("fireLevel", actFire.getText().toString());
                            warn.put("floodLevel", actFlood.getText().toString());
                            warn.put("floodRain", actRain.getText().toString());
                            warn.put("typhoonName", etTy.getText().toString());
                            warn.put("typhoonSignal", actSig.getText().toString());
                            warn.put("disasterDate", disasterDate);
                            warn.put("disasterDateTime", disasterDateTime);
                            warn.put("disasterTime", disasterTime);

                            df2.set(warn)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Admin_CreateWarning.this, "Warning Posted!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), Admin_Disaster.class);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.slide_in_left,
                                                    R.anim.slide_out_right);
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Admin_CreateWarning.this, "Warning Failed to Post.", Toast.LENGTH_SHORT).show();
                                            Log.e("TAG", "onFailure: WARNING POST FAIL" + e.getMessage() );
                                        }
                                    });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Meycauayan")) {

                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm aa - MMM dd, yyy", Locale.getDefault());
                            SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yy", Locale.getDefault());
                            SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm aa", Locale.getDefault());
                            String disasterDate = sdf2.format(new Date());
                            String disasterDateTime = sdf.format(new Date());
                            String disasterTime = sdf3.format(new Date());

                            DocumentReference df2 = fStore.collection("Warning").document();
                            Map<String, Object> warn = new HashMap<>();
                            warn.put("title", actType.getText().toString());
                            warn.put("body", "Meycauayan");
                            warn.put("disasterInfo", etIns.getText().toString());
                            warn.put("eqMagnitude", etMag.getText().toString());
                            warn.put("fireLevel", actFire.getText().toString());
                            warn.put("floodLevel", actFlood.getText().toString());
                            warn.put("floodRain", actRain.getText().toString());
                            warn.put("typhoonName", etTy.getText().toString());
                            warn.put("typhoonSignal", actSig.getText().toString());
                            warn.put("disasterDate", disasterDate);
                            warn.put("disasterDateTime", disasterDateTime);
                            warn.put("disasterTime", disasterTime);

                            df2.set(warn)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Admin_CreateWarning.this, "Warning Posted!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), Admin_Disaster.class);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.slide_in_left,
                                                    R.anim.slide_out_right);
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Admin_CreateWarning.this, "Warning Failed to Post", Toast.LENGTH_SHORT).show();
                                            Log.e("TAG", "onFailure: WARNING POST FAIL" + e.getMessage() );
                                        }
                                    });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("San Jose del Monte")) {

                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm aa - MMM dd, yyy", Locale.getDefault());
                            SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yy", Locale.getDefault());
                            SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm aa", Locale.getDefault());
                            String disasterDate = sdf2.format(new Date());
                            String disasterDateTime = sdf.format(new Date());
                            String disasterTime = sdf3.format(new Date());

                            DocumentReference df2 = fStore.collection("Warning").document();
                            Map<String, Object> warn = new HashMap<>();
                            warn.put("title", actType.getText().toString());
                            warn.put("body", "San Jose del Monte");
                            warn.put("disasterInfo", etIns.getText().toString());
                            warn.put("eqMagnitude", etMag.getText().toString());
                            warn.put("fireLevel", actFire.getText().toString());
                            warn.put("floodLevel", actFlood.getText().toString());
                            warn.put("floodRain", actRain.getText().toString());
                            warn.put("typhoonName", etTy.getText().toString());
                            warn.put("typhoonSignal", actSig.getText().toString());
                            warn.put("disasterDate", disasterDate);
                            warn.put("disasterDateTime", disasterDateTime);
                            warn.put("disasterTime", disasterTime);

                            df2.set(warn)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Admin_CreateWarning.this, "Warning Posted!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), Admin_Disaster.class);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.slide_in_left,
                                                    R.anim.slide_out_right);
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Admin_CreateWarning.this, "Warning Failed to Post.", Toast.LENGTH_SHORT).show();
                                            Log.e("TAG", "onFailure: WARNING POST FAIL" + e.getMessage() );
                                        }
                                    });
                        }
                        else if(documentSnapshot.getString("adminCity").equals("Santa Maria")) {

                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm aa - MMM dd, yyy", Locale.getDefault());
                            SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yy", Locale.getDefault());
                            SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm aa", Locale.getDefault());
                            String disasterDate = sdf2.format(new Date());
                            String disasterDateTime = sdf.format(new Date());
                            String disasterTime = sdf3.format(new Date());

                            DocumentReference df2 = fStore.collection("Warning").document();
                            Map<String, Object> warn = new HashMap<>();
                            warn.put("title", actType.getText().toString());
                            warn.put("body", "Santa Maria");
                            warn.put("disasterInfo", etIns.getText().toString());
                            warn.put("eqMagnitude", etMag.getText().toString());
                            warn.put("fireLevel", actFire.getText().toString());
                            warn.put("floodLevel", actFlood.getText().toString());
                            warn.put("floodRain", actRain.getText().toString());
                            warn.put("typhoonName", etTy.getText().toString());
                            warn.put("typhoonSignal", actSig.getText().toString());
                            warn.put("disasterDate", disasterDate);
                            warn.put("disasterDateTime", disasterDateTime);
                            warn.put("disasterTime", disasterTime);

                            df2.set(warn)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Admin_CreateWarning.this, "Warning Posted!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), Admin_Disaster.class);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.slide_in_left,
                                                    R.anim.slide_out_right);
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Admin_CreateWarning.this, "Warning Failed to Post.", Toast.LENGTH_SHORT).show();
                                            Log.e("TAG", "onFailure: WARNING POST FAIL" + e.getMessage() );
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
        Intent intent = new Intent(getApplicationContext(), Admin_Disaster.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
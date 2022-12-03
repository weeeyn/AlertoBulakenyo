package com.activity.alertobulakenyo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Admin_CreateWarning extends AppCompatActivity {

    TextInputLayout tilType, tilCity, tilBrgy, tilMag, tilFire, tilRain, tilFlood, tilTy, tilSig, tilIns;
    AutoCompleteTextView actType, actCity, actBrgy, actFire, actRain, actFlood, actSig;
    EditText etMag, etTy, etIns;
    Dialog dialog;
    Button btnPost;

//    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_create_warning);

//        fStore = FirebaseFirestore.getInstance();

        tilType = (TextInputLayout) findViewById (R.id.tilType);
        tilCity = (TextInputLayout) findViewById (R.id.tilCity);
        tilBrgy = (TextInputLayout) findViewById (R.id.tilBrgy);
        tilMag = (TextInputLayout) findViewById (R.id.tilMag);
        tilFire = (TextInputLayout) findViewById (R.id.tilFire);
        tilRain = (TextInputLayout) findViewById (R.id.tilRain);
        tilFlood = (TextInputLayout) findViewById (R.id.tilFlood);
        tilTy = (TextInputLayout) findViewById (R.id.tilTy);
        tilSig = (TextInputLayout) findViewById (R.id.tilSig);
        tilIns = (TextInputLayout) findViewById (R.id.tilIns);

        actType = (AutoCompleteTextView) findViewById (R.id.actType);
        actCity = (AutoCompleteTextView) findViewById (R.id.actCity);
        actBrgy = (AutoCompleteTextView) findViewById (R.id.actBrgy);
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

        String [] city = {"Bocaue", "Marilao", "Meycauayan", "San Jose del Monte", "Santa Maria"};

        String [] brgyBoc = {"ALL", "Antipona", "Bagumbayan", "Bambang", "Batia", "Biñang 1st",
                "Biñang 2nd", "Bolacan", "Bundukan", "Bunlo", "Caingin", "Duhat", "Igulot",
                "Lolomboy", "Poblacion", "Sulucan", "Taal", "Tambobong", "Turo", "Wakas"};

        String [] brgyMar = {"ALL", "Abangan Norte", "Abangan Sur", "Ibayo", "Lambakin", "Lias",
                "Loma de Gato", "Nagbalon", "Patubig", "Poblacion I", "Poblacion II", "Prenza I",
                "Prenza II", "Santa Rosa I", "Santa Rosa II", "Saog", "Tabing Ilog"};

        String [] brgyMey = {"ALL", "Bagbaguin", "Bahay Pare", "Bancal", "Banga", "Bayugo", "Caingin",
                "Calvario", "Camalig", "Hulo", "Iba", "Langka", "Lawa", "Libtong", "Liputan", "Longos",
                "Malhacan", "Pajo", "Pandayan", "Pantoc", "Perez", "Poblacion", "Saluysoy",
                "Saint Francis (Gasak)", "Tugatog", "Ubihan", "Zamora"};

        String [] brgySJDM = {"ALL", "Assumption", "Bagong Buhay I", "Bagong Buhay II", "Bagong Buhay III",
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

        String [] brgySanMa = {"ALL", "Bagbaguin", "Balasing", "Buenavista", "Bulac", "Camangyanan",
                "Catmon", "Cay Pombo", "Caysio", "Guyong", "Lalakhan", "Mag-asawang Sapa",
                "Mahabang Parang", "Manggahan", "Parada", "Poblacion", "Pulong Buhangin", "San Gabriel",
                "San Jose Patag", "San Vicente", "Santa Clara", "Santa Cruz", "Silangan",
                "Tabing Bakod", "Tumana"};

        String [] fire = {"First Alarm", "Second Alarm", "Third Alarm", "Fourth Alarm", "Fifth Alarm",
                "Task Force Alpha", "Task Force Bravo", "Task Force Charlie", "Task Force Delta",
                "General Alarm", "Under Control", "Fireout"};

        String [] rain = {"Yellow Warning", "Orange Warning", "Red Warning"};

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

                    // palitawin yung instructions pag nakapili na nung level - magset text lang
                    tilIns.setVisibility(View.VISIBLE);



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

                            }
                            else if (selFire == "Second Alarm")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);


                            }
                            else if (selFire == "Third Alarm")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);


                            }
                            else if (selFire == "Fourth Alarm")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);


                            }
                            else if (selFire == "Fifth Alarm")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);


                            }
                            else if (selFire == "Task Force Alpha")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);


                            }
                            else if (selFire == "Task Force Bravo")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);


                            }
                            else if (selFire == "Task Force Charlie")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);


                            }
                            else if (selFire == "Task Force Delta")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);


                            }
                            else if (selFire == "General Alarm")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);


                            }
                            else if (selFire == "Under Control")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);


                            }
                            else if (selFire == "Fireout")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);


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
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selRain = rainAdapter.getItem(position);

                            if (selRain == "Yellow Warning")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);


                            }
                            else if (selRain == "Orange Warning")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);


                            }
                            else if (selRain == "Red Warning")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);


                            }
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


                            }
                            else if (selFlood == "Orange Warning")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);


                            }
                            else if (selFlood == "Red Warning")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);


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


                            }
                            else if (selSig == "Signal No. 2")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);


                            }
                            else if (selSig == "Signal No. 3")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);


                            }
                            else if (selSig == "Signal No. 4")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);


                            }
                            else if (selSig == "Signal No. 5")
                            {
                                // palitawin yung instructions pag nakapili na nung level - magset text lang
                                tilIns.setVisibility(View.VISIBLE);


                            }
                        }
                    });
                }
            }
        });

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(Admin_CreateWarning.this, R.layout.dropdown_items, city);
        actCity.setDropDownBackgroundResource(R.color.white);
        actCity.setAdapter(cityAdapter);

        ((AutoCompleteTextView)tilCity.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = cityAdapter.getItem(position);

                if (selectedCity == "Bocaue")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(Admin_CreateWarning.this, R.layout.dropdown_items, brgyBoc);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);
                        }
                    });
                }
                else if (selectedCity == "Marilao")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(Admin_CreateWarning.this, R.layout.dropdown_items, brgyMar);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);
                        }
                    });
                }
                else if (selectedCity == "Meycauayan")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(Admin_CreateWarning.this, R.layout.dropdown_items, brgyMey);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);
                        }
                    });
                }
                else if (selectedCity == "San Jose del Monte")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(Admin_CreateWarning.this, R.layout.dropdown_items, brgySJDM);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);
                        }
                    });
                }
                else if (selectedCity == "Santa Maria")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(Admin_CreateWarning.this, R.layout.dropdown_items, brgySanMa);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);
                        }
                    });
                }
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                createWarning();

            }
        });
    }

//    private void createWarning() {
//
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm aa - MMM dd, yyy", Locale.getDefault());
//        SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yy", Locale.getDefault());
//        SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm aa", Locale.getDefault());
//        String disasterDate = sdf2.format(new Date());
//        String disasterDateTime = sdf.format(new Date());
//        String disasterTime = sdf3.format(new Date());
//
//        DocumentReference df = fStore.collection("Warning").document();
//        Map<String, Object> warn = new HashMap<>();
//        warn.put("disasterType", actType.getText().toString());
//        warn.put("disasterCity", actCity.getText().toString());
//        warn.put("disasterBrgy", actBrgy.getText().toString());
//        warn.put("disasterInfo", etIns.getText().toString());
//        warn.put("eqMagnitude", etMag.getText().toString());
//        warn.put("fireLevel", actFire.getText().toString());
//        warn.put("floodLevel", actFlood.getText().toString());
//        warn.put("floodRain", actRain.getText().toString());
//        warn.put("typhoonName", etTy.getText().toString());
//        warn.put("typhoonSignal", actSig.getText().toString());
//        warn.put("disasterDate", disasterDate);
//        warn.put("disasterDateTime", disasterDateTime);
//        warn.put("disasterTime", disasterTime);
//
//        df.set(warn)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(Admin_CreateWarning.this, "Announcement Posted!", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getApplicationContext(), Admin_Disaster.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(Admin_CreateWarning.this, "Warning Posted", Toast.LENGTH_SHORT).show();
//                        Log.e("TAG", "onFailure: WARNING POST FAIL" + e.getMessage() );
//                    }
//                });
//
//
//
//    }

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
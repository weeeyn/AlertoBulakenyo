package com.activity.alertobulakenyo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class Admin_CreateWarning extends AppCompatActivity {

    TextInputLayout tilType, tilCity, tilBrgy, tilMag, tilFire, tilRain, tilFlood, tilTy, tilSig, tilIns;
    AutoCompleteTextView actType, actCity, actBrgy, actFire, actRain, actFlood, actSig;
    EditText etMag, etTy, etIns;
    Dialog dialog;
    Button btnPreview, btnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_create_warning);

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

        btnPreview = (Button) findViewById (R.id.btnPreview);
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


                }
                else if (selType == "FIRE")
                {
                    tilMag.setVisibility(View.GONE);
                    tilFire.setVisibility(View.VISIBLE);
                    tilRain.setVisibility(View.GONE);
                    tilFlood.setVisibility(View.GONE);
                    tilTy.setVisibility(View.GONE);
                    tilSig.setVisibility(View.GONE);

                    ArrayAdapter<String> fireAdapter = new ArrayAdapter<>(Admin_CreateWarning.this, R.layout.dropdown_items, fire);
                    actFire.setDropDownBackgroundResource(R.color.white);
                    actFire.setAdapter(fireAdapter);

                    ((AutoCompleteTextView)tilFire.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selFire = fireAdapter.getItem(position);

                            if (selFire == "First Alarm")
                            {

                            }
                            else if (selFire == "Second Alarm")
                            {

                            }
                            else if (selFire == "Third Alarm")
                            {

                            }
                            else if (selFire == "Fourth Alarm")
                            {

                            }
                            else if (selFire == "Fifth Alarm")
                            {

                            }
                            else if (selFire == "Task Force Alpha")
                            {

                            }
                            else if (selFire == "Task Force Bravo")
                            {

                            }
                            else if (selFire == "Task Force Charlie")
                            {

                            }
                            else if (selFire == "Task Force Delta")
                            {

                            }
                            else if (selFire == "General Alarm")
                            {

                            }
                            else if (selFire == "Under Control")
                            {

                            }
                            else if (selFire == "Fireout")
                            {

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

                    ArrayAdapter<String> rainAdapter = new ArrayAdapter<>(Admin_CreateWarning.this, R.layout.dropdown_items, rain);
                    actRain.setDropDownBackgroundResource(R.color.white);
                    actRain.setAdapter(rainAdapter);

                    ((AutoCompleteTextView)tilRain.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selRain = rainAdapter.getItem(position);

                            if (selRain == "Yellow Warning")
                            {

                            }
                            else if (selRain == "Orange Warning")
                            {

                            }
                            else if (selRain == "Red Warning")
                            {

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

                            }
                            else if (selFlood == "Orange Warning")
                            {

                            }
                            else if (selFlood == "Red Warning")
                            {

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


                }
                else if (selType == "TYPHOON")
                {
                    tilMag.setVisibility(View.GONE);
                    tilFire.setVisibility(View.GONE);
                    tilRain.setVisibility(View.GONE);
                    tilFlood.setVisibility(View.GONE);
                    tilTy.setVisibility(View.VISIBLE);
                    tilSig.setVisibility(View.VISIBLE);

                    ArrayAdapter<String> sigAdapter = new ArrayAdapter<>(Admin_CreateWarning.this, R.layout.dropdown_items, signal);
                    actSig.setDropDownBackgroundResource(R.color.white);
                    actSig.setAdapter(sigAdapter);

                    ((AutoCompleteTextView)tilSig.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selSig = sigAdapter.getItem(position);

                            if (selSig == "Signal No. 1")
                            {

                            }
                            else if (selSig == "Signal No. 2")
                            {

                            }
                            else if (selSig == "Signal No. 3")
                            {

                            }
                            else if (selSig == "Signal No. 4")
                            {

                            }
                            else if (selSig == "Signal No. 5")
                            {

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

        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Admin_CreateWarning.this);
                dialog.setContentView(R.layout.dialog_sample_disaster_alert);

                Button btnEvac = (Button) dialog.findViewById (R.id.btnEvac);
                Button btnClose = (Button) dialog.findViewById (R.id.btnClose);

                btnEvac.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Admin_CreateWarning.this, Admin_Evacuation.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right,
                                R.anim.slide_out_left);
                    }
                });

                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Admin_CreateWarning.this, "Warning Posted!", Toast.LENGTH_SHORT).show();

                finish();
                finishActivity(107);
                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);

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
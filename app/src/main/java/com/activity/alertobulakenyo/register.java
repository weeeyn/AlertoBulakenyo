package com.activity.alertobulakenyo;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class register extends AppCompatActivity {

    private TextInputLayout tilCity, tilBrgy;
    private EditText etRegFname, etRegLname, etRegUsername, etRegEmail, etRegPass, etRegConPass, etRegContact, etRegHouse;
    private AutoCompleteTextView actCity, actBrgy, actProvince;
    private CheckBox cbAgree1, cbAgree2;
    private Button btnSignup;
    private TextView tvTerms, tvPrivacy, tvLogin;
    private int cb1 = 0, cb2 = 0;
    private ProgressBar progressBar;

    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_register);

        progressBar = findViewById(R.id.progressBar);

        tilCity = (TextInputLayout) findViewById (R.id.tilCity);
        tilBrgy = (TextInputLayout) findViewById (R.id.tilBrgy);

        etRegFname = (EditText) findViewById (R.id.etRegFname);
        etRegLname = (EditText) findViewById (R.id.etRegLname);
        etRegUsername = (EditText) findViewById (R.id.etRegUsername);
        etRegContact = (EditText) findViewById (R.id.etRegContact);
        etRegHouse = (EditText) findViewById (R.id.etRegHouse);
        etRegEmail = (EditText) findViewById (R.id.etRegEmail);
        etRegPass = (EditText) findViewById (R.id.etRegPass);
        etRegConPass = (EditText) findViewById (R.id.etRegConPass);

        actBrgy = (AutoCompleteTextView) findViewById (R.id.actBrgy);
        actCity = (AutoCompleteTextView) findViewById (R.id.actCity);
        actProvince = (AutoCompleteTextView) findViewById (R.id.actProvince);

        cbAgree1 = (CheckBox) findViewById (R.id.cbAgree1);
        cbAgree2 = (CheckBox) findViewById (R.id.cbAgree2);
        btnSignup = (Button) findViewById (R.id.btnSignup);

        tvTerms = (TextView) findViewById (R.id.tvTerms);
        tvPrivacy = (TextView) findViewById (R.id.tvPrivacy);
        tvLogin = (TextView) findViewById (R.id.tvLogin);

        final String[][] city = {{"Bocaue", "Marilao", "Meycauayan", "San Jose del Monte", "Santa Maria"}};

        String[] brgyBoc = {"Antipona", "Bagumbayan", "Bambang", "Batia", "Biñang 1st", "Biñang 2nd",
                "Bolacan", "Bundukan", "Bunlo", "Caingin", "Duhat", "Igulot", "Lolomboy", "Poblacion",
                "Sulucan", "Taal", "Tambobong", "Turo", "Wakas"};

        String[] brgyMar = {"Abangan Norte", "Abangan Sur", "Ibayo", "Lambakin", "Lias", "Loma de Gato",
                "Nagbalon", "Patubig", "Poblacion I", "Poblacion II", "Prenza I", "Prenza II",
                "Santa Rosa I", "Santa Rosa II", "Saog", "Tabing Ilog"};

        String[] brgyMey = {"Bagbaguin", "Bahay Pare", "Bancal", "Banga", "Bayugo", "Caingin",
                "Calvario", "Camalig", "Hulo", "Iba", "Langka", "Lawa", "Libtong", "Liputan", "Longos",
                "Malhacan", "Pajo", "Pandayan", "Pantoc", "Perez", "Poblacion", "Saluysoy",
                "Saint Francis (Gasak)", "Tugatog", "Ubihan", "Zamora"};

        String[] brgySJDM = {"Assumption", "Bagong Buhay I", "Bagong Buhay II", "Bagong Buhay III",
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

        String[] brgySanMa = {"Bagbaguin", "Balasing", "Buenavista", "Bulac", "Camangyanan", "Catmon",
                "Cay Pombo", "Caysio", "Guyong", "Lalakhan", "Mag-asawang Sapa", "Mahabang Parang",
                "Manggahan", "Parada", "Poblacion", "Pulong Buhangin", "San Gabriel", "San Jose Patag",
                "San Vicente", "Santa Clara", "Santa Cruz", "Silangan", "Tabing Bakod", "Tumana"};

        String[] province = {"Bulacan"};

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(register.this, R.layout.dropdown_items, city[0]);
        actCity.setDropDownBackgroundResource(R.color.white);
        actCity.setAdapter(cityAdapter);

        ((AutoCompleteTextView) tilCity.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = cityAdapter.getItem(position);

                if (selectedCity == "Bocaue") {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(register.this, R.layout.dropdown_items, brgyBoc);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView) tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);
                        }
                    });
                } else if (selectedCity == "Marilao") {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(register.this, R.layout.dropdown_items, brgyMar);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView) tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);
                        }
                    });
                } else if (selectedCity == "Meycauayan") {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(register.this, R.layout.dropdown_items, brgyMey);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView) tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);
                        }
                    });
                } else if (selectedCity == "San Jose del Monte") {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(register.this, R.layout.dropdown_items, brgySJDM);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView) tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);
                        }
                    });
                } else if (selectedCity == "Santa Maria") {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(register.this, R.layout.dropdown_items, brgySanMa);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView) tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);
                        }
                    });
                }
            }
        });

        ArrayAdapter<String> provAdapter = new ArrayAdapter<>(register.this, R.layout.dropdown_items, province);
        actProvince.setDropDownBackgroundResource(R.color.white);
        actProvince.setAdapter(provAdapter);

        tvTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cbAgree1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAgree1.isChecked()) {
                    cb1 = 1;
                }
                else {
                    cb1 = 0;
                }
            }
        });

        cbAgree2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAgree2.isChecked()) {
                    cb2 = 1;
                }
                else {
                    cb2 = 0;
                }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegister();
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);
            }
        });

    }

    private void userRegister() {

        if ((cb1 == 1) && (cb2 == 1)) {
            String firstname = etRegFname.getText().toString();
            String lastname = etRegLname.getText().toString();
            String username = etRegUsername.getText().toString();
            String contact = etRegContact.getText().toString();
            String house = etRegHouse.getText().toString();
            String city = actCity.getText().toString();
            String brgy = actBrgy.getText().toString();
            String province = actProvince.getText().toString();
            String email = etRegEmail.getText().toString();
            String password = etRegPass.getText().toString();
            String conpass = etRegConPass.getText().toString();

            if (firstname.isEmpty()) {
                Toast.makeText(this, "Please enter your First Name.", Toast.LENGTH_SHORT).show();
                etRegFname.setError("First Name is Required!");
                etRegFname.requestFocus();
                return;
            } else if (lastname.isEmpty()) {
                Toast.makeText(this, "Please enter your Last Name.", Toast.LENGTH_SHORT).show();
                etRegLname.setError("Last Name is Required!");
                etRegLname.requestFocus();
                return;
            } else if (username.isEmpty()) {
                Toast.makeText(this, "Please enter your Username.", Toast.LENGTH_SHORT).show();
                etRegUsername.setError("Username is Required!");
                etRegUsername.requestFocus();
                return;
            } else if (contact.isEmpty()) {
                Toast.makeText(this, "Please enter your Contact Number.", Toast.LENGTH_SHORT).show();
                etRegContact.setError("Contact Number is Required!");
                etRegContact.requestFocus();
                return;
            } else if (contact.length() != 11) {
                Toast.makeText(register.this, "Please re-enter your contact number.", Toast.LENGTH_SHORT).show();
                etRegContact.setError("Contact Number is Invalid");
                etRegContact.requestFocus();
                return;
            } else if (house.isEmpty()) {
                Toast.makeText(this, "Please enter your House Address containing its House Number and Street Name.", Toast.LENGTH_SHORT).show();
                etRegHouse.setError("House Address is Required!");
                etRegHouse.requestFocus();
                return;
            } else if (city.isEmpty()) {
                Toast.makeText(this, "Please select your City.", Toast.LENGTH_SHORT).show();
                actCity.setError("City selection is Required!");
                actCity.requestFocus();
                return;
            } else if (brgy.isEmpty()) {
                Toast.makeText(this, "Please select your Barangay.", Toast.LENGTH_SHORT).show();
                actBrgy.setError("Barangay selection is Required!");
                actBrgy.requestFocus();
                return;
            } else if (province.isEmpty()) {
                Toast.makeText(this, "Please select the Province.", Toast.LENGTH_SHORT).show();
                actProvince.setError("Province selection is Required!");
                actProvince.requestFocus();
                return;
            } else if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your Valid Email.", Toast.LENGTH_SHORT).show();
                etRegEmail.setError("Email is Required!");
                etRegEmail.requestFocus();
                return;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(register.this, "Please re-enter your Valid Email.", Toast.LENGTH_SHORT).show();
                etRegEmail.setError("Email is Invalid!");
                etRegEmail.requestFocus();
                return;
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Please enter your Password.", Toast.LENGTH_SHORT).show();
                etRegPass.setError("Password is Required!");
                etRegPass.requestFocus();
                return;
            } else if (password.length() < 6) {
                Toast.makeText(register.this, "Please re-enter your password.", Toast.LENGTH_SHORT).show();
                etRegPass.setError("Minimum password length should be 6 characters");
                etRegPass.requestFocus();
                return;
            } else if (conpass.isEmpty()) {
                Toast.makeText(this, "Please re-enter your Password.", Toast.LENGTH_SHORT).show();
                etRegConPass.setError("Password Do Not Match!");
                etRegConPass.requestFocus();
                return;
            }
            else {
                fAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    UserInfo userInfo = new UserInfo (firstname, lastname, username, contact, house, city, brgy, province, email);

                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(userInfo)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    Log.d(TAG, "onComplete: ACCOUNT SUCCESSFULLY CREATED!");
                                                    progressBar.setVisibility(View.GONE);

                                                    startActivity(new Intent(register.this, login.class));
                                                    finish();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e(TAG, e.getMessage() );
                                        }
                                    });
                                } else {
                                    Log.d(TAG, "onComplete: FAILED TO REGISTER.");
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, e.getMessage() );
                    }
                });
            }


        } else {
            Toast.makeText(this, "Please read and agree to the Terms of Service and Privacy Policy.", Toast.LENGTH_SHORT).show();
        }

    }

    private void sendVerificationEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: EMAIL SENT.");
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(register.this, login.class));
                            finish();
                        } else {
                            Log.d(TAG, "onComplete: EMAIL NOT SENT.");
                            overridePendingTransition(R.anim.slide_in_left,
                                    R.anim.slide_out_right);
                            finish();
                            overridePendingTransition(R.anim.slide_in_left,
                                    R.anim.slide_out_right);
                            startActivity(getIntent());
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
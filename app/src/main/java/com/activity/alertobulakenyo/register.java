package com.activity.alertobulakenyo;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    TextInputLayout tilCity, tilBrgy;
    EditText firstNameET, lastNameET, usernameET, emailET, passwordET, confirmPasswordET, etContactET, etHouseET;
    AutoCompleteTextView actCity, actBrgy;
    CheckBox cbAgree1, cbAgree2;
    Button btnSignup;
    TextView tvTerms, tvPrivacy, tvLogin;
    int cb1 = 0, cb2 = 0;
    ProgressBar progressBar;

    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_register);

        progressBar = (ProgressBar) findViewById (R.id.progressBar);

        firstNameET = (EditText) findViewById (R.id.etRegFname);
        lastNameET = (EditText) findViewById (R.id.etRegLname);
        usernameET = (EditText) findViewById (R.id.etRegUsername);
        emailET = (EditText) findViewById (R.id.etRegEmail);
        passwordET = (EditText) findViewById (R.id.etRegPass);
        confirmPasswordET = (EditText) findViewById (R.id.etRegConPass);

        cbAgree1 = (CheckBox) findViewById (R.id.cbAgree1);
        cbAgree2 = (CheckBox) findViewById (R.id.cbAgree2);
        btnSignup = (Button) findViewById (R.id.btnSignup);

        tvTerms = (TextView) findViewById (R.id.tvTerms);
        tvPrivacy = (TextView) findViewById (R.id.tvPrivacy);
        tvLogin = (TextView) findViewById (R.id.tvLogin);

        tilCity = (TextInputLayout) findViewById(R.id.tilCity);
        tilBrgy = (TextInputLayout) findViewById(R.id.tilBrgy);

        etContactET = (EditText) findViewById(R.id.etRegContact);
        etHouseET = (EditText) findViewById(R.id.etRegHouse);

        actBrgy = (AutoCompleteTextView) findViewById(R.id.actBrgy);
        actCity = (AutoCompleteTextView) findViewById(R.id.actCity);

        String[] city = {"Bocaue", "Marilao", "Meycauayan", "San Jose del Monte", "Santa Maria"};

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

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(register.this, R.layout.dropdown_items, city);
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

                progressBar.setVisibility(View.VISIBLE);

                if ( (cb1 == 1) && (cb2 == 1) ) {

                    String firstname = firstNameET.getText().toString();
                    String lastname = lastNameET.getText().toString();
                    String username = usernameET.getText().toString();
                    String contact = etContactET.getText().toString();
                    String house = etHouseET.getText().toString();
                    String city = actCity.getText().toString();
                    String brgy = actBrgy.getText().toString();
                    String email = emailET.getText().toString();
                    String password = passwordET.getText().toString();
                    String confirmPass = confirmPasswordET.getText().toString();

                    if (firstname.isEmpty()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(register.this, "Please enter your First Name.", Toast.LENGTH_SHORT).show();
                        firstNameET.setError("First Name can't be empty!");
                        firstNameET.requestFocus();
                        return;
                    } else if (lastname.isEmpty()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(register.this, "Please enter your Last Name.", Toast.LENGTH_SHORT).show();
                        lastNameET.setError("Last Name can't be empty!");
                        lastNameET.requestFocus();
                        return;
                    } else if (username.isEmpty()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(register.this, "Please enter your Username.", Toast.LENGTH_SHORT).show();
                        usernameET.setError("Username can't be empty!");
                        usernameET.requestFocus();
                        return;
                    } else if (contact.isEmpty()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(register.this, "Please enter your Contact Number.", Toast.LENGTH_SHORT).show();
                        etContactET.setError("Contact Number can't be empty!");
                        etContactET.requestFocus();
                        return;
                    } else if (contact.length() != 11) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(register.this, "Please enter a Valid Contact Number.", Toast.LENGTH_SHORT).show();
                        etContactET.setError("Invalid Contact Number!");
                        etContactET.requestFocus();
                        return;
                    } else if (house.isEmpty()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(register.this, "Please enter your House No. and Street Name.", Toast.LENGTH_SHORT).show();
                        etHouseET.setError("House Address can't be empty!");
                        etHouseET.requestFocus();
                        return;
                    } else if (city.isEmpty()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(register.this, "Please select your City.", Toast.LENGTH_SHORT).show();
                        actCity.setError("City Address can't be empty!");
                        actCity.requestFocus();
                        return;
                    } else if (brgy.isEmpty()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(register.this, "Please select your Barangay.", Toast.LENGTH_SHORT).show();
                        actBrgy.setError("Barangay Address can't be empty!");
                        actBrgy.requestFocus();
                        return;
                    } else if (email.isEmpty()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(register.this, "Please enter your Email.", Toast.LENGTH_SHORT).show();
                        emailET.setError("Email can't be empty!");
                        emailET.requestFocus();
                        return;
                    } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(register.this, "Please enter a valid Email Address.", Toast.LENGTH_SHORT).show();
                        emailET.setError("Invalid Email!");
                        emailET.requestFocus();
                        return;
                    } else if (password.isEmpty()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(register.this, "Please enter your Password.", Toast.LENGTH_SHORT).show();
                        passwordET.requestFocus();
                        return;
                    } else if (password.length() != 8) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(register.this, "Please enter a password with 8 characters.", Toast.LENGTH_SHORT).show();
                        passwordET.requestFocus();
                        return;
                    }
                    else if (!confirmPass.equals(password)) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(register.this, "Password do not match!.", Toast.LENGTH_SHORT).show();
                        confirmPasswordET.requestFocus();
                        return;
                    }
                    else {

                        fAuth.createUserWithEmailAndPassword(email,password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()) {
                                            FirebaseUser resUser = fAuth.getCurrentUser();

                                            resUser.sendEmailVerification()
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            String resUserId = resUser.getUid();

                                                            DocumentReference dRef = fStore.collection("UserData").document(resUserId);
                                                            Map<String, Object> resUserInfo = new HashMap<>();
                                                            resUserInfo.put("resFname", firstname);
                                                            resUserInfo.put("resLname", lastname);
                                                            resUserInfo.put("resUsername", username);
                                                            resUserInfo.put("resContact", contact);
                                                            resUserInfo.put("resHouse", house);
                                                            resUserInfo.put("resCity", city);
                                                            resUserInfo.put("resBrgy", brgy);
                                                            resUserInfo.put("resEmail", email);
                                                            resUserInfo.put("resUserId", resUserId);
                                                            resUserInfo.put("userType", "resident");

                                                            dRef.set(resUserInfo);

                                                            progressBar.setVisibility(View.GONE);
                                                            Toast.makeText(register.this, "Registered Successfully. Please confirm your email to continue logging in.", Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(register.this, login.class);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    });
                                        } else {
                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(register.this, "Registration Unsuccessful.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        progressBar.setVisibility(View.GONE);
                                        Log.e(TAG, "onFailure: REGISTRATION FAIL" + e.getMessage() );
                                    }
                                });
                    }
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(register.this, "Please read and agree to the Terms of Service and Privacy Policy.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        tvTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, Terms.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        tvPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, Privacy.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
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

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), login.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }


}
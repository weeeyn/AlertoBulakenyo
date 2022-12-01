package com.activity.alertobulakenyo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class register extends AppCompatActivity {

    TextInputLayout tilCity, tilBrgy;
    EditText firstName, lastName, username, email, password, confirmPassword, etContact, etHouse;
    AutoCompleteTextView actCity, actBrgy, actProvince;
    CheckBox cbAgree1, cbAgree2;
    Button btnSignup;
    TextView tvTerms, tvPrivacy, tvLogin;
    int cb1 = 0, cb2 = 0;

    boolean valid = true;

    //firebase authentication
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_register);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        firstName = (EditText) findViewById (R.id.etRegFname);
        lastName = (EditText) findViewById (R.id.etRegLname);
        username = (EditText) findViewById (R.id.etRegUsername);
        email = (EditText) findViewById (R.id.etRegEmail);
        password = (EditText) findViewById (R.id.etRegPass);
        confirmPassword = (EditText) findViewById (R.id.etRegConPass);

        cbAgree1 = (CheckBox) findViewById (R.id.cbAgree1);
        cbAgree2 = (CheckBox) findViewById (R.id.cbAgree2);
        btnSignup = (Button) findViewById (R.id.btnSignup);

        tvTerms = (TextView) findViewById (R.id.tvTerms);
        tvPrivacy = (TextView) findViewById (R.id.tvPrivacy);
        tvLogin = (TextView) findViewById (R.id.tvLogin);

        tilCity = (TextInputLayout) findViewById(R.id.tilCity);
        tilBrgy = (TextInputLayout) findViewById(R.id.tilBrgy);

        etContact = (EditText) findViewById(R.id.etRegContact);
        etHouse = (EditText) findViewById(R.id.etRegHouse);

        actBrgy = (AutoCompleteTextView) findViewById(R.id.actBrgy);
        actCity = (AutoCompleteTextView) findViewById(R.id.actCity);
        actProvince = (AutoCompleteTextView) findViewById(R.id.actProvince);

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
                if ( (cb1 == 1) && (cb2 == 1) ) {

                    if (firstName.getText().toString().isEmpty()) {
                        Toast.makeText(register.this, "Please enter your First Name.", Toast.LENGTH_SHORT).show();
                        firstName.setError("First Name can't be empty!");
                        firstName.requestFocus();
                        return;
                    } else if (lastName.getText().toString().isEmpty()) {
                        Toast.makeText(register.this, "Please enter your Last Name.", Toast.LENGTH_SHORT).show();
                        lastName.setError("Last Name can't be empty!");
                        lastName.requestFocus();
                        return;
                    } else if (username.getText().toString().isEmpty()) {
                        Toast.makeText(register.this, "Please enter your Username.", Toast.LENGTH_SHORT).show();
                        username.setError("Username can't be empty!");
                        username.requestFocus();
                        return;
                    } else if (etContact.getText().toString().isEmpty()) {
                        Toast.makeText(register.this, "Please enter your Contact Number.", Toast.LENGTH_SHORT).show();
                        etContact.setError("Contact Number can't be empty!");
                        etContact.requestFocus();
                        return;
                    } else if (etContact.length() != 11) {
                        Toast.makeText(register.this, "Please enter a Valid Contact Number .", Toast.LENGTH_SHORT).show();
                        etContact.setError("Invalid Contact Number!");
                        etContact.requestFocus();
                        return;
                    } else if (etHouse.getText().toString().isEmpty()) {
                        Toast.makeText(register.this, "Please enter your House No. and Street Name.", Toast.LENGTH_SHORT).show();
                        etHouse.setError("House Address can't be empty!");
                        etHouse.requestFocus();
                        return;
                    } else if (actCity.getText().toString().isEmpty()) {
                        Toast.makeText(register.this, "Please select your City.", Toast.LENGTH_SHORT).show();
                        actCity.setError("City Address can't be empty!");
                        actCity.requestFocus();
                        return;
                    } else if (actBrgy.getText().toString().isEmpty()) {
                        Toast.makeText(register.this, "Please select your Barangay.", Toast.LENGTH_SHORT).show();
                        actCity.setError("Barangay Address can't be empty!");
                        actCity.requestFocus();
                        return;
                    } else if (actProvince.getText().toString().isEmpty()) {
                        Toast.makeText(register.this, "Please select Bulacan.", Toast.LENGTH_SHORT).show();
                        actProvince.setError("Bulacan Address can't be empty!");
                        actProvince.requestFocus();
                        return;
                    } else if (email.getText().toString().isEmpty()) {
                        Toast.makeText(register.this, "Please enter your Email.", Toast.LENGTH_SHORT).show();
                        email.setError("Email can't be empty!");
                        email.requestFocus();
                        return;
                    } else if (!Patterns.EMAIL_ADDRESS.matcher((CharSequence) email).matches()) {
                        Toast.makeText(register.this, "Please enter a valid Email Address.", Toast.LENGTH_SHORT).show();
                        email.setError("Invalid Email!");
                        email.requestFocus();
                        return;
                    } else if (password.getText().toString().isEmpty()) {
                        Toast.makeText(register.this, "Please enter your Password.", Toast.LENGTH_SHORT).show();
                        password.requestFocus();
                        return;
                    } else if (!confirmPassword.equals(password)) {
                        Toast.makeText(register.this, "Password do not match!.", Toast.LENGTH_SHORT).show();
                        confirmPassword.requestFocus();
                        return;
                    } else {

                        //registration process starts
                        fAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        FirebaseUser user = fAuth.getCurrentUser();

                                        DocumentReference df = fStore.collection("UserData").document(user.getUid());
                                        Map<String, Object> userInfo = new HashMap<>();
                                        userInfo.put("FirstName", firstName.getText().toString());
                                        userInfo.put("LastName", lastName.getText().toString());
                                        userInfo.put("Username", username.getText().toString());
                                        userInfo.put("Email", email.getText().toString());
                                        userInfo.put("Password", password.getText().toString());
                                        userInfo.put("ConfirmPassword", confirmPassword.getText().toString());
                                        userInfo.put("Contact", etContact.getText().toString());
                                        userInfo.put("HouseAddress", etHouse.getText().toString());
                                        userInfo.put("Barangay", actBrgy.getText().toString());
                                        userInfo.put("City", actCity.getText().toString());
                                        userInfo.put("Province", actProvince.getText().toString());

                                        //specify user access (if user or admin)
                                        userInfo.put("User", "2");

                                        df.set(userInfo);

                                        Toast.makeText(register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), login.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
                else {
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
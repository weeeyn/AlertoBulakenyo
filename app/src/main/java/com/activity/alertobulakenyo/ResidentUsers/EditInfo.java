package com.activity.alertobulakenyo.ResidentUsers;

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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.activity.alertobulakenyo.R;
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
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

public class EditInfo extends AppCompatActivity {

    Button btnSave;
    TextInputLayout tilCity, tilBrgy;
    EditText etFname, etLname, etUsername, etEmail, etCon, etHouse;
    AutoCompleteTextView actBrgy, actCity;
    ProgressBar progressBar;

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

        setContentView(R.layout.activity_edit_info);

        progressBar = (ProgressBar) findViewById (R.id.progressBar);

        btnSave = (Button) findViewById (R.id.btnSave);

        tilCity = (TextInputLayout) findViewById (R.id.tilCity);
        tilBrgy = (TextInputLayout) findViewById (R.id.tilBrgy);

        etFname = (EditText) findViewById (R.id.etFname);
        etLname = (EditText) findViewById (R.id.etLname);
        etUsername = (EditText) findViewById (R.id.etUsername);
        etEmail= (EditText) findViewById (R.id.etEmail);
        etCon = (EditText) findViewById (R.id.etCon);
        etHouse = (EditText) findViewById (R.id.etHouse);

        actBrgy = (AutoCompleteTextView) findViewById (R.id.actBrgy);
        actCity = (AutoCompleteTextView) findViewById (R.id.actCity);

        String [] city = {"Bocaue", "Marilao", "Meycauayan", "San Jose del Monte", "Santa Maria"};

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

        String [] province = {"Bulacan"};

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(EditInfo.this, R.layout.dropdown_items, city);
        actCity.setDropDownBackgroundResource(R.color.white);
        actCity.setAdapter(cityAdapter);

        ((AutoCompleteTextView)tilCity.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = cityAdapter.getItem(position);

                if (selectedCity == "Bocaue")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(EditInfo.this, R.layout.dropdown_items, brgyBoc);
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
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(EditInfo.this, R.layout.dropdown_items, brgyMar);
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
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(EditInfo.this, R.layout.dropdown_items, brgyMey);
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
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(EditInfo.this, R.layout.dropdown_items, brgySJDM);
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
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(EditInfo.this, R.layout.dropdown_items, brgySanMa);
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


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                editProfile();
            }
        });

    }

    private void editProfile() {

        String editFname = etFname.getText().toString();
        String editLname = etLname.getText().toString();
        String editUsername = etUsername.getText().toString();
        String editEmail = etEmail.getText().toString();
        String editContact = etCon.getText().toString();
        String editHouse = etHouse.getText().toString();
        String editBrgy = actBrgy.getText().toString();
        String editCity = actCity.getText().toString();

        if (editFname.isEmpty()) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please enter your First Name.", Toast.LENGTH_SHORT).show();
            etFname.setError("First Name can't be empty!");
            etFname.requestFocus();
            return;
        } else if (editLname.isEmpty()) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please enter your Last Name.", Toast.LENGTH_SHORT).show();
            etLname.setError("Last Name can't be empty!");
            etLname.requestFocus();
            return;
        } else if (editUsername.isEmpty()) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please enter your Username.", Toast.LENGTH_SHORT).show();
            etUsername.setError("Username can't be empty!");
            etUsername.requestFocus();
            return;
        } else if (editContact.isEmpty()) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please enter your Contact Number.", Toast.LENGTH_SHORT).show();
            etCon.setError("Contact Number can't be empty!");
            etCon.requestFocus();
            return;
        } else if (editContact.length() != 11) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please enter a Valid Contact Number.", Toast.LENGTH_SHORT).show();
            etCon.setError("Invalid Contact Number!");
            etCon.requestFocus();
            return;
        } else if (editHouse.isEmpty()) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please enter your House No. and Street Name.", Toast.LENGTH_SHORT).show();
            etHouse.setError("House Address can't be empty!");
            etHouse.requestFocus();
            return;
        } else if (editCity.isEmpty()) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please select your City.", Toast.LENGTH_SHORT).show();
            actCity.setError("City Address can't be empty!");
            actCity.requestFocus();
            return;
        } else if (editBrgy.isEmpty()) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please select your Barangay.", Toast.LENGTH_SHORT).show();
            actBrgy.setError("Barangay Address can't be empty!");
            actBrgy.requestFocus();
            return;
        } else if (editEmail.isEmpty()) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please enter your Email.", Toast.LENGTH_SHORT).show();
            etEmail.setError("Email can't be empty!");
            etEmail.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(editEmail).matches()) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please enter a valid Email Address.", Toast.LENGTH_SHORT).show();
            etEmail.setError("Invalid Email!");
            etEmail.requestFocus();
            return;
        } else {

            final DocumentReference sDoc = fStore.collection("UserData").document(userId);

            fStore.runTransaction(new Transaction.Function<Void>() {
                @Override
                public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                    DocumentSnapshot snapshot = transaction.get(sDoc);

                    transaction.update(sDoc, "resFname", editFname);
                    transaction.update(sDoc, "resLname", editLname);
                    transaction.update(sDoc, "resUsername", editUsername);
                    transaction.update(sDoc, "resEmail", editEmail);
                    transaction.update(sDoc, "resContact", editContact);
                    transaction.update(sDoc, "resHouse", editHouse);
                    transaction.update(sDoc, "resBrgy", editBrgy);
                    transaction.update(sDoc, "resCity", editCity);

                    return null;
                }
            })
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("TAG", "Transaction success!");
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(EditInfo.this, "Profile Updated!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), AccountInfo.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_left,
                                    R.anim.slide_out_right);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("TAG", "Transaction failure.", e);
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(EditInfo.this, "Profile DID NOT Update.", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        DocumentReference df = fStore.collection("UserData").document(userId);
        df.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if(task.getResult().exists()) {

                            String fNameResult = task.getResult().getString("resFname");
                            String lNameResult = task.getResult().getString("resLname");
                            String usernameResult = task.getResult().getString("resUsername");
                            String emailResult = task.getResult().getString("resEmail");
                            String contactResult = task.getResult().getString("resContact");
                            String houseAddResult = task.getResult().getString("resHouse");
                            String brgyResult = task.getResult().getString("resBrgy");

                            etFname.setText(fNameResult);
                            etLname.setText(lNameResult);
                            etUsername.setText(usernameResult);
                            etEmail.setText(emailResult);
                            etCon.setText(contactResult);
                            etHouse.setText(houseAddResult);
                            actBrgy.setText(brgyResult);
                        }
                        else {
                            Toast.makeText(EditInfo.this, "User do no Exist.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), AccountInfo.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
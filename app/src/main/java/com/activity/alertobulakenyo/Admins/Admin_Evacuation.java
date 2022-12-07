package com.activity.alertobulakenyo.Admins;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.activity.alertobulakenyo.ObjectClasses.EvacuationHolder;
import com.activity.alertobulakenyo.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Admin_Evacuation extends AppCompatActivity implements OnMapReadyCallback {

    Button btnAddNewEvac;
    CardView card_Boc, card_Mar, card_Mey, card_SJDM, card_SM;

    //var's for google map
    GoogleMap map;
    Location lastKnownLocation;
    private boolean locationPermissionGranted;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    //default location @ Manila, Philippines
    private final LatLng defaultLocation = new LatLng(14.599512, 120.984222);
    private ArrayList<EvacuationHolder> evacuationHolderArrayList;

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

        setContentView(R.layout.activity_admin_evacuation);

        btnAddNewEvac = (Button) findViewById (R.id.btnAddNewEvac);

        btnAddNewEvac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Evacuation.this, Admin_AddEvac.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

//        card_Boc = (CardView) findViewById (R.id.card_Boc);
//        card_Mar = (CardView) findViewById (R.id.card_Mar);
//        card_Mey = (CardView) findViewById (R.id.card_Mey);
//        card_SJDM = (CardView) findViewById (R.id.card_SJDM);
//        card_SM = (CardView) findViewById (R.id.card_SM);
//
//        DocumentReference df = fStore.collection("UserData").document(userId);
//        df.get()
//                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        if(documentSnapshot.getString("adminCity").equals("Bacaue")) {
//                            card_Boc.setVisibility(View.VISIBLE);
//                            card_Mar.setVisibility(View.GONE);
//                            card_Mey.setVisibility(View.GONE);
//                            card_SJDM.setVisibility(View.GONE);
//                            card_SM.setVisibility(View.GONE);
//
//                            card_Boc.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//
//                                    Intent intent = new Intent(Admin_Evacuation.this, Admin_EvacBocaue.class);
//                                    startActivity(intent);
//                                    overridePendingTransition(R.anim.slide_in_right,
//                                            R.anim.slide_out_left);
//                                }
//                            });
//                        }
//                        else if(documentSnapshot.getString("adminCity").equals("Marilao")) {
//                            card_Boc.setVisibility(View.GONE);
//                            card_Mar.setVisibility(View.VISIBLE);
//                            card_Mey.setVisibility(View.GONE);
//                            card_SJDM.setVisibility(View.GONE);
//                            card_SM.setVisibility(View.GONE);
//
//                            card_Mar.setVisibility(View.VISIBLE);
//                            card_Mar.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//
//                                    Intent intent = new Intent(Admin_Evacuation.this, Admin_EvacMarilao.class);
//                                    startActivity(intent);
//                                    overridePendingTransition(R.anim.slide_in_right,
//                                            R.anim.slide_out_left);
//                                }
//                            });
//
//                        }
//                        else if(documentSnapshot.getString("adminCity").equals("Meycauayan")) {
//                            card_Boc.setVisibility(View.GONE);
//                            card_Mar.setVisibility(View.GONE);
//                            card_Mey.setVisibility(View.VISIBLE);
//                            card_SJDM.setVisibility(View.GONE);
//                            card_SM.setVisibility(View.GONE);
//
//                            card_Mey.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//
//                                    Intent intent = new Intent(Admin_Evacuation.this, Admin_EvacMeycauayan.class);
//                                    startActivity(intent);
//                                    overridePendingTransition(R.anim.slide_in_right,
//                                            R.anim.slide_out_left);
//                                }
//                            });
//
//                        }
//                        else if(documentSnapshot.getString("adminCity").equals("San Jose del Monte")) {
//                            card_Boc.setVisibility(View.GONE);
//                            card_Mar.setVisibility(View.GONE);
//                            card_Mey.setVisibility(View.GONE);
//                            card_SJDM.setVisibility(View.VISIBLE);
//                            card_SM.setVisibility(View.GONE);
//
//                            card_SJDM.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//
//                                    Intent intent = new Intent(Admin_Evacuation.this, Admin_EvacSJDM.class);
//                                    startActivity(intent);
//                                    overridePendingTransition(R.anim.slide_in_right,
//                                            R.anim.slide_out_left);
//                                }
//                            });
//                        }
//                        else if(documentSnapshot.getString("adminCity").equals("Santa Maria")) {
//                            card_Boc.setVisibility(View.GONE);
//                            card_Mar.setVisibility(View.GONE);
//                            card_Mey.setVisibility(View.GONE);
//                            card_SJDM.setVisibility(View.GONE);
//                            card_SM.setVisibility(View.VISIBLE);
//
//                            card_SM.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//
//                                    Intent intent = new Intent(Admin_Evacuation.this, Admin_EvacStaMaria.class);
//                                    startActivity(intent);
//                                    overridePendingTransition(R.anim.slide_in_right,
//                                            R.anim.slide_out_left);
//                                }
//                            });
//                        }
//                    }
//                });


        //call fragment and inflate map
        SupportMapFragment supportMapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        supportMapFragment.getMapAsync(Admin_Evacuation.this);

        //initialize fusedlocation
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Admin_Home.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map=googleMap;



        getLocationPermission();
        updateLocationUI();
        getDeviceLocation();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        locationPermissionGranted = false;
        if (requestCode
                == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationPermissionGranted = true;
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        updateLocationUI();

    }
    private void getDeviceLocation() {
        try {
            if (locationPermissionGranted) {
                Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            lastKnownLocation = task.getResult();
                            if (lastKnownLocation != null) {
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(lastKnownLocation.getLatitude(),
                                                lastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                                // String text= String.valueOf(lastKnownLocation.distanceTo(deltalocation));
                                // Toast.makeText(maps.this, text, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            map.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(defaultLocation, DEFAULT_ZOOM));
                            map.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage(), e);
        }
    }

    private void updateLocationUI() {
        if (map == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                map.setMyLocationEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                map.setMyLocationEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
                lastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }



    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            locationPermissionGranted=true;
        }
        else{
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

        }
    }
}
package com.activity.alertobulakenyo.ResidentUsers;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.Toast;

import com.activity.alertobulakenyo.ObjectClasses.EvacuationHolder;
import com.activity.alertobulakenyo.R;
import com.activity.alertobulakenyo.distanceParameter;
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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Evacuation extends AppCompatActivity implements OnMapReadyCallback {

    CardView card_Boc, card_Mar, card_Mey, card_SJDM, card_SM;

    //var's for google map
    GoogleMap map;
    Location lastKnownLocation,DeltaLocation;
    private boolean locationPermissionGranted;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    //default location @ Manila, Philippines
    private final LatLng defaultLocation = new LatLng(14.599512, 120.984222);
    private ArrayList<EvacuationHolder> evacuationHolderArrayList;
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    ArrayList<Float> distance;
    ArrayList<distanceParameter> dP;
    Button getClosest;
    float min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_evacuation);


        /*  hidden temporarily ... remove comment if needed ...
        card_Boc = (CardView) findViewById (R.id.card_Boc);
        card_Mar = (CardView) findViewById (R.id.card_Mar);
        card_Mey = (CardView) findViewById (R.id.card_Mey);
        card_SJDM = (CardView) findViewById (R.id.card_SJDM);
        card_SM = (CardView) findViewById (R.id.card_SM);

        card_Boc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), EvacBocaue.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        card_Mar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), EvacMarilao.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        card_Mey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), EvacMeycauayan.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        card_SJDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), EvacSJDM.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        card_SM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), EvacStaMaria.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        */

        //call fragment and inflate map
        SupportMapFragment supportMapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps0);
        supportMapFragment.getMapAsync(Evacuation.this);

        //initialize fusedlocation
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        getClosest=findViewById(R.id.getClosestLocationButton);
        DeltaLocation=new Location("");
        distance=new ArrayList<>();
        dP=new ArrayList<>();

        getClosest.setOnClickListener(view -> {
            getclosestLocation();
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Home.class);
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

        //get marker from firebase and set to map
        fStore.collection("Evacuation").get().addOnCompleteListener(task -> {
            if(!task.isSuccessful()){
                Toast.makeText(this, "No locations!!", Toast.LENGTH_LONG).show();
            }
            else if(task.isSuccessful()){
                List<DocumentSnapshot> list=task.getResult().getDocuments();
                for (DocumentSnapshot documentSnapshot: list){
                    EvacuationHolder evacParams=documentSnapshot.toObject(EvacuationHolder.class);
                    Double latC,longC;
                    latC= Double.valueOf(String.valueOf(evacParams.getEvacuationLatitude()));
                    longC= Double.valueOf(String.valueOf(evacParams.getEvacuationLongitude()));

                    LatLng latLng=new LatLng(latC,longC);
                    map.addMarker(new MarkerOptions().position(latLng).title(evacParams.getEvacuationName()));

                    DeltaLocation.setLatitude(latLng.latitude);
                    DeltaLocation.setLongitude(latLng.longitude);

                    float data=lastKnownLocation.distanceTo(DeltaLocation);
                    distance.add(data);
                    // Log.i(TAG, "onMapReady: "+data+" ");
                    //get location from coord

                    dP.add(new distanceParameter(data,evacParams.getEvacuationName()+" "+evacParams.getEvacuationAddress(),latC,longC));
                }
                min= Collections.min(distance);
            }
            else{
                Toast.makeText(this, task.getException().toString(), Toast.LENGTH_SHORT).show();
            }
        });


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

    private void getclosestLocation() {
        //get closest location using distance(m)

        // check if dp is null then
        // cycle arraylist for min then display location
        if(dP.size()<1){
            Toast.makeText(this, "No data from database", Toast.LENGTH_SHORT).show();
        }
        else{
            for (int i=0;i<dP.size();i++){
                //   Log.i(TAG, "Data from dp:"+ dP.get(i).getLocation());
                if(min== dP.get(i).getDistance()){
                    dP.get(i).getLocation();
                    Toast.makeText(Evacuation.this, /*min+*/ " "+dP.get(i).getLocation(), Toast.LENGTH_LONG).show();
                    // Log.i(TAG, "Closest to me: "+ dP.get(i).getLocation());
                    //point marker to coordinates

                    LatLng coord=new LatLng(dP.get(i).getLatitude(),dP.get(i).getLongitude());
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(coord,15));
                    Log.i("coord",coord.toString());
                }

            }
        }


    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            locationPermissionGranted=true;
        }
        else{
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

        }
    }
}
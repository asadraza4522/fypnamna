package com.example.fypapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.fypapplication.Post.model.LocationInfo;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    Double locationLat, locationLong;
    private GoogleMap mMap;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dbReference = database.getReference("test");
    private Button find_location_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        find_location_btn = (Button) findViewById(R.id.btn_find_location);
//        if (mapFragment != null) {
//            mapFragment.getMapAsync(this);
//        }

        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    //get the exact longitude and latitude from the database "test"
                    LocationInfo location = snapshot.child("test").getValue(LocationInfo.class);
                    locationLat = location.getLatitude();
                    locationLong = location.getLongitude();
                    showLocation();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            };
        });
        //trigger reading of location from database using the button
        find_location_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if the latitude and longitude is not null
                if (locationLat != null && locationLong != null) {
                    // create a LatLng object from location
                    LatLng latLng = new LatLng(25.3548, 51.1839);
                    //create a marker at the read location and display it on the map
                    mMap.addMarker(new MarkerOptions().position(latLng).title("The GUARD is currently here"));
                    //specify how the map camera is updated
                    CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 16.0f);
                    //update the camera with the CameraUpdate object
                    mMap.moveCamera(update);
                } else {
                    // if location is null , log an error message
                    Log.e("Maps Activity", "user location cannot be found");
                }
            }
        });
    }
    public void showLocation(){
        if (locationLat != null && locationLong != null) {
            // create a LatLng object from location
            LatLng latLng = new LatLng(locationLat, locationLong);
            //create a marker at the read location and display it on the map
            mMap.addMarker(new MarkerOptions().position(latLng).title("The GUARD is currently here"));
            //specify how the map camera is updated
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 16.0f);
            //update the camera with the CameraUpdate object
            mMap.moveCamera(update);
        } else {
            // if location is null , log an error message
            Log.e("Maps Activity", "user location cannot be found");
        }
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(@NotNull GoogleMap googleMap) {
        Log.d("MapActivity", "onMapReady: " + googleMap);
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
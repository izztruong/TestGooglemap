package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab1.Search.SearchActivity;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap googleMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    public static final String APIKEY="4wAzwPzeRZEcKqAbo32x39XcqauuM0uHyi3D-QTeNvc";
    public static Double LAT_NOW=0.0;
    public static Double LNG_NOW=0.0;
    EditText edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        edt=findViewById(R.id.edtplace);
        edt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Intent intent=new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        Intent intent=getIntent();
        double lat= intent.getDoubleExtra("lat",0);
        if (lat!=0){
             //lat= intent.getDoubleExtra("lat",0);
            double lng=intent.getDoubleExtra("lng",0);
            String q=intent.getStringExtra("q");
            LatLng lo = new LatLng(lat, lng);
            CircleOptions circleOptions = new CircleOptions().center(lo).radius(10).fillColor(Color.BLUE).strokeColor(Color.BLACK).strokeWidth(2);
            googleMap.addMarker(new MarkerOptions().position(lo));
            googleMap.addCircle(circleOptions);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lo, 12));
            edt.setText(q);
        }else {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Quyền đã được cấp

            FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                System.out.println(" thành công location");
                                LAT_NOW=location.getLatitude();
                                LNG_NOW=location.getLongitude();
                                LatLng lo = new LatLng(location.getLatitude(), location.getLongitude());
                                CircleOptions circleOptions = new CircleOptions().center(lo).radius(10).fillColor(Color.BLUE).strokeColor(Color.BLACK).strokeWidth(2);
                                googleMap.addMarker(new MarkerOptions().position(lo));
                                googleMap.addCircle(circleOptions);
                                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lo, 12));
                            } else {
                                Log.e("Location", "Không thể lấy được vị trí hiện tại. Lý do: Location is null.");
                            }
                        }
                    }
            );
        } else {
            // Yêu cầu quyền
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
        }
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
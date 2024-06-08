package com.example.lab1.Direction;

import static com.example.lab1.MainActivity.APIKEY;
import static com.example.lab1.MainActivity.LAT_NOW;
import static com.example.lab1.MainActivity.LNG_NOW;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.lab1.Data.ApiHere;
import com.example.lab1.Data.Retrofitclass;
import com.example.lab1.MainActivity;
import com.example.lab1.Model.GeocodeResponse;
import com.example.lab1.Model.RedirectionResponse;
import com.example.lab1.R;
import com.example.lab1.Search.SearchActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DirectionActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap Map;
    String origin = String.valueOf(LAT_NOW) + "," + String.valueOf(LNG_NOW);
    String destination;
    String transportMode = "car";
    String shave = "polyline";
    String line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_direction);
        mapFragment.getMapAsync(this);


    }

    void getCoordinates(String q, GoogleMap googleMap) {
        ApiHere apiHere = Retrofitclass.getService();
        Call<GeocodeResponse> call = apiHere.getGeocode(q, APIKEY);
        call.enqueue(new Callback<GeocodeResponse>() {
            @Override
            public void onResponse(Call<GeocodeResponse> call, Response<GeocodeResponse> response) {
                if (response.isSuccessful()) {
                    System.out.println("suscess");
                    GeocodeResponse geocodeResponse = response.body();
                    if (geocodeResponse != null) {
                        destination = String.valueOf(geocodeResponse.getItems().get(0).getPosition().getLat()) + "," +
                                String.valueOf(geocodeResponse.getItems().get(0).getPosition().getLng());
                        getDirection(googleMap);
                    } else {
                        System.out.println("Lỗi lấy tọa độ");
                    }
                }
            }

            @Override
            public void onFailure(Call<GeocodeResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    public void getDirection(GoogleMap googleMap) {
        ApiHere apiHere2 = Retrofitclass.getService1();
        System.out.println(origin + destination + transportMode + shave);
        Call<RedirectionResponse> call = apiHere2.getDirection(origin, destination, transportMode, shave, APIKEY);
        call.enqueue(new Callback<RedirectionResponse>() {
            @Override
            public void onResponse(Call<RedirectionResponse> call, Response<RedirectionResponse> response) {
                if (response.isSuccessful()) {
                    System.out.println("success get direction");
                    line= response.body().getRoutes().get(0).getSections().get(0).getPolyline();
                    //line = "BGshrkkD847xZ7BwCvCoB3DUjSAnGgZvvB7kB3S3NjIzFAw0GUstDUo4BAoVU0oB0F4DsEkDsd8VopB8fgZsTkIoGgKkIkNgKgZgUoagUwMsJgPkN0ewW0KkIoQkNwvB8kBnBgFAoGU0FoBoG8BgF8B4DwCkD4DoBkDAsEnBkDvC4D3D8BrE0KkIwH0FwMsJ8Q4NgF4D4DkD0KkIoBoBoQwMgjBkc4SsO0KkIsY4SwRkNwHoGgZ0U4DkD8Q4NgPgKkDwCsOgKoQkN0PwM8foagP0K0jBwbgF4DsJkI4I8G0FgF4DwCkNgKwC8B4IgF3D0PnBgF7GkXrEsTjIwgB3I4hBjSgmC7LwqB3N41BnLsnBnQk6BzF0UjDoL_JgjB3D8LnLsnB7G0UnGwRzU0yBnQsnB_TouBjIkS3NsiB7GoQjIkSnkBg1CvvB0uDrJoVvHwRnGsO7GoQvHkS_nBk9CjDwH3D4I7G0P_J4XjXs2BvH8QrO8fjD8GvCgF7GsOnQkhB_YsxB3hBghCrT8kB7LkXvCgFvHsOrJkSnL0U3D8G7GwMnQwgB3I8QvCgFjI0P_O8arEkI7B4D7B4DrJwR_J4S_E4IjSsiBvR8f3I0P7QsdvM8VzKsTvCsE_J0PkcgoB0FkD8GoBgF4DokB4wB";
                    List<LatLng> points = decodePolyline(line);
//                    PolylineOptions polylineOptions = new PolylineOptions()
//                            .addAll(points) // Thêm các điểm vào đường polyline
//                            .width(10) // Độ dày của đường polyline
//                            .color(Color.RED); // Màu sắc của đường polyline
                    // Thêm đường polyline vào Google Map
                    PolylineOptions polylineOptions = new PolylineOptions();
                    for (LatLng point : points) {
                        polylineOptions.add(point);
                    }
                    System.out.println(points.get(0).latitude+","+points.get(0).longitude);
                    googleMap.addPolyline(polylineOptions);
                    // Di chuyển và zoom vào đường polyline
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(points.get(0), 13));
                }
            }

            @Override
            public void onFailure(Call<RedirectionResponse> call, Throwable t) {
            }
        });
    }

    private List<LatLng> decodePolyline(String encodedPolyline) {
        List<LatLng> points = new ArrayList<>();
        int index = 0;
        int len = encodedPolyline.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int result = 1;
            int shift = 0;
            int b = 0;
            do {
                if (index < len) {
                    b = encodedPolyline.charAt(index++) - 63 - 1;
                    result += b << shift;
                    shift += 5;
                }
            } while (b >= 0x1f && index < len);
            lat += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            result = 1;
            shift = 0;
            do {
                if (index < len) {
                    b = encodedPolyline.charAt(index++) - 63 - 1;
                    result += b << shift;
                    shift += 5;
                }
            } while (b >= 0x1f && index < len);
            lng += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            points.add(new LatLng(lat / 100000d, lng / 100000d));
        }
        return points;

    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        Intent intent = getIntent();
        String q = intent.getStringExtra("nameAddress");
        getCoordinates(q, googleMap);

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
package com.example.lab1.Data;

import com.example.lab1.Model.GeocodeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiHere {
    @GET("/vi/geocode")
    Call<GeocodeResponse> getGeocode(
            @Query("q") String place,
            @Query("apiKey") String apiKey
    );
}

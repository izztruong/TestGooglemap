package com.example.lab1.Data;


import com.example.lab1.Model.AutoCompleteResponse;
import com.example.lab1.Model.GeocodeResponse;
import com.example.lab1.Model.RedirectionResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiHere {
    @GET("v1/geocode")
    Call<GeocodeResponse> getGeocode(
            @Query("q") String place,
            @Query("apiKey") String apiKey
    );
    @GET("v1/autocomplete")
    Call<AutoCompleteResponse> getAutoComplete(
            @Query("q") String place,
            @Query("apiKey") String apiKey
    );
    @GET("v8/routes")
    Call<RedirectionResponse> getDirection(
            @Query("origin") String origin,
            @Query("destination") String destination,
            @Query("transportMode") String transportMode,
            @Query("return") String return1,
            @Query("apiKey") String apiKey
    );


}

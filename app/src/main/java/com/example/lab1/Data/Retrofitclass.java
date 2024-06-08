package com.example.lab1.Data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofitclass {
     private static final String BaseUrl = "https://geocode.search.hereapi.com/";
     private static final String BaseUrl1 = "https://router.hereapi.com/";

     public static ApiHere getService() {
          Retrofit retrofit = new Retrofit.Builder()
                  .baseUrl(BaseUrl)
                  .addConverterFactory(GsonConverterFactory.create())
                  .build();
          return retrofit.create(ApiHere.class);
     }
     public static ApiHere getService1() {
          Retrofit retrofit = new Retrofit.Builder()
                  .baseUrl(BaseUrl1)
                  .addConverterFactory(GsonConverterFactory.create())
                  .build();
          return retrofit.create(ApiHere.class);
     }



}

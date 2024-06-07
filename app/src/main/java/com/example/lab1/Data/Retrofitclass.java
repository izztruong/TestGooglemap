package com.example.lab1.Data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofitclass {
     Retrofit retrofit=new Retrofit.Builder()
             .baseUrl("https://geocode.search.hereapi.com/")
             .addConverterFactory(GsonConverterFactory.create())
             .build();


}

package com.example.lab1.Search;

import static com.example.lab1.MainActivity.APIKEY;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.lab1.Adapter.SuggestPlaceAdapter;
import com.example.lab1.Data.ApiHere;
import com.example.lab1.Data.Retrofitclass;
import com.example.lab1.Interface.OnclickItem;
import com.example.lab1.MainActivity;
import com.example.lab1.Model.AutoCompleteResponse;
import com.example.lab1.Model.GeocodeResponse;
import com.example.lab1.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchActivity extends AppCompatActivity implements OnclickItem {
     EditText edtsearch;
     ImageView icon_back,icon_search;
     RecyclerView recyclerView;
    public SuggestPlaceAdapter adapter;
    public ArrayList<AutoCompleteResponse.AutoComplete> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView=findViewById(R.id.listplace);
        edtsearch=findViewById(R.id.edtsearchplace);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Handler handler = new Handler();
        Runnable inputFinished = new Runnable() {
            @Override
            public void run() {
                OntypeSearch();
            }
        };
        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacks(inputFinished);
                handler.postDelayed(inputFinished, 500);
            }
        });


        icon_back=findViewById(R.id.icon_back);
        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        icon_search=findViewById(R.id.icon_search);
        icon_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q= String.valueOf(edtsearch.getText());
                OnclickIconSearch(q);
            }
        });
    }
    void OntypeSearch(){
        System.out.println("call autocomplete");
        String q= String.valueOf(edtsearch.getText());
        ApiHere apiHere=Retrofitclass.getService();
        Call<AutoCompleteResponse> call=apiHere.getAutoComplete(q,APIKEY);
        call.enqueue(new Callback<AutoCompleteResponse>() {
            @Override
            public void onResponse(Call<AutoCompleteResponse> call, Response<AutoCompleteResponse> response) {
                if (response.isSuccessful()) {
                    System.out.println("autocomplete");
                    list.clear();
                    list.addAll(response.body().getItems());
                    setAdapter(list);
                }
            }

            @Override
            public void onFailure(Call<AutoCompleteResponse> call, Throwable t) {
               System.out.println(t);
            }
        });

    }
    private void setAdapter(ArrayList<AutoCompleteResponse.AutoComplete> list){
        adapter=new SuggestPlaceAdapter(list,SearchActivity.this,this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
       recyclerView.setLayoutManager(layoutManager);
       recyclerView.setAdapter(adapter);

    }
    void OnclickIconSearch(String q){
        System.out.println("click icon search");

        ApiHere apiHere= Retrofitclass.getService();
        Call<GeocodeResponse> call=apiHere.getGeocode(q,APIKEY );
        call.enqueue(new Callback<GeocodeResponse>() {
            @Override
            public void onResponse(Call<GeocodeResponse> call, Response<GeocodeResponse> response) {
                if(response.isSuccessful()){
                  System.out.println("suscess");
                    GeocodeResponse geocodeResponse=response.body();
                    System.out.println(String.valueOf(geocodeResponse.getItems().get(0).getPosition().getLat()));
                    if(geocodeResponse!=null) {

                        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                        intent.putExtra("lat", geocodeResponse.getItems().get(0).getPosition().getLat());
                        intent.putExtra("lng", geocodeResponse.getItems().get(0).getPosition().getLng());
                        intent.putExtra("q",q);
                        startActivity(intent);
                    }
                    else {
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

    @Override
    public void OnclickItemSP(String q) {
        OnclickIconSearch(q);
    }
}
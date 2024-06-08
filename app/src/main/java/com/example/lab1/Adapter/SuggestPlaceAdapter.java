package com.example.lab1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.lab1.Direction.DirectionActivity;
import com.example.lab1.Interface.OnclickItem;
import com.example.lab1.Model.AutoCompleteResponse;
import com.example.lab1.PlaceActivity;
import com.example.lab1.R;
import com.example.lab1.Search.SearchActivity;

import java.util.ArrayList;

public class SuggestPlaceAdapter extends RecyclerView.Adapter<SuggestPlaceAdapter.ViewHolder> {
    private ArrayList<AutoCompleteResponse.AutoComplete>list;
    private Context context;
    OnclickItem onclickItem;

    public SuggestPlaceAdapter(ArrayList<AutoCompleteResponse.AutoComplete> list, Context context, OnclickItem onclickItem) {
        this.list = list;
        this.context = context;
        this.onclickItem = onclickItem;
    }

    public SuggestPlaceAdapter(ArrayList<AutoCompleteResponse.AutoComplete> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.item_listplace,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtplace.setText(list.get(position).getTitle());
        holder.txtplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickItem.OnclickItemSP(list.get(position).getTitle());
            }
        });
        holder.icon_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity = (Activity) context;
                Intent intent=new Intent(context, DirectionActivity.class);
                intent.putExtra("nameAddress",list.get(position).getTitle());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtplace;
        ImageView icon_direction;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtplace=itemView.findViewById(R.id.txtsuggestplace);
            icon_direction=itemView.findViewById(R.id.icon_direction);

        }
    }
}

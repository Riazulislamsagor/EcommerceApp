package com.example.myecommerceapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ShowAllProductAdapter extends RecyclerView.Adapter<ShowAllProductAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ShowAllDataModel> list;

    public ShowAllProductAdapter(Context context, ArrayList<ShowAllDataModel> list) {
        this.context = context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.showall_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShowAllDataModel model = list.get(position);
        holder.name.setText(model.getName());
        holder.price.setText("$"+model.getPrice());

        // Load image using Glide or Picasso
        Glide.with(context)
                .load(model.getImgurl())
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("detailed",list.get(position));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.item_image);
            name=itemView.findViewById(R.id.item_nam);
            price=itemView.findViewById(R.id.item_cost);
        }
    }
}

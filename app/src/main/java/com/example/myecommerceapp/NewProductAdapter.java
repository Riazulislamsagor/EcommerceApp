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

import java.util.List;

public class NewProductAdapter extends RecyclerView.Adapter<NewProductAdapter.ViewHolder> {
    private Context context;
    private List<NewProductModel> list;

    public NewProductAdapter(Context context, List<NewProductModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.new_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewProductModel model = list.get(position);
        holder.newname.setText(model.getName());
        holder.newprice.setText(String.valueOf(model.getPrice()));

        // Load image using Glide or Picasso
        Glide.with(context)
                .load(model.getImgurl())
                .into(holder.newimage);

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
        ImageView newimage;
        TextView newname,newprice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newimage=itemView.findViewById(R.id.new_img);
            newname=itemView.findViewById(R.id.newproduct);
            newprice=itemView.findViewById(R.id.new_price);
        }
    }
}

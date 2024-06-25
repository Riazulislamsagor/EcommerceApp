package com.example.myecommerceapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {
    private Context context;
    private List<MycartModel> list;
    int totalamount = 0;

    public MyCartAdapter(Context context, List<MycartModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MycartModel model = list.get(position);
        holder.date.setText(model.getCurrentDate());
        holder.time.setText(model.getCurrentTime());
        holder.name.setText(model.getProductName());
        holder.price.setText(model.getProductPrice()+"$");
        holder.totalprice.setText(String.valueOf(model.getTotalprice()));
        holder.totalquantity.setText(model.getTotalquantity());

        totalamount=totalamount+list.get(position).getTotalprice();
        Intent intent=new Intent("MyTotalAmount");
        intent.putExtra("totalAmount",totalamount);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,price,date,time,totalquantity,totalprice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.product_name);
            price=itemView.findViewById(R.id.product_price);
            date=itemView.findViewById(R.id.current_date);
            time=itemView.findViewById(R.id.current_time);
            totalquantity=itemView.findViewById(R.id.total_quantity);
            totalprice=itemView.findViewById(R.id.total_price);

        }
    }
}

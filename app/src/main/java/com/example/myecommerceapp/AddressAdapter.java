package com.example.myecommerceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    Context context;
    List<AddressModel> addressModelList;
    SelectAddress selectAddress;
    private RadioButton selectedRadiobtn;

    public AddressAdapter(Context context, List<AddressModel> addressModelList, SelectAddress selectAddress) {
        this.context = context;
        this.addressModelList = addressModelList;
        this.selectAddress = selectAddress;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.address.setText(addressModelList.get(position).getUserAdrees());

        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (AddressModel addressModel:addressModelList){
                    addressModel.setIsselected(false);
                }
                addressModelList.get(position).setIsselected(true);

                if (selectedRadiobtn!=null){
                    selectedRadiobtn.setChecked(false);
                }
                selectedRadiobtn=(RadioButton) v;
                selectedRadiobtn.setChecked(true);
                selectAddress.setAddress(addressModelList.get(position).getUserAdrees());
            }
        });

    }

    @Override
    public int getItemCount() {
        return addressModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView address;
        RadioButton radioButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            address=itemView.findViewById(R.id.address_add);
            radioButton=itemView.findViewById(R.id.select_address);
        }
    }

    public interface SelectAddress{
        void setAddress(String address);
    }
}

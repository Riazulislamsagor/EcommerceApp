package com.example.myecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity implements AddressAdapter.SelectAddress {
    Button addadressbtn, paymentbtn;
    RecyclerView recyclerView;
    AddressAdapter addressAdapter;
    List<AddressModel> addressModelList;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    Toolbar toolbar;
    String maddress="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        addadressbtn = findViewById(R.id.add_address_btn);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        toolbar = findViewById(R.id.address_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Object obj=getIntent().getSerializableExtra("item");

        recyclerView = findViewById(R.id.address_recycler);
        paymentbtn = findViewById(R.id.payment_btn);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addressModelList = new ArrayList<>();
        addressAdapter = new AddressAdapter(getApplicationContext(), addressModelList, this);
        recyclerView.setAdapter(addressAdapter);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                AddressModel model = document.toObject(AddressModel.class);
                                addressModelList.add(model);
                            }
                            addressAdapter.notifyDataSetChanged();
                        }
                    }
                });



        paymentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double amount=0.0;
                if (obj instanceof NewProductModel){
                    NewProductModel newProductModel=(NewProductModel) obj;
                    amount=newProductModel.getPrice();
                }
                if (obj instanceof PopularProductModel){
                    PopularProductModel popularProductModel=(PopularProductModel) obj;
                    amount=popularProductModel.getPrice();
                }
                if (obj instanceof ShowAllDataModel){
                    ShowAllDataModel showAllDataModel=(ShowAllDataModel) obj;
                    amount=showAllDataModel.getPrice();
                }
                Intent intent=new Intent(AddressActivity.this, PaymentActivity.class);
                intent.putExtra("amount",amount);
                startActivity(intent);



            }
        });


        addadressbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddressActivity.this, AddAddressActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void setAddress(String address) {

        maddress=address;
    }
}
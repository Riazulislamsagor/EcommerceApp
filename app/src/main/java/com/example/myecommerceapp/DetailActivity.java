package com.example.myecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;

public class DetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView detailedimg;
    TextView rating,name,description,price,quantity;
    Button addtocart,buynow;
    ImageView additem,removeItem;
    private FirebaseFirestore firestore;
    FirebaseAuth auth;
    NewProductModel newProductModel=null;
    ShowAllDataModel showAllDataModel=null;
    PopularProductModel popularProductModel=null;
    int totalquantity=1;
    int totalprice=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();



        final Object obj=getIntent().getSerializableExtra("detailed");
        final Object object=getIntent().getSerializableExtra("detailed");
        final Object show=getIntent().getSerializableExtra("detailed");

        if (obj instanceof NewProductModel){
            newProductModel=(NewProductModel) obj;
        }
 if (obj instanceof PopularProductModel){
            popularProductModel=(PopularProductModel) obj;

        } if (obj instanceof ShowAllDataModel){
            showAllDataModel=(ShowAllDataModel) obj;
        }

        detailedimg=findViewById(R.id.detail_img);
        rating=findViewById(R.id.myrating);
        name=findViewById(R.id.detailname);
        description=findViewById(R.id.detaildescription);
        price=findViewById(R.id.detailedprice);
        quantity=findViewById(R.id.quantity);
        toolbar=findViewById(R.id.detailtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addtocart=findViewById(R.id.addtoCart);
        buynow=findViewById(R.id.buynow);

        additem=findViewById(R.id.addItem);
        removeItem=findViewById(R.id.removeitem);

        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DetailActivity.this, AddressActivity.class);
                if (newProductModel!=null){
                    intent.putExtra("item",newProductModel);
                }
                if (popularProductModel!=null){
                    intent.putExtra("item",popularProductModel);
                }
                if (showAllDataModel!=null){
                    intent.putExtra("item",showAllDataModel);
                }

                startActivity(intent);

            }
        });



        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalquantity<10){
                    totalquantity++;
                    quantity.setText(String.valueOf(totalquantity));
                    if (newProductModel!=null){
                        totalprice = newProductModel.getPrice() * totalquantity;

                    }
                    if (popularProductModel!=null){
                        totalprice = popularProductModel.getPrice() * totalquantity;

                    }
                    if (showAllDataModel!=null){
                        totalprice = showAllDataModel.getPrice() * totalquantity;

                    }
                }

            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalquantity>1){
                    totalquantity--;
                    quantity.setText(String.valueOf(totalquantity));
                }

            }
        });

        if (newProductModel!=null){
            Glide.with(getApplicationContext()).load(newProductModel.getImgurl()).into(detailedimg);
            name.setText(newProductModel.getName());
            rating.setText(newProductModel.getRating());
            description.setText(newProductModel.getDescription());
            price.setText(String.valueOf(newProductModel.getPrice()));
           // name.setText(newProductModel.getName());
            totalprice = newProductModel.getPrice() * totalquantity;
        }
       if (popularProductModel!=null){
            Glide.with(getApplicationContext()).load(popularProductModel.getImgurl()).into(detailedimg);
            name.setText(popularProductModel.getName());
            rating.setText(popularProductModel.getRating());
            description.setText(popularProductModel.getDescription());
            price.setText(String.valueOf(popularProductModel.getPrice()));
           // name.setText(newProductModel.getName());
           totalprice = popularProductModel.getPrice() * totalquantity;
        }
 if (showAllDataModel!=null){
            Glide.with(getApplicationContext()).load(showAllDataModel.getImgurl()).into(detailedimg);
            name.setText(showAllDataModel.getName());
            rating.setText(showAllDataModel.getRating());
            description.setText(showAllDataModel.getDescription());
            price.setText(String.valueOf(showAllDataModel.getPrice()));
           // name.setText(newProductModel.getName());
     totalprice = showAllDataModel.getPrice() * totalquantity;
        }

 addtocart.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         addtoCart();
     }
 });

    }
    private void addtoCart(){
        String saveCurrentTime,saveCurrentdate;

        Calendar callfordate=Calendar.getInstance();

        SimpleDateFormat currentdate=new SimpleDateFormat("MM.dd.yyyy");
        saveCurrentdate=currentdate.format(callfordate.getTime());

        SimpleDateFormat currentTime=new SimpleDateFormat("hh:mm:ss");
        saveCurrentTime=currentTime.format(callfordate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();
        cartMap.put("productName",name.getText().toString());
        cartMap.put("productPrice",price.getText().toString());
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("currentDate",saveCurrentdate);
        cartMap.put("totalquantity",quantity.getText().toString());
        cartMap.put("totalprice",totalprice);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User")
                .add(cartMap)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailActivity.this, "Added To Cart", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
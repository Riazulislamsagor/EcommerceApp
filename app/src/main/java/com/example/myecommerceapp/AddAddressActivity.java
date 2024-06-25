package com.example.myecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAddressActivity extends AppCompatActivity {
    EditText name,address,city,postalcode,phonenumber;
    Toolbar toolbar;

    Button addAddressbtn;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

        name=findViewById(R.id.ad_name);
        address=findViewById(R.id.ad_address);
        city=findViewById(R.id.ad_city);
        postalcode=findViewById(R.id.ad_code);
        phonenumber=findViewById(R.id.ad_phone);
        toolbar=findViewById(R.id.add_address_toolbar);
        addAddressbtn=findViewById(R.id.ad_add_address);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addAddressbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username=name.getText().toString();
                String useraddress=address.getText().toString();
                String usercity=city.getText().toString();
                String userpostalcode=postalcode.getText().toString();
                String userphone=phonenumber.getText().toString();

                String final_address="";

                if (!username.isEmpty()){
                    final_address+=username;
                }if (!useraddress.isEmpty()){
                    final_address+=useraddress;
                }
                if (!usercity.isEmpty()){
                    final_address+=usercity;
                }
                if (!userpostalcode.isEmpty()){
                    final_address+=userpostalcode;
                }
                if (!userphone.isEmpty()){
                    final_address+=userphone;
                }

                if ( !username.isEmpty() &&  !useraddress.isEmpty() && !usercity.isEmpty()&&!userpostalcode.isEmpty()&&!userphone.isEmpty() ){
                    Map<String,String> map=new HashMap<>();
                    map.put("userAdrees",final_address);

                    firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                            .collection("Address")
                            .add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(AddAddressActivity.this, "Address Added", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(AddAddressActivity.this, DetailActivity.class));
                                        finish();
                                    }

                                }
                            });

                }else {
                    Toast.makeText(AddAddressActivity.this, "Kindly Fill All Field", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
}
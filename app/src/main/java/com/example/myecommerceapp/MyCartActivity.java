package com.example.myecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MyCartActivity extends AppCompatActivity {
    int overAllTotalAmount;
    TextView overAllAmount;

    Toolbar toolbar;

    RecyclerView recyclerView;
    MyCartAdapter myCartAdapter;
    List<MycartModel> mycartModelList;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        recyclerView=findViewById(R.id.mycartrecycleView);
        toolbar=findViewById(R.id.mycarttoolbar);
        overAllAmount=findViewById(R.id.textView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LocalBroadcastManager.getInstance(this)
                        .registerReceiver(mmessagereceiver,new IntentFilter("MyTotalAmount"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mycartModelList=new ArrayList<>();
        myCartAdapter=new MyCartAdapter(this,mycartModelList);
        recyclerView.setAdapter(myCartAdapter);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            MycartModel model = document.toObject(MycartModel.class);
                            mycartModelList.add(model);
                        }
                        // Notify the adapter of the data change
                        myCartAdapter.notifyDataSetChanged();

                    }
                });

    }
    public BroadcastReceiver mmessagereceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalbill=intent.getIntExtra("totalAmount",0);
            overAllAmount.setText("Total Amount:"+totalbill+"$");

        }
    };
}
package com.example.myecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ShowAllDataActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ShowAllProductAdapter adapter;
    ArrayList<ShowAllDataModel>showAllModelArrayList;
    FirebaseFirestore firestore;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_data);
        firestore=FirebaseFirestore.getInstance();
        showAllModelArrayList=new ArrayList<>();
        recyclerView=findViewById(R.id.show_rec);
        toolbar=findViewById(R.id.showalltoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String type=getIntent().getStringExtra("type");

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        adapter=new ShowAllProductAdapter(this,showAllModelArrayList);
        recyclerView.setAdapter(adapter);


        if (type==null || type.isEmpty()){
            firestore.collection("ShowAll")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ShowAllDataModel showAllModel = document.toObject(ShowAllDataModel.class);
                                    showAllModelArrayList.add(showAllModel);
                                    adapter.notifyDataSetChanged();

                                }
                                // Notify the adapter of the data change
                            } else {
                                // Handle the error
                            }
                        }
                    });

        }
        if (type!=null && type.equalsIgnoreCase("men")){
            firestore.collection("ShowAll").whereEqualTo("type","men")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ShowAllDataModel showAllModel = document.toObject(ShowAllDataModel.class);
                                    showAllModelArrayList.add(showAllModel);
                                    adapter.notifyDataSetChanged();

                                }
                                // Notify the adapter of the data change
                            } else {
                                // Handle the error
                            }
                        }
                    });

        }
        if (type!=null && type.equalsIgnoreCase("women")){
            firestore.collection("ShowAll").whereEqualTo("type","women")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ShowAllDataModel showAllModel = document.toObject(ShowAllDataModel.class);
                                    showAllModelArrayList.add(showAllModel);
                                    adapter.notifyDataSetChanged();

                                }
                                // Notify the adapter of the data change
                            } else {
                                // Handle the error
                            }
                        }
                    });

        }
        if (type!=null && type.equalsIgnoreCase("kids")){
            firestore.collection("ShowAll").whereEqualTo("type","kids")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ShowAllDataModel showAllModel = document.toObject(ShowAllDataModel.class);
                                    showAllModelArrayList.add(showAllModel);
                                    adapter.notifyDataSetChanged();

                                }
                                // Notify the adapter of the data change
                            } else {
                                // Handle the error
                            }
                        }
                    });

        }
        if (type!=null && type.equalsIgnoreCase("shoes")){
            firestore.collection("ShowAll").whereEqualTo("type","shoes")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ShowAllDataModel showAllModel = document.toObject(ShowAllDataModel.class);
                                    showAllModelArrayList.add(showAllModel);
                                    adapter.notifyDataSetChanged();

                                }
                                // Notify the adapter of the data change
                            } else {
                                // Handle the error
                            }
                        }
                    });

        }
        if (type!=null && type.equalsIgnoreCase("camera")){
            firestore.collection("ShowAll").whereEqualTo("type","camera")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ShowAllDataModel showAllModel = document.toObject(ShowAllDataModel.class);
                                    showAllModelArrayList.add(showAllModel);
                                    adapter.notifyDataSetChanged();

                                }
                                // Notify the adapter of the data change
                            } else {
                                // Handle the error
                            }
                        }
                    });

        }
        if (type!=null && type.equalsIgnoreCase("watch")){
            firestore.collection("ShowAll").whereEqualTo("type","watch")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ShowAllDataModel showAllModel = document.toObject(ShowAllDataModel.class);
                                    showAllModelArrayList.add(showAllModel);
                                    adapter.notifyDataSetChanged();

                                }
                                // Notify the adapter of the data change
                            } else {
                                // Handle the error
                            }
                        }
                    });

        }



    }
}
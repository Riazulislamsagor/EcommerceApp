package com.example.myecommerceapp;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView catshowall,newshowall,populershowall;

    private ImageSlider imageSlider;
    CategoryAdapter categoryAdapter;
    NewProductAdapter newProductAdapter;
    PopularProductAdapter popularProductAdapter;
    LinearLayout linearLayout;

    ProgressDialog progressDialog;

    RecyclerView recyclerView,newproductrecycleview,popularrecycleview;
    ArrayList<CategoryModel>list;
    ArrayList<NewProductModel>newProductModelArrayList;
    ArrayList<PopularProductModel>productModelArrayList;
    FirebaseFirestore db;
    Toolbar toolbar;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        imageSlider =findViewById(R.id.image_slider);
        recyclerView=findViewById(R.id.rec_category);
        newproductrecycleview=findViewById(R.id.new_product_rec);
        popularrecycleview=findViewById(R.id.popular_rec);
        toolbar=findViewById(R.id.hometolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_menu_24);

        catshowall=findViewById(R.id.category_see_all);
        newshowall=findViewById(R.id.newProducts_see_all);
        populershowall=findViewById(R.id.popular_see_all);

        catshowall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ShowAllDataActivity.class);
                startActivity(intent);
            }
        });
          newshowall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(MainActivity.this, ShowAllDataActivity.class);
                   startActivity(intent);
            }
        });
          populershowall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(MainActivity.this, ShowAllDataActivity.class);
               startActivity(intent);
            }
        });

        linearLayout=findViewById(R.id.home_layout);
        linearLayout.setVisibility(View.GONE);
        db=FirebaseFirestore.getInstance();
        progressDialog=new ProgressDialog(this);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        list=new ArrayList<>();
      // LinearLayoutManager layoutManager=new LinearLayoutManager(this);
       recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

       // recyclerView.setLayoutManager(layoutManager);
        categoryAdapter=new CategoryAdapter(this,list);
        recyclerView.setAdapter(categoryAdapter);
        slideModels.add(new SlideModel(R.drawable.shoes1, "Discount on shoes Item", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.shoes2, "Discount on shoes Item", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.shoes3, "Discount on shoes Item", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.shoes4, "Discount on shoes Item", ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(slideModels);
        progressDialog.setTitle("Well come to my Ecommerce App");
        progressDialog.setMessage("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategoryModel model = document.toObject(CategoryModel.class);
                                list.add(model);
                            }
                            // Notify the adapter of the data change
                            categoryAdapter.notifyDataSetChanged();
                            linearLayout.setVisibility(View.VISIBLE);
                            progressDialog.dismiss();
                        } else {
                            // Handle the error
                        }
                    }
                });

        newProductModelArrayList=new ArrayList<>();

        newproductrecycleview.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
       newProductAdapter=new NewProductAdapter(this,newProductModelArrayList);
        newproductrecycleview.setAdapter(newProductAdapter);



        db.collection("NewProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NewProductModel model = document.toObject(NewProductModel.class);
                                newProductModelArrayList.add(model);
                            }
                            // Notify the adapter of the data change
                            newProductAdapter.notifyDataSetChanged();
                        } else {
                            // Handle the error
                        }
                    }
                });

        //popular
        productModelArrayList=new ArrayList<>();

        popularrecycleview.setLayoutManager(new GridLayoutManager(this,3));
        popularProductAdapter=new PopularProductAdapter(this,productModelArrayList);
        popularrecycleview.setAdapter(popularProductAdapter);


        db.collection("AllProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularProductModel model = document.toObject(PopularProductModel.class);
                                productModelArrayList.add(model);
                            }
                            // Notify the adapter of the data change
                            popularProductAdapter.notifyDataSetChanged();
                        } else {
                            // Handle the error
                        }
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.menulogout){
            auth.signOut();
            Intent intent=new Intent(MainActivity.this, RegistrtionActivity.class);
            startActivity(intent);


        }
        if (id==R.id.menumycart){
            Intent intent=new Intent(MainActivity.this, MyCartActivity.class);
            startActivity(intent);

        }
        return true;
    }
}
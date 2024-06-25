package com.example.myecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.myecommerceapp.databinding.ActivityRegistrtionBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrtionActivity extends AppCompatActivity {

    ActivityRegistrtionBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegistrtionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth= FirebaseAuth.getInstance();

        binding.createaccountId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=binding.username.getText().toString();
                String useremail=binding.usermail.getText().toString();
                String userpassword=binding.userpassword.getText().toString();

                if (TextUtils.isEmpty(username)){
                    binding.username.setText("enter user name");
                }

                if (TextUtils.isEmpty(useremail)){
                    binding.username.setText("enter user email");
                }

                if (TextUtils.isEmpty(userpassword)){
                    binding.username.setText("enter user password");
                }

                if (userpassword.length()<6){
                    binding.username.setText("password invalid");
                }
                auth.createUserWithEmailAndPassword(useremail,userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(RegistrtionActivity.this, "Regestration is success", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RegistrtionActivity.this, "Regestration is faield", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        });
        binding.loginId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegistrtionActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });




    }



    }

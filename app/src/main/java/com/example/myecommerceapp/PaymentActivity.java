package com.example.myecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity{

    Toolbar toolbar;
    TextView subtotal,discount,shiping,total;
    Button paymentbtn;
    double amount=0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        toolbar=findViewById(R.id.payment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        paymentbtn=findViewById(R.id.pay_btn);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





        amount=getIntent().getDoubleExtra("amount",0.0);


        discount=findViewById(R.id.textView17);
        subtotal=findViewById(R.id.sub_total);
        shiping=findViewById(R.id.textView18);
        total=findViewById(R.id.total_amt);

        subtotal.setText(amount+"$");

        paymentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                paymentMethod();

            }
        });
    }


        private void paymentMethod () {


            final Activity activity = PaymentActivity.this;

            try {
                JSONObject options = new JSONObject();
                //Set Company Name
                options.put("name", "My E-Commerce App");
                //Ref no
                options.put("description", "Reference No. #123456");
                //Image to be display
                options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
                //options.put("order_id", "order_9A33XWu170gUtm");
                // Currency type
                options.put("currency", "USD");
                //double total = Double.parseDouble(mAmountText.getText().toString());
                //multiply with 100 to get exact amount in rupee
                amount = amount * 100;
                //amount
                options.put("amount", amount);
                JSONObject preFill = new JSONObject();
                //email
                preFill.put("email", "developer.kharag@gmail.com");
                //contact
                preFill.put("contact", "7489347378");

                options.put("prefill", preFill);


            } catch (Exception e) {
                Log.e("TAG", "Error in starting Razorpay Checkout", e);
            }
        }



    }


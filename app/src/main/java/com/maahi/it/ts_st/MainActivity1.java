package com.maahi.it.ts_st;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

public class MainActivity1 extends AppCompatActivity {
    //implements PaymentResultListener
    private Button button;
    private EditText button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        button = findViewById(R.id.button);
        button1 = findViewById(R.id.button1);

//        Checkout.preload(getApplicationContext());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startPayment();
                startActivity(new Intent(MainActivity1.this, WebViewActivity.class).putExtra("amount", button1.getText().toString()));
//

//                String str_mobile = getIntent().getStringExtra("MOBILE_NO");
//                lbl_mobileNo.setText("Sent To: "+str_mobile);
            }
        });
    }

//    private void startPayment(){
//        Checkout checkout = new Checkout();
//        checkout.setKeyID("rzp_test_eNwgA5oEnElHde");
//        checkout.setImage(R.drawable.ic_launcher_foreground);
//        final Activity activity = this;
//        try {
//            JSONObject options = new JSONObject();
//            options.put("name", "CodingSTUFF");
//            options.put("description", "Reference No. #123456");
//            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
////            options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
//            options.put("theme.color", "#3399cc");
//            options.put("currency", "INR");
//            options.put("amount", "5000");//pass amount in currency subunits
//            options.put("prefill.email", "gaurav.kumar@example.com");
//            options.put("prefill.contact","9988776655");
//            JSONObject retryObj = new JSONObject();
//            retryObj.put("enabled", true);
//            retryObj.put("max_count", 4);
//            options.put("retry", retryObj);
//
//            checkout.open(activity, options);
//
//        } catch(Exception e) {
//            Log.e("TAG", "Error in starting Razorpay Checkout", e);
//        }
//    }
//
//    @Override
//    public void onPaymentSuccess(String s) {
//        Log.d("ONSUCCESS", "onPaymentSuccess: " + s);
//    }
//
//    @Override
//    public void onPaymentError(int i, String s) {
//        Log.d("ONERROR", "onPaymentError: "+s);
//    }
}
package com.example.foodzone.Activitys;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodzone.Adapters.CartAdapter;
import com.example.foodzone.Helper.ChangeNumberItemsListener;
import com.example.foodzone.Helper.ManagmentCart;
import com.example.foodzone.databinding.ActivityCartactivityBinding;
import com.shreyaspatil.EasyUpiPayment.EasyUpiPayment;
import com.shreyaspatil.EasyUpiPayment.listener.PaymentStatusListener;
import com.shreyaspatil.EasyUpiPayment.model.TransactionDetails;

import java.util.Arrays;
import java.util.List;


public class cartActivity extends databaseActivity  implements PaymentStatusListener{


    ActivityCartactivityBinding binding;
    final String upi ="jaydeepmangaliya@oksbi";
    String name  = "jaydeep";
    String transactionId="12314554";
    String desc ="thanyou";
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCartactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        managmentCart = new ManagmentCart(this);
        setVariable();
        calculateCart();
        initcartList();
        Toast.makeText(this, ""+areUpiAppsAvailable(), Toast.LENGTH_SHORT).show();

        binding.cheakoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(managmentCart.getTotalFee()<=0){
                    Toast.makeText(cartActivity.this, "payment is not valid", Toast.LENGTH_SHORT).show();
                }
                else {
                  paymentgetWaystart();
                }

            }
        });
    }


    private void initcartList() {
        if(managmentCart.getListCart().isEmpty()){
            binding.emptytxt.setVisibility(View.VISIBLE);
            binding.scrollviewcart.setVisibility(View.GONE);
        }
        else {
            binding.emptytxt.setVisibility(View.GONE);
            binding.scrollviewcart.setVisibility(View.VISIBLE);
        }
        binding.cartView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        binding.cartView.setAdapter(new CartAdapter(managmentCart.getListCart(), managmentCart, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                calculateCart();
            }
        }));
    }

    private void calculateCart() {
      double percentTxt =0.02;
      double delivery=0;
      double tax = Math.round(managmentCart.getTotalFee()*percentTxt*100.0)/100;
      double total = Math.round(managmentCart.getTotalFee()+tax+delivery*100)/100;
      double itemTotal = Math.round(managmentCart.getTotalFee()*100)/100;

      binding.totalfeestxt.setText("RS."+itemTotal);
      binding.TOTALTAX.setText("RS."+tax);
      binding.deliverytxt.setText("RS."+delivery);
      binding.totalltxt.setText("RS."+total);

    }

    private void setVariable() {
        binding.Cbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(cartActivity.this,MainActivity.class));
                finish();
            }
        });
    }
    public void paymentgetWaystart() {

            final EasyUpiPayment easyUpiPayment = new EasyUpiPayment.Builder()
                    .with(cartActivity.this)
                    // on below line we are adding upi id.
                    .setPayeeVpa(upi)
                    // on below line we are setting name to which we are making payment.
                    .setPayeeName(name)
                    // on below line we are passing transaction id.
                    .setTransactionId(transactionId)
                    // on below line we are passing transaction ref id.
                    .setTransactionRefId(transactionId)
                    // on below line we are adding description to payment.
                    .setDescription(desc)
                    // on below line we are passing amount which is being paid.
                    .setAmount(String.valueOf(managmentCart.getTotalFee()))
                    // on below line we are calling a build method to build this ui.
                    .build();
            // on below line we are calling a start
            // payment method to start a payment.

            // on below line we are calling a set payment
            // status listener method to call other payment methods.


          easyUpiPayment.setPaymentStatusListener((PaymentStatusListener) cartActivity.this);
        easyUpiPayment.startPayment();


    }
    private boolean areUpiAppsAvailable() {
        PackageManager packageManager = getPackageManager();
        List<String> upiAppPackages = Arrays.asList(
                "com.google.android.apps.nbu.paisa.user", // Google Pay
                "net.one97.paytm",                       // Paytm
                "com.phonepe.app",                       // PhonePe
                "in.org.npci.upiapp"                     // BHIM
        );
        for (String packageName : upiAppPackages) {
            try {
                packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
                return true; // UPI app is installed
            } catch (PackageManager.NameNotFoundException e) {
                // App not installed

            }
        }

        return false; // No UPI apps found
    }



    @Override
    public void onTransactionCompleted(TransactionDetails transactionDetails) {
        Toast.makeText(this, "Transaction completed: " + transactionDetails, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionSuccess() {
        Toast.makeText(this, "payment done ", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onTransactionSubmitted() {
        Toast.makeText(this, "Transaction submitted.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionFailed() {
        Toast.makeText(this, "Transaction failed: ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionCancelled() {
        Toast.makeText(this, "Transaction cancelled.", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAppNotFound() {
        Toast.makeText(this, "app not install", Toast.LENGTH_SHORT).show();
    }


}
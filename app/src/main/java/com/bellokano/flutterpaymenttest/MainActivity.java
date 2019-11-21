package com.bellokano.flutterpaymenttest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.flutterwave.raveandroid.RaveConstants;
import com.flutterwave.raveandroid.RavePayActivity;
import com.flutterwave.raveandroid.RavePayManager;

public class MainActivity extends AppCompatActivity {

    EditText    amount, fname,  lname,  email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount  =   findViewById(R.id.amount);
        fname   =   findViewById(R.id.firstName);
        lname   =   findViewById(R.id.lastName);
        email   =   findViewById(R.id.email);
    }

    public void Pay(View view) {
        new RavePayManager(MainActivity.this)
                .setAmount(Double.parseDouble(amount.getText().toString()))
                .setCountry("Nigeria")
                .setCurrency("NGN")
                .setfName(fname.getText().toString())
                .setlName(lname.getText().toString())
                .setEmail(email.getText().toString())
                .setPublicKey("FLWPUBK-ae46134bb4febc6e69c9fc4adae49983-X")
                .setEncryptionKey("d87c6f7af68de0517ba760a2")
                .setTxRef("trTest")
                .initialize();

        checkPayment();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == RaveConstants.RAVE_REQUEST_CODE  &&  data    !=  null)   {
            String  msg =   data.getStringExtra("response");
            if (resultCode  == RavePayActivity.RESULT_SUCCESS)  {
                Toast.makeText(this,    "SUCCESS"   +   msg,    Toast.LENGTH_LONG).show();
            }   else if (resultCode ==  RavePayActivity.RESULT_ERROR)   {
                Toast.makeText(this,    "ERROR"   +   msg,    Toast.LENGTH_LONG).show();
            }   else if (resultCode ==  RavePayActivity.RESULT_CANCELED)    {
                Toast.makeText(this,    "CANCELLED"   +   msg,    Toast.LENGTH_LONG).show();
            }
        }   else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void checkPayment() {

    }
}

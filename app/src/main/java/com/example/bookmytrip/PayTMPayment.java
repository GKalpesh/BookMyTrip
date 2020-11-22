package com.example.bookmytrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PayTMPayment extends AppCompatActivity {

    TextView orderid, custid;
    public static TextView amount_etpt;
    Button start_transaction;
    public String holdAmount = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_t_m_payment);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        orderid = (TextView) findViewById(R.id.orderid);
        custid = (TextView) findViewById(R.id.custid);
        amount_etpt = (TextView) findViewById(R.id.amount_etpt);
        start_transaction = (Button) findViewById(R.id.start_transaction);

        holdAmount = Integer.toString(LocalPaymentOne.tktAmount);

        amount_etpt.setText(holdAmount);
        orderid.setText(LocalPaymentOne.order_id);
        custid.setText(LocalPaymentOne.customer_id);

        start_transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayTMPayment.this, CheckSum.class);
                intent.putExtra("orderid", orderid.getText().toString());
                intent.putExtra("custid", custid.getText().toString());
                startActivity(intent);
            }
        });

        if (ContextCompat.checkSelfPermission(PayTMPayment.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PayTMPayment.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

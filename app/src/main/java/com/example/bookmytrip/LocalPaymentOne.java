package com.example.bookmytrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmytrip.databaseHelp.DatabaseHelperTkt;
import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

import static com.example.bookmytrip.databaseHelp.DatabaseHelperTkt.TABLE_NAME;

public class LocalPaymentOne extends AppCompatActivity {

    Calendar calander;
    SimpleDateFormat simpledateformat;
    String dateValue;
    DatabaseHelperTkt myDb;


    public TextView getSrc;
    public TextView getDest;
    public TextView getRadio;
    public TextView dateAndTime;
    public TextView getSpinner;
    public TextView expireTV;
    public Button continueTo;
    public TextView tktNumber;
    public ImageView qrcode;
    public Button showqrcode;
    public static TextView amount_charged;
    private static RadioGroup radioGroup;
    public static RadioButton radioButton;
    public static int tktAmount = 15;
    public int selectedId;
    public String holdRadioValueNew;

    public static String order_id = "";
    public static String customer_id = "";
    public static String customer_email = "";
    public String completeData = "";

    Random random;
    int min = 10000000;
    int max = 99999999;
    int randomNumber;

    public String src = "";
    public String dest = "";
    public String radio = "";
    public String date = "";
    public String spinner = "";

    String srcValue, destValue, tktNoValue, spinnerValue, radioValue, tktAmtValue, dateTimeValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_payment_one);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDb = new DatabaseHelperTkt(this);

        getSrc = (TextView) findViewById(R.id.getSource);
        getDest = (TextView) findViewById(R.id.getDest);
        getRadio = (TextView) findViewById(R.id.getRadio);
        getSpinner = (TextView) findViewById(R.id.getSpinner);
        dateAndTime = (TextView) findViewById(R.id.dateAndTime);
        expireTV = (TextView) findViewById(R.id.expireTV);
        continueTo = (Button) findViewById(R.id.continueTo);
        tktNumber = (TextView) findViewById(R.id.tktNumber);
        amount_charged = (TextView) findViewById(R.id.amount_charged);
        qrcode = (ImageView) findViewById(R.id.qrcode);
        showqrcode = (Button) findViewById(R.id.showqrcode);

        qrcode.setVisibility(View.INVISIBLE);

        order_id = UUID.randomUUID().toString().substring(0,28);
        customer_id = FirebaseAuth.getInstance().getUid();
        customer_email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        random = new Random();

        calander = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("HH:mm:ss");
        dateValue = simpledateformat.format(calander.getTime());
        dateAndTime.setText(TrainTktOne.currentDate + " "+ dateValue);

        src = TrainTktOne.srcDemo;
        dest = TrainTktOne.destDemo;
        radio = TrainTktOne.holdRadioValue;
        spinner = TrainTktOne.valueOfSpinner;

        if (spinner.equals("1")){
            tktAmount = tktAmount*1;
        }
        else if (spinner.equals("2")){
            tktAmount = tktAmount*2;
        }
        else if (spinner.equals("3")){
            tktAmount = tktAmount*3;
        }
        else if (spinner.equals("4")){
            tktAmount = tktAmount*4;
        }
        else if (spinner.equals("5")){
            tktAmount = tktAmount*5;
        }


        if (radio.equals("Return")){
            tktAmount = tktAmount * 2;
        }


        getSrc.setText(src);
        getDest.setText(dest);
        getRadio.setText(radio);
        getSpinner.setText(spinner);
        amount_charged.setText(Integer.toString(tktAmount));
        expireTV.setText("*Ticket will expire on " + TrainTktOne.currentDate + " at 12am*");


        //radio button code start
        radioGroup = (RadioGroup)findViewById(R.id.groupRadioPayment);
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // Get the selected Radio Button
                radioButton = (RadioButton) group.findViewById(checkedId);
            }
        });
        //radio code ends

        //random Number
        if (max > min){
            randomNumber = random.nextInt((max - min) + 1) + min;
            tktNumber.setText(String.valueOf(randomNumber));
        }

        completeData = "Source: " + getSrc.getText().toString() + "\n" +
                "Destination: " + getDest.getText().toString() + "\n" +
                "Ticket No.: " + tktNumber.getText().toString() + "\n" +
                "Adults: " + getSpinner.getText().toString()  + "\n" +
                "Second Class (II)" + "  " + "Journey: " + getRadio.getText().toString() + "\n" +
                "Fare: " + amount_charged.getText().toString() + "\n" +
                "Date and Time: " + dateAndTime.getText().toString();

        //code for QR code
        showqrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrcode.setVisibility(View.VISIBLE);
                showqrcode.setVisibility(View.INVISIBLE);

                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                try {
                    BitMatrix bitMatrix = qrCodeWriter.encode(completeData, BarcodeFormat.QR_CODE, 500, 500);
                    Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.RGB_565);
                    for (int x = 0; x<500; x++){
                        for (int y=0; y<500; y++){
                            bitmap.setPixel(x,y,bitMatrix.get(x,y)? Color.BLACK : Color.WHITE);
                        }
                    }
                    qrcode.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                    e.printStackTrace();
                }
            }
        });
        //QR code ends

        continueTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) radioGroup.findViewById(selectedId); //it holds the radioButton value
                holdRadioValueNew = radioButton.getText().toString();

                if (selectedId == -1){
                    Toast.makeText(LocalPaymentOne.this, "Please select any one payment method...", Toast.LENGTH_SHORT).show();
                }
                else if (holdRadioValueNew.equals("UPI")){

                    boolean isInserted = myDb.insertData(tktNumber.getText().toString(),getSrc.getText().toString(),
                            getDest.getText().toString(), getSpinner.getText().toString(), getRadio.getText().toString(),
                            amount_charged.getText().toString(), dateAndTime.getText().toString(), customer_email
                    );
                    if(isInserted == true) {
                        Log.i("DataBase FeedBack", "Data Inserted");
                    }
                    else {
                        Log.i("DataBase FeedBack", "Data Not Inserted");
                    }
                    Intent intent = new Intent(LocalPaymentOne.this, UPIPayment.class);
                    startActivity(intent);
                }
                else if (holdRadioValueNew.equals("PayTM")){

                    boolean isInserted = myDb.insertData(tktNumber.getText().toString(),getSrc.getText().toString(),
                            getDest.getText().toString(), getSpinner.getText().toString(), getRadio.getText().toString(),
                            amount_charged.getText().toString(), dateAndTime.getText().toString(), customer_email
                    );
                    if(isInserted == true) {
                        Log.i("DataBase FeedBack", "Data Inserted");
                    }
                    else {
                        Log.i("DataBase FeedBack", "Data Not Inserted");
                    }
                    Intent intent = new Intent(LocalPaymentOne.this, PayTMPayment.class);
                    startActivity(intent);
                }
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


//    srcValue = "Soorce: " + getSrc.getText().toString();
//    destValue = "Destination: " + getDest.getText().toString();
//    tktNoValue = "Ticket Numbe: " + tktNumber.getText().toString();
//    spinnerValue = "Travellers: " + getSpinner.getText().toString();
//    radioValue = "Journey: " + getRadio.getText().toString();
//    tktAmtValue = "Fare: " + amount_charged.getText().toString();
//    dateTimeValue = "Date: " + dateAndTime.getText().toString();

}

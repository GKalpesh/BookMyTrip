package com.example.bookmytrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.badge.BadgeUtils;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PNRStatus extends AppCompatActivity {

    private RequestQueue mQueuePNR;


    TextView pnrData;
    EditText pnrNumber;
    Button getPnrData;
    String pnrValue = "";
    OkHttpClient client;

    String myResponse = "";
    String trainNumber = "";
    String trainName = "";
    String boardingDate = "";
    String from = "";
    String to = "";
    String reservedUpto = "";
    String boardingPoint = "";
    String classPNR = "";
    String journeyDetails = "";

    String passengerNo = "";
    String bookingStatus = "";
    String currentStatus = "";

    String singleParsed = "";
    String dataParsed = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_n_r_status);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pnrNumber = (EditText) findViewById(R.id.pnrNumber);
        getPnrData = (Button) findViewById(R.id.getPnrData);
        pnrData = (TextView) findViewById(R.id.pnrData);

        mQueuePNR = Volley.newRequestQueue(this);

        getPnrData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pnrValue = pnrNumber.getText().toString();

                if (pnrValue.isEmpty() || pnrValue.length() <= 9 || pnrValue.length() >= 11){
                    Toast.makeText(PNRStatus.this, "Please enter a valid PNR number.", Toast.LENGTH_SHORT).show();
                }else{

                    client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("https://indianrailways.p.rapidapi.com/index.php?pnr=" + pnrValue)
                            .get()
                            .addHeader("x-rapidapi-host", "indianrailways.p.rapidapi.com")
                            .addHeader("x-rapidapi-key", "1c6ea5b995msh2136c998f68f93bp1126afjsn885c9e6351b7")
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            call.cancel();
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull okhttp3.Response response) throws IOException {
                            if (response.isSuccessful()){

                                try {
                                    myResponse = response.body().string();

                                    JSONObject reader = new JSONObject(myResponse);

                                    JSONObject sys  = reader.getJSONObject("journeyDetails");
                                    trainNumber = sys.getString("trainNumber");
                                    trainName = sys.getString("trainName");
                                    boardingDate = sys.getString("boardingDate");
                                    from = sys.getString("from");
                                    to = sys.getString("to");
                                    reservedUpto = sys.getString("reservedUpto");
                                    boardingPoint = sys.getString("boardingPoint");
                                    classPNR = sys.getString("class");

                                    journeyDetails = "Train Number:  " + trainNumber + "\n" +
                                            "Train Name:  " + trainName + "\n" +
                                            "Boarding Date:  " + boardingDate + "\n" +
                                            "From:  " + from + "\n" +
                                            "to:  " + to + "\n" +
                                            "Reservation Upto:  " + reservedUpto + "\n" +
                                            "Boarding Point:  " + boardingPoint + "\n" +
                                            "class:  " + classPNR + "\n" ;

                                    JSONArray myJsonArray =reader.getJSONArray("bookingStatus");
                                    for (int i = 0; i < myJsonArray.length(); i++) {
                                        JSONObject object = myJsonArray.getJSONObject(i);

                                        passengerNo = object.getString("passengerNo");
                                        bookingStatus = object.getString("bookingStatus");
                                        currentStatus = object.getString("currentStatus");

                                        singleParsed = "Passenger No.:  " + passengerNo + "\n" +
                                                "Booking Status:  " + bookingStatus + "\n" +
                                                "Current Status:  " + currentStatus + "\n\n";

                                        dataParsed = dataParsed + singleParsed;

                                    }

                                    PNRStatus.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (journeyDetails.isEmpty() && dataParsed.isEmpty()){
                                                pnrData.setText("No data found for the given PNR.");
                                            }
                                            else {
                                                pnrData.setText(journeyDetails + "\n" + dataParsed);
                                            }
                                        }
                                    });

                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

                    pnrData.setText("No data found for the given PNR.");

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
}

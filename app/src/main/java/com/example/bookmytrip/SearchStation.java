package com.example.bookmytrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class SearchStation extends AppCompatActivity {
    private RequestQueue mQueue;

    EditText stationName;
    TextView stationData;
    Button getStationData;

    OkHttpClient client;
    String urlStation = "";
    String myResponseStation = "";
    String nameStation = "";
    String codeStation = "";
    String singleParsedStation = "";
    String dataParsedStation = "";
    String valueStation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_station);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        stationName = (EditText) findViewById(R.id.stationName);
        stationData = (TextView) findViewById(R.id.stationData);
        getStationData = (Button) findViewById(R.id.getStationData);

        mQueue = Volley.newRequestQueue(this);

        getStationData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valueStation = stationName.getText().toString();

                client = new OkHttpClient();

                urlStation = "https://indianrailways.p.rapidapi.com/findstations.php?station=" + valueStation;

                final Request request = new Request.Builder()
                        .url(urlStation)
                        .get()
                        .addHeader("x-rapidapi-host", "indianrailways.p.rapidapi.com")
                        .addHeader("x-rapidapi-key", "1c6ea5b995msh2136c998f68f93bp1126afjsn885c9e6351b7")
                        .build();

                singleParsedStation = "";
                dataParsedStation = "";


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
                                myResponseStation = response.body().string();

                                JSONObject myjsonObject = new JSONObject(myResponseStation);
                                JSONArray myJsonArray = myjsonObject.getJSONArray("stations");

                                for (int i = 0; i < myJsonArray.length(); i++){
                                    JSONObject object = myJsonArray.getJSONObject(i);
                                    nameStation = object.getString("stationName");
                                    codeStation = object.getString("stationCode");

                                    singleParsedStation = "Station Name:  " + nameStation + "\n" + "Station Code:  " + codeStation + "\n\n";

                                    dataParsedStation = dataParsedStation + singleParsedStation;
                                }
                                SearchStation.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        stationData.setText(dataParsedStation);
                                    }
                                });

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                });

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

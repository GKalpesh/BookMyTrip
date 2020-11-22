package com.example.bookmytrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import RecyclerView.DatabaseRecycleAdapter;
import RecyclerView.ModelClass;

public class ExpressTrainShedule extends AppCompatActivity {

    RecyclerView objRecyclerView;
    ArrayList<ModelClass> objModelClassArrayList;
    DatabaseRecycleAdapter objDatabaseRecycleAdapter;

    public ImageView clickDropdown7;
    public ImageView clickDropdown8;

    String[] language ={"Panvel[PNVL]", "CSMT", "Goa,Madgaon", "Pune Jn", "Surat[ST]", "Ahmedabad", "Chennai Central", "New Delhi",
            "Patna", "Kolhapur", "Nerul", "Panvel"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express_train_shedule);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Creating the instance of ArrayAdapter containing list of language names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.auto_dropdown,language);
        //Getting the instance of AutoCompleteTextView
        final AutoCompleteTextView actvSrc =  (AutoCompleteTextView)findViewById(R.id.autoCompleteTextViewSrc3);
        final AutoCompleteTextView actvDest =  (AutoCompleteTextView)findViewById(R.id.autoCompleteTextViewDest3);

        clickDropdown7 = (ImageView) findViewById(R.id.clickDropdow7);
        clickDropdown8 = (ImageView) findViewById(R.id.clickDropdown8);
        Button ExpressButton = (Button) findViewById(R.id.ExpresssearchTrains);
        objRecyclerView = findViewById(R.id.data1);
        objModelClassArrayList=new ArrayList<>();

        actvSrc.setThreshold(1);//will start working from first character
        actvSrc.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actvSrc.setTextColor(Color.BLACK);

        actvDest.setThreshold(1);//will start working from first character
        actvDest.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actvDest.setTextColor(Color.BLACK);
        database db = new database(ExpressTrainShedule.this);
        final SQLiteDatabase database = db.getReadableDatabase( );

        ExpressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Shedule.this, Timings.class);
//                startActivity(intent);
                if (actvSrc.getText().toString().equals("Patna") && actvDest.getText().toString().equals("Ahmedabad")){
                    Cursor cursor = database.rawQuery("SELECT STATIONS,TIMING,PLATFORM FROM TRAINTIMING WHERE STATIONS = ?",new String[]{"PATNA-AHMEDABAD"});

                    if(cursor.getCount()==0){
                        Toast.makeText(ExpressTrainShedule.this,"No data is return",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(ExpressTrainShedule.this,"Success",Toast.LENGTH_SHORT).show();
                        while (cursor.moveToNext()){
                            objModelClassArrayList.add(new ModelClass(cursor.getString(0),
                                    cursor.getString(1),
                                    cursor.getString(2)
                            ));
                        }
                        objDatabaseRecycleAdapter=new DatabaseRecycleAdapter(objModelClassArrayList);
                        objRecyclerView.hasFixedSize();
                        objRecyclerView.setLayoutManager(new LinearLayoutManager(ExpressTrainShedule.this));
                        objRecyclerView.setAdapter(objDatabaseRecycleAdapter);
                    }

                }
                else if (actvSrc.getText().toString().equals("CSMT") && actvDest.getText().toString().equals("Kolhapur")){
                    Cursor cursor = database.rawQuery("SELECT STATIONS,TIMING,PLATFORM FROM TRAINTIMING WHERE STATIONS = ?",new String[]{"CSMT-KOLHAPUR"});

                    if(cursor.getCount()==0){
                        Toast.makeText(ExpressTrainShedule.this,"No data is return",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(ExpressTrainShedule.this,"Success",Toast.LENGTH_SHORT).show();
                        while (cursor.moveToNext()){
                            objModelClassArrayList.add(new ModelClass(cursor.getString(0),
                                    cursor.getString(1),
                                    cursor.getString(2)
                            ));
                        }
                        objDatabaseRecycleAdapter=new DatabaseRecycleAdapter(objModelClassArrayList);
                        objRecyclerView.hasFixedSize();
                        objRecyclerView.setLayoutManager(new LinearLayoutManager(ExpressTrainShedule.this));
                        objRecyclerView.setAdapter(objDatabaseRecycleAdapter);
                    }
                }
                else {
                    Toast.makeText(ExpressTrainShedule.this, "Failed", Toast.LENGTH_SHORT).show();

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




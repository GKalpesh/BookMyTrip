package com.example.bookmytrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import RecyclerView.DatabaseRecycleAdapter;
import RecyclerView.ModelClass;

public class Shedule extends AppCompatActivity {
    RecyclerView objRecyclerView;
    ArrayList<ModelClass>objModelClassArrayList;
    DatabaseRecycleAdapter objDatabaseRecycleAdapter;

    public ImageView clickDropdown5;
    public ImageView clickDropdown6;

    String[] language = {"CSMT", "Byculla", "Chichpokli", "Parel", "Dadar", "Matunga", "Kurla", "Ghatkopar", "Vikhroli",
            "Kanjur Marg","Bhandup", "Mulund", "Thane", "Diva Jn", "Dombivli", "Kalyan", "Ambernath", "Badlapur", "Neral", "Bhivpuri Road",
            "Karjat", "Khopoli", "Titwala", "Asangaon", "Kasara", "Churchgate", "Marine Lines", "Charni Road", "Grant Road", "Mumbai Central",
            "Mahalakshmi", "Lower Parel", "Prabhadevi", "Matunga Road", "Bandra", "Khar Road", "Andheri", "Jogeshwari", "Malad", "Borivali",
            "Mira Road", "Bhayander", "Vasai Road", "Virar", "Masjid", "Sandhurst Road", "Sewri", "Vadala Road", "Mahin Jn", "Vile Parle",
            "Goregaon", "Tilaknagar", "Chembur", "Mankhurd", "Vashi", "Sanpada", "Juinagar", "Nerul", "Belapur CBD", "Kharghar", "Panvel",
            "Airoli", "Rabale", "Ghansoli", "Koperkhairne", "Turbhe"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shedule);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Creating the instance of ArrayAdapter containing list of language names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.auto_dropdown,language);
        //Getting the instance of AutoCompleteTextView
        final AutoCompleteTextView actvSrc =  (AutoCompleteTextView)findViewById(R.id.autoCompleteTextViewSrc1);
        final AutoCompleteTextView actvDest =  (AutoCompleteTextView)findViewById(R.id.autoCompleteTextViewDest1);

        clickDropdown5 = (ImageView) findViewById(R.id.clickDropdow5);
        clickDropdown6 = (ImageView) findViewById(R.id.clickDropdown6);
        Button SearchTrains = (Button) findViewById(R.id.searchTrains);
        objRecyclerView = findViewById(R.id.dataRecycle);
        objModelClassArrayList=new ArrayList<>();

        actvSrc.setThreshold(1);//will start working from first character
        actvSrc.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actvSrc.setTextColor(Color.BLACK);

        actvDest.setThreshold(1);//will start working from first character
        actvDest.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actvDest.setTextColor(Color.BLACK);
        database db = new database(this);
        final SQLiteDatabase database = db.getReadableDatabase();

        clickDropdown5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actvSrc.showDropDown();
            }
        });

        clickDropdown6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actvDest.showDropDown();
            }
        });


        SearchTrains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Shedule.this, Timings.class);
//                startActivity(intent);
                if (actvSrc.getText().toString().equals("Nerul") && actvDest.getText().toString().equals("Andheri")){
                    Toast.makeText(Shedule.this,"Success",Toast.LENGTH_SHORT).show();

                    Cursor cursor = database.rawQuery("SELECT STATIONS,TIMING,PLATFORM FROM TRAINTIMING WHERE STATIONS = ?",new String[]{"Nerul-Andheri"});

                    if(cursor.getCount()==0){
                        Toast.makeText(Shedule.this,"No data is return",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        while (cursor.moveToNext()){
                            objModelClassArrayList.add(new ModelClass(cursor.getString(0),
                                    cursor.getString(1),
                                    cursor.getString(2)
                            ));
                        }
                        objDatabaseRecycleAdapter=new DatabaseRecycleAdapter(objModelClassArrayList);
                        objRecyclerView.hasFixedSize();
                        objRecyclerView.setLayoutManager(new LinearLayoutManager(Shedule.this));
                        objRecyclerView.setAdapter(objDatabaseRecycleAdapter);
                    }

                }

                //nerul to thane


                else if (actvSrc.getText().toString().equals("Nerul") && actvDest.getText().toString().equals("Thane")){
                    Toast.makeText(Shedule.this,"Success",Toast.LENGTH_SHORT).show();

                    Cursor cursor = database.rawQuery("SELECT STATIONS,TIMING,PLATFORM FROM TRAINTIMING WHERE STATIONS = ?",new String[]{"NERUL-THANE"});

                    if(cursor.getCount()==0){
                        Toast.makeText(Shedule.this,"No data is return",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        while (cursor.moveToNext()){
                            objModelClassArrayList.add(new ModelClass(cursor.getString(0),
                                    cursor.getString(1),
                                    cursor.getString(2)
                            ));
                        }
                        objDatabaseRecycleAdapter=new DatabaseRecycleAdapter(objModelClassArrayList);
                        objRecyclerView.hasFixedSize();
                        objRecyclerView.setLayoutManager(new LinearLayoutManager(Shedule.this));
                        objRecyclerView.setAdapter(objDatabaseRecycleAdapter);
                    }
                }

                //nerul to panvel
                else if (actvSrc.getText().toString().equals("Nerul") && actvDest.getText().toString().equals("Panvel")){
                    Toast.makeText(Shedule.this,"Success",Toast.LENGTH_SHORT).show();

                    Cursor cursor = database.rawQuery("SELECT STATIONS,TIMING,PLATFORM FROM TRAINTIMING WHERE STATIONS = ?",new String[]{"NERUL-PANVEL"});

                    if(cursor.getCount()==0){
                        Toast.makeText(Shedule.this,"No data is return",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        while (cursor.moveToNext()){
                            objModelClassArrayList.add(new ModelClass(cursor.getString(0),
                                    cursor.getString(1),
                                    cursor.getString(2)
                            ));
                        }
                        objDatabaseRecycleAdapter=new DatabaseRecycleAdapter(objModelClassArrayList);
                        objRecyclerView.hasFixedSize();
                        objRecyclerView.setLayoutManager(new LinearLayoutManager(Shedule.this));
                        objRecyclerView.setAdapter(objDatabaseRecycleAdapter);
                    }
                }
                //nerul to Ambernath

                else if (actvSrc.getText().toString().equals("Nerul") && actvDest.getText().toString().equals("Ambernath")){
                    Toast.makeText(Shedule.this,"Success",Toast.LENGTH_SHORT).show();

                    Cursor cursor = database.rawQuery("SELECT STATIONS,TIMING,PLATFORM FROM TRAINTIMING WHERE STATIONS = ?",new String[]{"NERUL-AMBERNATH"});

                    if(cursor.getCount()==0){
                        Toast.makeText(Shedule.this,"No data is return",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        while (cursor.moveToNext()){
                            objModelClassArrayList.add(new ModelClass(cursor.getString(0),
                                    cursor.getString(1),
                                    cursor.getString(2)
                            ));
                        }
                        objDatabaseRecycleAdapter=new DatabaseRecycleAdapter(objModelClassArrayList);
                        objRecyclerView.hasFixedSize();
                        objRecyclerView.setLayoutManager(new LinearLayoutManager(Shedule.this));
                        objRecyclerView.setAdapter(objDatabaseRecycleAdapter);
                    }
                }
                else {
                    Toast.makeText(Shedule.this,"Failed",Toast.LENGTH_SHORT).show();

                }}
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

package com.example.bookmytrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bookmytrip.databaseHelp.DatabaseHelperTkt;
import com.example.bookmytrip.recyclerViewClasses.ModalClassTkt;
import com.example.bookmytrip.recyclerViewClasses.RecyclerAdaper;

import java.util.ArrayList;

import static com.example.bookmytrip.databaseHelp.DatabaseHelperTkt.TABLE_NAME;

public class ShowTKTValue extends AppCompatActivity {
    ArrayList<ModalClassTkt> modalClassTktArrayList;
    RecyclerAdaper recyclerAdaper;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_t_k_t_value);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.tktRV);
        modalClassTktArrayList  = new ArrayList<>();
        showHistory();
    }

    public void showHistory(){
        try {
            DatabaseHelperTkt myDb = new DatabaseHelperTkt(ShowTKTValue.this);
            SQLiteDatabase objsqLiteDatabase = myDb.getReadableDatabase();
            if (objsqLiteDatabase != null){
                Cursor objcursor = objsqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);

                if (objcursor.getCount() == 0){
                    Toast.makeText(ShowTKTValue.this, "No data found", Toast.LENGTH_SHORT).show();
                }
                else {
                    while (objcursor.moveToNext()){
                        modalClassTktArrayList.add(new ModalClassTkt(objcursor.getString(0),
                                objcursor.getString(1), objcursor.getString(2),
                                objcursor.getString(3), objcursor.getString(4),
                                objcursor.getString(5), objcursor.getString(6),
                                objcursor.getString(7)
                        ));
                    }

                    recyclerAdaper = new RecyclerAdaper(modalClassTktArrayList);
                    recyclerView.hasFixedSize();
                    recyclerView.setLayoutManager(new LinearLayoutManager(ShowTKTValue.this));
                    recyclerView.setAdapter(recyclerAdaper);

                }

            }
            else {
                Toast.makeText(ShowTKTValue.this, "Database is null", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(ShowTKTValue.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

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
import com.example.bookmytrip.recyclerViewClasses.ModelClassTran;
import com.example.bookmytrip.recyclerViewClasses.RecyclerAdaper;
import com.example.bookmytrip.recyclerViewClasses.RecyclerAdapterTran;

import java.util.ArrayList;

import static com.example.bookmytrip.databaseHelp.DatabaseHelperTkt.TABLE_NAME;

public class ShowTranValue extends AppCompatActivity {
    ArrayList<ModelClassTran> modelClassTranArrayList;
    RecyclerAdapterTran recyclerAdaperTran;

    RecyclerView recyclerViewTran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tran_value);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewTran = (RecyclerView) findViewById(R.id.tranRV);
        modelClassTranArrayList = new ArrayList<>();

        showHistoryTran();
    }

    public void showHistoryTran(){
        try {
            DatabaseHelperTkt myDb = new DatabaseHelperTkt(ShowTranValue.this);
            SQLiteDatabase objsqLiteDatabase = myDb.getReadableDatabase();
            if (objsqLiteDatabase != null){
                Cursor objcursor = objsqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);

                if (objcursor.getCount() == 0){
                    Toast.makeText(ShowTranValue.this, "No data found", Toast.LENGTH_SHORT).show();
                }
                else {
                    while (objcursor.moveToNext()){
                        modelClassTranArrayList.add(new ModelClassTran(objcursor.getString(1),
                                objcursor.getString(2), objcursor.getString(5),
                                objcursor.getString(6)));
                    }

                    recyclerAdaperTran = new RecyclerAdapterTran(modelClassTranArrayList);
                    recyclerViewTran.hasFixedSize();
                    recyclerViewTran.setLayoutManager(new LinearLayoutManager(ShowTranValue.this));
                    recyclerViewTran.setAdapter(recyclerAdaperTran);

                }

            }
            else {
                Toast.makeText(ShowTranValue.this, "Database is null", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(ShowTranValue.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

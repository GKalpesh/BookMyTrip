package com.example.bookmytrip.ui.dashboard;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmytrip.R;
import com.example.bookmytrip.ShowTKTValue;
import com.example.bookmytrip.databaseHelp.DatabaseHelperTkt;
import com.example.bookmytrip.recyclerViewClasses.ModalClassTkt;
import com.example.bookmytrip.recyclerViewClasses.RecyclerAdaper;

import java.util.ArrayList;

import static com.example.bookmytrip.databaseHelp.DatabaseHelperTkt.TABLE_NAME;

public class DashboardFragment extends Fragment {

    ArrayList<ModalClassTkt> modalClassTktArrayList;
    RecyclerAdaper recyclerAdaper;

    RecyclerView recyclerView;

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.tktRVDB);
        modalClassTktArrayList  = new ArrayList<>();
        showHistory();

        return root;
    }

    public void showHistory(){
        try {
            DatabaseHelperTkt myDb = new DatabaseHelperTkt(getActivity());
            SQLiteDatabase objsqLiteDatabase = myDb.getReadableDatabase();
            if (objsqLiteDatabase != null){
                Cursor objcursor = objsqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);

                if (objcursor.getCount() == 0){
                    Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();
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
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(recyclerAdaper);

                }

            }
            else {
                Toast.makeText(getActivity(), "Database is null", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
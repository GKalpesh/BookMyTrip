package RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmytrip.R;

import java.util.ArrayList;

public class DatabaseRecycleAdapter extends RecyclerView.Adapter<DatabaseRecycleAdapter.DatabaseViewHolder> {
    ArrayList<ModelClass> objModelClassArrayList;

    public DatabaseRecycleAdapter(ArrayList<ModelClass> objModelClassArrayList) {
        this.objModelClassArrayList = objModelClassArrayList;
    }

    @NonNull
    @Override
    public DatabaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View Singlerow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_row,viewGroup,false);
        return new DatabaseViewHolder(Singlerow);
    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseViewHolder databaseViewHolder, int i) {
        ModelClass objModelClass = objModelClassArrayList.get(i);
        databaseViewHolder.stationsTV.setText(objModelClass.getStations());
        databaseViewHolder.timingTV.setText(objModelClass.getTiming());
        databaseViewHolder.platformTV.setText(objModelClass.getPlatform());;


    }

    @Override
    public int getItemCount() {
        return objModelClassArrayList.size();
    }

    public static class DatabaseViewHolder extends RecyclerView.ViewHolder {
        TextView stationsTV,timingTV,platformTV;
        public DatabaseViewHolder(@NonNull View itemView) {
            super(itemView);
            stationsTV = itemView.findViewById(R.id.station);
            timingTV = itemView.findViewById(R.id.timing);
            platformTV = itemView.findViewById(R.id.Platform);
        }
    }

}

package com.example.bookmytrip.recyclerViewClasses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmytrip.R;

import java.util.ArrayList;

public class RecyclerAdapterTran extends RecyclerView.Adapter<RecyclerAdapterTran.DatabaseViewHolderTran> {

    ArrayList<ModelClassTran> modelClassTranArrayList;

    public RecyclerAdapterTran(ArrayList<ModelClassTran> modelClassTranArrayList) {
        this.modelClassTranArrayList = modelClassTranArrayList;
    }

    @NonNull
    @Override
    public DatabaseViewHolderTran onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View singleRowTran = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowfortransaction, parent, false);
        return new DatabaseViewHolderTran(singleRowTran);
    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseViewHolderTran holder, int position) {
        ModelClassTran modelClassTran = modelClassTranArrayList.get(position);
        holder.srcTran.setText(modelClassTran.getSrcTran());
        holder.destTran.setText(modelClassTran.getDestTran());
        holder.fareTran.setText(modelClassTran.getTktAmtTran());
        holder.dateTran.setText(modelClassTran.getDateTimeTran());
    }

    @Override
    public int getItemCount() {
        return modelClassTranArrayList.size();
    }

    public static class DatabaseViewHolderTran extends RecyclerView.ViewHolder{

        TextView srcTran, destTran, fareTran, dateTran;

        public DatabaseViewHolderTran(@NonNull View itemView) {
            super(itemView);
            srcTran = itemView.findViewById(R.id.srctran);
            destTran = itemView.findViewById(R.id.desttran);
            fareTran = itemView.findViewById(R.id.faretran);
            dateTran = itemView.findViewById(R.id.datetran);
        }
    }
}

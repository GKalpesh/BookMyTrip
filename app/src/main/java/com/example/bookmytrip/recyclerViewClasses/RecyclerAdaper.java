package com.example.bookmytrip.recyclerViewClasses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmytrip.R;

import java.util.ArrayList;

public class RecyclerAdaper extends RecyclerView.Adapter<RecyclerAdaper.DatabaseViewHolderTkt> {

    ArrayList<ModalClassTkt> modalClassTkts;

    public RecyclerAdaper(ArrayList<ModalClassTkt> modalClassTkts) {
        this.modalClassTkts = modalClassTkts;
    }

    @NonNull
    @Override
    public DatabaseViewHolderTkt onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View singleRowTkt = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowfortkt, parent, false);
        return new DatabaseViewHolderTkt(singleRowTkt);
    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseViewHolderTkt holder, int position) {
        ModalClassTkt modalClassTkt = modalClassTkts.get(position);
        holder.tktno.setText("Ticket Number: " + modalClassTkt.getTktNoValue());
        holder.src.setText("Source: " + modalClassTkt.getSrcValue());
        holder.dest.setText("Destination: " + modalClassTkt.getDestValue());
        holder.spinner.setText("Travellers: " + modalClassTkt.getSpinnerValue());
        holder.radio.setText("Journey: " + modalClassTkt.getRadioValue());
        holder.fare.setText("Fare: " + modalClassTkt.getTktAmtValue());
        holder.date.setText("Date: " + modalClassTkt.getDateTimeValue());
        holder.email.setText("User Email: " + modalClassTkt.getEmailValue());

    }

    @Override
    public int getItemCount() {
        return modalClassTkts.size();
    }

    public static class DatabaseViewHolderTkt extends RecyclerView.ViewHolder{

        TextView tktno, src, dest, spinner, radio, fare, date, email;

        public DatabaseViewHolderTkt(@NonNull View itemView) {
            super(itemView);
            tktno = itemView.findViewById(R.id.tktnoTV);
            src = itemView.findViewById(R.id.srcTV);
            dest = itemView.findViewById(R.id.destTV);
            spinner = itemView.findViewById(R.id.spinnerTV);
            radio = itemView.findViewById(R.id.radioTV);
            fare = itemView.findViewById(R.id.fareTV);
            date = itemView.findViewById(R.id.dateTV);
            email = itemView.findViewById(R.id.emailTV);

        }
    }

}

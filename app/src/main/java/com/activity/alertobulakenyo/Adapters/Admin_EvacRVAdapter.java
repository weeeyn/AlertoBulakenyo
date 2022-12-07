package com.activity.alertobulakenyo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.activity.alertobulakenyo.Admins.Admin_EditEvac;
import com.activity.alertobulakenyo.ObjectClasses.EvacuationHolder;
import com.activity.alertobulakenyo.R;

import java.util.ArrayList;

public class Admin_EvacRVAdapter extends RecyclerView.Adapter<Admin_EvacRVAdapter.ViewHolder> {

    private ArrayList<EvacuationHolder> evacuationHolderArrayList;
    private Context context;

    public Admin_EvacRVAdapter(ArrayList<EvacuationHolder> evacuationHolderArrayList, Context context) {
        this.evacuationHolderArrayList = evacuationHolderArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_evac, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EvacuationHolder evacuationHolder = evacuationHolderArrayList.get(position);
        holder.evacNameTV.setText(evacuationHolder.getEvacuationName());
        holder.evacAddTV.setText(evacuationHolder.getEvacuationAddress());
    }

    @Override
    public int getItemCount() {
        return evacuationHolderArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView evacNameTV;
        private final TextView evacAddTV;

        public ViewHolder(View itemView) {
            super(itemView);

            evacNameTV = itemView.findViewById(R.id.tvEvacName);
            evacAddTV = itemView.findViewById(R.id.tvLoc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EvacuationHolder evacuationHolder = evacuationHolderArrayList.get(getAdapterPosition());
                    Intent intent = new Intent(context, Admin_EditEvac.class);
                    intent.putExtra("evac", evacuationHolder);
                    context.startActivity(intent);
                }
            });
        }
    }
}

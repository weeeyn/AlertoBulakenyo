package com.activity.alertobulakenyo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterLS extends RecyclerView.Adapter<AdapterLS.ViewHolder> {

    private ArrayList<WarningHolder> warningHolderArrayList;
    private Context context;

    public AdapterLS(ArrayList<WarningHolder> warningHolderArrayList, Context context) {
        this.warningHolderArrayList = warningHolderArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterLS.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterLS.ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_ls, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLS.ViewHolder holder, int position) {
        WarningHolder warningHolder = warningHolderArrayList.get(position);
        holder.dateTimeTv.setText(warningHolder.getDisasterDate());
        holder.timeTv.setText((warningHolder.getDisasterTime()));
        holder.brgyCityTv.setText(warningHolder.getDisasterBrgy() + ", " + warningHolder.getDisasterCity());
    }

    @Override
    public int getItemCount() {
        return warningHolderArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView dateTimeTv;
        private final TextView timeTv;
        private final TextView brgyCityTv;

        public ViewHolder(View itemView) {
            super(itemView);

            dateTimeTv = itemView.findViewById(R.id.tvDate);
            timeTv = itemView.findViewById(R.id.tvTime);
            brgyCityTv = itemView.findViewById(R.id.tvLoc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WarningHolder warningHolder = warningHolderArrayList.get(getAdapterPosition());
                    Intent intent = new Intent(context, LS_info.class);
                    intent.putExtra("lsinfo", warningHolder);
                    context.startActivity(intent);
                }
            });
        }
    }
}

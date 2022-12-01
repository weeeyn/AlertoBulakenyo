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

public class Admin_FireAdapter extends RecyclerView.Adapter<Admin_FireAdapter.ViewHolder> {

    private ArrayList<WarningHolder> warningHolderArrayList;
    private Context context;

    public Admin_FireAdapter(ArrayList<WarningHolder> warningHolderArrayList, Context context) {
        this.warningHolderArrayList = warningHolderArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Admin_FireAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Admin_FireAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_warn_fire, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Admin_FireAdapter.ViewHolder holder, int position) {
        WarningHolder warningHolder = warningHolderArrayList.get(position);
        holder.dateTimeTv.setText(warningHolder.getDisasterDateTime());
        holder.warnTypeTv.setText((warningHolder.getDisasterType()));
        holder.fireTv.setText(warningHolder.getFireLevel());
        holder.brgyCityTv.setText(warningHolder.getDisasterBrgy() + ", " + warningHolder.getDisasterCity());
    }

    @Override
    public int getItemCount() {
        return warningHolderArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView dateTimeTv;
        private final TextView warnTypeTv;
        private final TextView fireTv;
        private final TextView brgyCityTv;

        public ViewHolder(View itemView) {
            super(itemView);

            dateTimeTv = itemView.findViewById(R.id.tvDateTime);
            warnTypeTv = itemView.findViewById(R.id.tvWarnType);
            fireTv = itemView.findViewById(R.id.tvInfo);
            brgyCityTv = itemView.findViewById(R.id.tvCityProv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WarningHolder warningHolder = warningHolderArrayList.get(getAdapterPosition());
                    Intent intent = new Intent(context, Admin_DisasterFireInfo.class);
                    intent.putExtra("firewarn", warningHolder);
                    context.startActivity(intent);
                }
            });
        }
    }
}


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

public class Admin_EQAdapter extends RecyclerView.Adapter<Admin_EQAdapter.ViewHolder> {

    private ArrayList<WarningHolder> warningHolderArrayList;
    private Context context;

    public Admin_EQAdapter(ArrayList<WarningHolder> warningHolderArrayList, Context context) {
        this.warningHolderArrayList = warningHolderArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Admin_EQAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Admin_EQAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_warn_eq, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Admin_EQAdapter.ViewHolder holder, int position) {
        WarningHolder warningHolder = warningHolderArrayList.get(position);
        holder.dateTimeTv.setText(warningHolder.getDisasterDateTime());
        holder.warnTypeTv.setText((warningHolder.getDisasterType()));
        holder.magInfoTv.setText(warningHolder.getEqMagnitude());
        holder.brgyCityTv.setText(warningHolder.getDisasterBrgy() + ", " + warningHolder.getDisasterCity());
    }

    @Override
    public int getItemCount() {
        return warningHolderArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView dateTimeTv;
        private final TextView warnTypeTv;
        private final TextView magInfoTv;
        private final TextView brgyCityTv;

        public ViewHolder(View itemView) {
            super(itemView);

            dateTimeTv = itemView.findViewById(R.id.tvDateTime);
            warnTypeTv = itemView.findViewById(R.id.tvWarnType);
            magInfoTv = itemView.findViewById(R.id.tvInfo);
            brgyCityTv = itemView.findViewById(R.id.tvCityProv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WarningHolder warningHolder = warningHolderArrayList.get(getAdapterPosition());
                    Intent intent = new Intent(context, Admin_DisasterEQInfo.class);
                    intent.putExtra("eqwarn", warningHolder);
                    context.startActivity(intent);
                }
            });
        }
    }
}
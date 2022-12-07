package com.activity.alertobulakenyo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.activity.alertobulakenyo.R;
import com.activity.alertobulakenyo.ResidentUsers.Ty_info;
import com.activity.alertobulakenyo.ObjectClasses.WarningHolder;

import java.util.ArrayList;

public class AdapterTy extends RecyclerView.Adapter<AdapterTy.ViewHolder> {

    private ArrayList<WarningHolder> warningHolderArrayList;
    private Context context;

    public AdapterTy(ArrayList<WarningHolder> warningHolderArrayList, Context context) {
        this.warningHolderArrayList = warningHolderArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_ty, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WarningHolder warningHolder = warningHolderArrayList.get(position);
        holder.dateTimeTv.setText(warningHolder.getDisasterDate());
        holder.timeTv.setText((warningHolder.getDisasterTime()));
        holder.tyNameTv.setText(warningHolder.getTyphoonName());
        holder.tvSignal.setText(warningHolder.getTyphoonSignal());
        holder.brgyCityTv.setText(warningHolder.getBody());
    }

    @Override
    public int getItemCount() {
        return warningHolderArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView dateTimeTv;
        private final TextView timeTv;
        private final TextView tyNameTv;
        private final TextView tvSignal;
        private final TextView brgyCityTv;

        public ViewHolder(View itemView) {
            super(itemView);

            dateTimeTv = itemView.findViewById(R.id.tvDate);
            timeTv = itemView.findViewById(R.id.tvTime);
            tyNameTv = itemView.findViewById(R.id.tvTyName);
            tvSignal = itemView.findViewById(R.id.tvSignal);
            brgyCityTv = itemView.findViewById(R.id.tvLoc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WarningHolder warningHolder = warningHolderArrayList.get(getAdapterPosition());
                    Intent intent = new Intent(context, Ty_info.class);
                    intent.putExtra("tyinfo", warningHolder);
                    context.startActivity(intent);
                }
            });
        }
    }
}

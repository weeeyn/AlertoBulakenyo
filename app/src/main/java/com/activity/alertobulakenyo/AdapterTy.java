package com.activity.alertobulakenyo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

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
    public AdapterTy.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterTy.ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_ty, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTy.ViewHolder holder, int position) {
        WarningHolder warningHolder = warningHolderArrayList.get(position);
        holder.dateTimeTv.setText(warningHolder.getDisasterDate());
        holder.timeTv.setText((warningHolder.getDisasterTime()));
        holder.tyNameTv.setText(warningHolder.getTyphoonName());
        holder.tvSignal.setText(warningHolder.getTyphoonSignal());
        holder.brgyCityTv.setText(warningHolder.getDisasterBrgy() + ", " + warningHolder.getDisasterCity());
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

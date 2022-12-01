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

public class PinakaAdmin_ViewAccRVAdapter extends RecyclerView.Adapter<PinakaAdmin_ViewAccRVAdapter.ViewHolder> {

    private ArrayList<AdminHolder> adminHolderArrayList;
    private Context context;

    public PinakaAdmin_ViewAccRVAdapter(ArrayList<AdminHolder> adminHolderArrayList, Context context)
    {
        this.adminHolderArrayList = adminHolderArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public PinakaAdmin_ViewAccRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_adminaccounts, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PinakaAdmin_ViewAccRVAdapter.ViewHolder holder, int position) {
        AdminHolder adminHolder = adminHolderArrayList.get(position);
        holder.adminCityDept.setText(adminHolder.getAdminCity() + " " + adminHolder.getAdminDeptAbv());
        holder.adminDept.setText(adminHolder.getAdminDept());
        holder.adminNameTV.setText(adminHolder.getAdminName());

    }

    @Override
    public int getItemCount() {
        return adminHolderArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView adminCityDept;
        private final TextView adminDept;
        private final TextView adminNameTV;

        public ViewHolder(View itemView) {
            super(itemView);

            adminCityDept = itemView.findViewById(R.id.tvDeptAbbv);
            adminDept = itemView.findViewById(R.id.tvDeptName);
            adminNameTV = itemView.findViewById(R.id.tvAdminName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AdminHolder adminHolder = adminHolderArrayList.get(getAdapterPosition());
                    Intent intent = new Intent(context, PinakaAdmin_AccountInfo.class);
                    intent.putExtra("admin", adminHolder);
                    context.startActivity(intent);
                }
            });
        }
    }
}

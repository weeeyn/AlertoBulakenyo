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

public class Admin_AnnouncementRVAdapter extends RecyclerView.Adapter<Admin_AnnouncementRVAdapter.ViewHolder> {

    private ArrayList<Announcements> announcementsArrayList;
    private Context context;

    public Admin_AnnouncementRVAdapter(ArrayList<Announcements> announcementsArrayList, Context context) {
        this.announcementsArrayList = announcementsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Admin_AnnouncementRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_ancmt, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Admin_AnnouncementRVAdapter.ViewHolder holder, int position) {
        Announcements announcements = announcementsArrayList.get(position);
        holder.anncmntTvTitle.setText(announcements.getAnncmntTitle());
        holder.anncmntTvDeptAbv.setText(announcements.getAnncmntDept());
        holder.anncmntTvDate.setText(announcements.getAnncmntDate());
    }

    @Override
    public int getItemCount() {
        return announcementsArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView anncmntTvTitle;
        private final TextView anncmntTvDeptAbv;
        private final TextView anncmntTvDate;

        public ViewHolder(View itemView) {
            super(itemView);

            anncmntTvTitle = itemView.findViewById(R.id.tvTitle);
            anncmntTvDeptAbv = itemView.findViewById(R.id.tvOffice);
            anncmntTvDate = itemView.findViewById(R.id.tvDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Announcements announcements = announcementsArrayList.get(getAdapterPosition());
                    Intent intent = new Intent(context, Admin_EditAncmnt.class);
                    intent.putExtra("announcements", announcements);
                    context.startActivity(intent);
                }
            });
        }
    }
}

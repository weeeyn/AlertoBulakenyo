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

public class AnnouncementRVAdapter extends RecyclerView.Adapter<AnnouncementRVAdapter.ViewHolder> {

    private ArrayList<Announcements> announcementArrayList;
    private Context context;

    public AnnouncementRVAdapter(ArrayList<Announcements> announcementArrayList, Context context) {
        this.announcementArrayList = announcementArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AnnouncementRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_ancmt, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementRVAdapter.ViewHolder holder, int position) {
        Announcements announcements = announcementArrayList.get(position);
        holder.anncmntTvTitle.setText(announcements.getAnncmntTitle());
        holder.anncmntTvDeptAbv.setText(announcements.getAnncmntDept());
        holder.anncmntTvDate.setText(announcements.getAnncmntDate());
    }

    @Override
    public int getItemCount() {
        return announcementArrayList.size();
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
                    Announcements announcements = announcementArrayList.get(getAdapterPosition());
                    Intent intent = new Intent(context, ancmntBody.class);
                    intent.putExtra("announcements", announcements);
                    context.startActivity(intent);
                }
            });
        }
    }
}

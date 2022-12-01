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

public class Admin_HotlinesRVAdapter extends RecyclerView.Adapter<Admin_HotlinesRVAdapter.ViewHolder> {

    private ArrayList<HotlinesHolder> hotlinesHolderArrayList;
    private Context context;

    public Admin_HotlinesRVAdapter(ArrayList<HotlinesHolder> hotlinesHolderArrayList, Context context) {
        this.hotlinesHolderArrayList = hotlinesHolderArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Admin_HotlinesRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.btn_hotlines, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Admin_HotlinesRVAdapter.ViewHolder holder, int position) {
        HotlinesHolder hotlinesHolder = hotlinesHolderArrayList.get(position);
        holder.hotlineName.setText(hotlinesHolder.getHotlineName());
    }

    @Override
    public int getItemCount() {
        return hotlinesHolderArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView hotlineName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            hotlineName = itemView.findViewById(R.id.tvHotName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HotlinesHolder hotlinesHolder = hotlinesHolderArrayList.get(getAdapterPosition());
                    Intent intent = new Intent(context, Admin_EditHotline.class);
                    intent.putExtra("hotline", hotlinesHolder);
                    context.startActivity(intent);
                }
            });
        }
    }
}

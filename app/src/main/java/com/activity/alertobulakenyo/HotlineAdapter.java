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

public class HotlineAdapter extends RecyclerView.Adapter<HotlineAdapter.ViewHolder> {

    private ArrayList<HotlinesHolder> hotlinesHolderArrayList;
    private Context context;

    public HotlineAdapter(ArrayList<HotlinesHolder> hotlinesHolderArrayList, Context context) {
        this.hotlinesHolderArrayList = hotlinesHolderArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public HotlineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HotlineAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.btn_hotlines, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HotlineAdapter.ViewHolder holder, int position) {
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
                    Intent intent = new Intent(context, ViewHotlines.class);
                    intent.putExtra("hotlineview", hotlinesHolder);
                    context.startActivity(intent);
                }
            });
        }
    }
}
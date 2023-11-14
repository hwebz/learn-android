package com.hado.secondweatherapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hado.secondweatherapp.Domains.FutureDomain;
import com.hado.secondweatherapp.R;

import java.util.ArrayList;

public class FutureAdapter extends RecyclerView.Adapter<FutureAdapter.viewholder> {
    ArrayList<FutureDomain> items;
    Context context;

    public FutureAdapter(ArrayList<FutureDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public FutureAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.viewholder_daily, parent, false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FutureAdapter.viewholder holder, int position) {
        holder.dayLabel.setText(items.get(position).getDay());
        holder.weatherLabel.setText(items.get(position).getStatus());
        holder.highTempLabel.setText(items.get(position).getHighTemp() + "°");
        holder.lowTempLabel.setText(items.get(position).getLowTemp() + "°");

        int drawableResourceId = holder.itemView.getResources()
                .getIdentifier(items.get(position).getPicPath(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.weatherIcon);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView dayLabel, weatherLabel, highTempLabel, lowTempLabel;
        ImageView weatherIcon;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            dayLabel = itemView.findViewById(R.id.dayLabel);
            weatherLabel = itemView.findViewById(R.id.weatherLabel);
            highTempLabel = itemView.findViewById(R.id.highTempLabel);
            lowTempLabel = itemView.findViewById(R.id.lowTempLabel);
            weatherIcon = itemView.findViewById(R.id.weatherIcon);
        }
    }
}

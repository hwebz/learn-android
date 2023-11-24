package com.hado.ecommercehealthyapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hado.ecommercehealthyapp.Activities.DetailActivity;
import com.hado.ecommercehealthyapp.Domains.BestDeal;
import com.hado.ecommercehealthyapp.R;

import java.util.ArrayList;

public class BestDealAdapter extends RecyclerView.Adapter<BestDealAdapter.ViewHolder> {
    ArrayList<BestDeal> bestDeals;
    Context context;

    public BestDealAdapter(ArrayList<BestDeal> bestDeals) {
        this.bestDeals = bestDeals;
    }

    @NonNull
    @Override
    public BestDealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.deal_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BestDealAdapter.ViewHolder holder, int position) {
        BestDeal selectedDeal = bestDeals.get(position);
        holder.titleTag.setText(selectedDeal.getTitle());
        holder.priceTag.setText(selectedDeal.getPrice() + "$/kg");

        int drawableResourceId = holder.itemView.getResources()
                .getIdentifier(selectedDeal.getImgPath(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.dealImage);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("selectedProduct", selectedDeal);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return bestDeals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTag;
        TextView priceTag;
        TextView addCartBtn;
        ImageView favButton;
        ImageView dealImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTag = itemView.findViewById(R.id.titleTag);
            priceTag = itemView.findViewById(R.id.priceTag);
            addCartBtn = itemView.findViewById(R.id.addCartBtn);
            favButton = itemView.findViewById(R.id.favBtn);
            dealImage = itemView.findViewById(R.id.dealImage);
        }
    }
}

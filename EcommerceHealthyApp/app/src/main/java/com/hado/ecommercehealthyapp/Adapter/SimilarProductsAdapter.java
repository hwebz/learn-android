package com.hado.ecommercehealthyapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hado.ecommercehealthyapp.Activities.DetailActivity;
import com.hado.ecommercehealthyapp.Domains.BestDeal;
import com.hado.ecommercehealthyapp.R;

import java.util.ArrayList;

public class SimilarProductsAdapter extends RecyclerView.Adapter<SimilarProductsAdapter.ViewHolder> {
    ArrayList<BestDeal> similarProducts;
    Context context;

    public SimilarProductsAdapter(ArrayList<BestDeal> similarProducts) {
        this.similarProducts = similarProducts;
    }

    @NonNull
    @Override
    public SimilarProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.similar_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarProductsAdapter.ViewHolder holder, int position) {
        BestDeal selectedProduct = similarProducts.get(position);
        int drawableResourceId = holder.itemView.getResources()
                .getIdentifier(selectedProduct.getImgPath(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.pic);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("selectedProduct", selectedProduct);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return similarProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pic = itemView.findViewById(R.id.productItem);
        }
    }
}

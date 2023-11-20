package com.hado.moviesapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hado.moviesapp.Domains.Genre;
import com.hado.moviesapp.Domains.Genres;
import com.hado.moviesapp.R;

import java.util.List;

public class DetailCategoryListAdapter extends RecyclerView.Adapter<DetailCategoryListAdapter.ViewHolder> {
    List<String> categories;
    Context context;

    public DetailCategoryListAdapter(List<String> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public DetailCategoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.genre_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailCategoryListAdapter.ViewHolder holder, int position) {
        String category = categories.get(position);
        holder.titleTxt.setText(category);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.catTxt);
        }
    }
}

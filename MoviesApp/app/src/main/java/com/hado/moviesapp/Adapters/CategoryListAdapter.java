package com.hado.moviesapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.hado.moviesapp.Activities.DetailActivity;
import com.hado.moviesapp.Domains.Film;
import com.hado.moviesapp.Domains.Films;
import com.hado.moviesapp.Domains.Genre;
import com.hado.moviesapp.Domains.Genres;
import com.hado.moviesapp.R;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {
    Genres genres;
    Context context;

    public CategoryListAdapter(Genres genres) {
        this.genres = genres;
    }

    @NonNull
    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.genre_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListAdapter.ViewHolder holder, int position) {
        Genre genre = genres.getGenres().get(position);
        holder.titleTxt.setText(genre.getName());

        holder.itemView.setOnClickListener(view -> {

        });
    }

    @Override
    public int getItemCount() {
        return genres.getGenres().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.catTxt);
        }
    }
}

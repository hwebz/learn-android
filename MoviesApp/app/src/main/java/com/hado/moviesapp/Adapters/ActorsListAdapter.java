package com.hado.moviesapp.Adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hado.moviesapp.R;

import java.util.List;

public class ActorsListAdapter extends RecyclerView.Adapter<ActorsListAdapter.ViewHolder> {
    List<String> actorsAvatar;
    Context context;

    public ActorsListAdapter(List<String> actorsAvatar) {
        this.actorsAvatar = actorsAvatar;
    }

    @NonNull
    @Override
    public ActorsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.actor_avatar, parent, false);
        return new ViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull ActorsListAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(actorsAvatar.get(position))
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return actorsAvatar.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.actorAvatar);
        }
    }
}

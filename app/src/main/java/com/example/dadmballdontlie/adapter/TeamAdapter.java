package com.example.dadmballdontlie.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dadmballdontlie.R;
import com.example.dadmballdontlie.data.model.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder>{

    private List<Team> listTeam;
    private HashMap<String, Drawable> imageTeam = new HashMap<>();
    private OnItemLongClickListener longClickListener;
    private OnItemClickListener onItemClickListener;

    public TeamAdapter(OnItemLongClickListener listener, OnItemClickListener onItemClickListener){
        listTeam = new ArrayList<>();
        longClickListener = listener;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_item,parent,false);
        TeamAdapter.ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.fullName.setText(listTeam.get(position).getFull_name());
        holder.name.setText(listTeam.get(position).getName());
        holder.imageView.setImageResource(listTeam.get(position).getTeamLogo());
    }

    @Override
    public int getItemCount() {
        return listTeam.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView fullName;
        public TextView name;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            fullName = itemView.findViewById(R.id.textViewTeamFullName);
            name = itemView.findViewById(R.id.textViewTeamName);
            imageView = itemView.findViewById(R.id.imageViewTeam);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    longClickListener.onItemLongClick(getTeam(getAdapterPosition()));
                    return false;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getTeam(getAdapterPosition()));
                }
            });
        }

    }

    public interface OnItemLongClickListener {
        void onItemLongClick(Team team);
    }

    public interface OnItemClickListener {
        void onItemClick(Team team);
    }

    public Team getTeam(int position){
        return listTeam.get(position);
    }

    public void updateList(List<Team> teams) {
        listTeam = teams;
        notifyDataSetChanged();
    }
}

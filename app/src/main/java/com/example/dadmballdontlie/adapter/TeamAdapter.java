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
    private HashMap<String, Drawable> imageTeam;

    public TeamAdapter(){
        listTeam = new ArrayList<>();
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
    }

    @Override
    public int getItemCount() {
        return listTeam.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView fullName;
        public TextView name;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            fullName = itemView.findViewById(R.id.textViewTeamFullName);
            name = itemView.findViewById(R.id.textViewTeamName);
        }

    }

    public void updateList(List<Team> teams) {
        listTeam = teams;
        notifyDataSetChanged();
    }
}

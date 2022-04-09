package com.example.dadmballdontlie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dadmballdontlie.R;
import com.example.dadmballdontlie.data.model.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder>{

    private List<Team> listTeam;
    private OnItemLongClickListener longClickListener;

    public TeamAdapter(OnItemLongClickListener listener){
        listTeam = new ArrayList<>();
        longClickListener = listener;
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
        holder.name.setText(listTeam.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return listTeam.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.textViewTeam);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    longClickListener.onItemLongClick(getTeam(getAdapterPosition()));
                    return false;
                }
            });
        }

    }

    public interface OnItemLongClickListener {
        void onItemLongClick(Team team);
    }

    public Team getTeam(int position){
        return listTeam.get(position);
    }

    public void updateList(List<Team> teams) {
        listTeam = teams;
        notifyDataSetChanged();
    }
}

package com.example.dadmballdontlie.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dadmballdontlie.R;
import com.example.dadmballdontlie.data.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder>{

    private List<Player> listPlayers;
    private OnItemClickListener onItemClickListener;

    public PlayerAdapter(OnItemClickListener onItemClickListener){
        listPlayers = new ArrayList<>();
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public PlayerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_item,parent,false);
        PlayerAdapter.ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(listPlayers.get(position).getFirst_name() + " " + listPlayers.get(position).getLast_name());
        holder.team.setText(listPlayers.get(position).getTeam().getFull_name());
    }

    @Override
    public int getItemCount() {
        return listPlayers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView team;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.textViewPlayer);
            team = itemView.findViewById(R.id.textViewPlayerTeam);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(listPlayers.get(getAdapterPosition()));
                    Log.i("DEBUG: ", getAdapterPosition()+"");
                }
            });
        }

    }

    public interface OnItemClickListener {
        void onItemClick(Player player);
    }

    public void updateList(List<Player> players) {
        listPlayers.clear();
        listPlayers.addAll(players);
        notifyDataSetChanged();
    }

}

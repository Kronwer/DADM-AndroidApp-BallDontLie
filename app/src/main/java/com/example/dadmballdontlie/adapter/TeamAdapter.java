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
        holder.fullName.setText(listTeam.get(position).getFull_name());
        holder.name.setText(listTeam.get(position).getName());
        holder.imageView.setImageDrawable(imageTeam.get(listTeam.get(position).getName()));
    }

    @Override
    public int getItemCount() {
        return listTeam.size();
    }

    public void loadTeamImages(Context context){
        for (Team team: listTeam) {
            switch (team.getName()){
                case "Bucks": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_bucks)); break;
                case "Bulls": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_bulls)); break;
                case "Cavaliers": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_cavaliers)); break;
                case "Celtics": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_celtics)); break;
                case "Clippers": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_clippers)); break;
                case "Grizzlies": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_grizzlies)); break;
                case "Hawks": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_hawks)); break;
                case "Heat": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_heat)); break;
                case "Hornets": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_hornets)); break;
                case "Jazz": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_jazz)); break;
                case "Kings": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_kings)); break;
                case "Knicks": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_knicks)); break;
                case "Lakers": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_lakers)); break;
                case "Magic": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_magic)); break;
                case "Mavericks": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_maverick)); break;
                case "Nets": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_nets)); break;
                case "Nuggets": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_nuggets)); break;
                case "Pacers": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_pacers)); break;
                case "Pelicans": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_pelicans)); break;
                case "Pistons": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_pistons)); break;
                case "Raptors": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_raptors)); break;
                case "Rockets": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_rockets)); break;
                case "76ers": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_sixers)); break;
                case "Spurs": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_spurs)); break;
                case "Suns": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_suns)); break;
                case "Thunder": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_thunders)); break;
                case "Timberwolves": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_timberwolves)); break;
                case "Trail Blazers": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_trailblazers)); break;
                case "Warriors": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_warriors)); break;
                case "Wizards": imageTeam.put(team.getName(), context.getDrawable(R.drawable.ic_wizards)); break;
            }
        }
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

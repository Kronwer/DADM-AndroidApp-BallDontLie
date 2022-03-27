package com.example.dadmballdontlie.data.local;

import com.example.dadmballdontlie.data.model.Player;
import com.example.dadmballdontlie.data.model.Team;

public interface NbaLocalDataSource {

    void addPlayer(Player player);

    void addTeam(Team team);

    void removePlayer(Player player);

    void removeTeam(Team team);

}

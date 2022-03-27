package com.example.dadmballdontlie.data.local;

import com.example.dadmballdontlie.data.model.Player;
import com.example.dadmballdontlie.data.model.Team;

import java.util.List;

public interface NbaLocalDataSource {

    void addPlayer(Player player);

    void addTeam(Team team);

    void removePlayer(Player player);

    void removeTeam(Team team);

    List<Player> getAllPlayers();

    List<Team> getAllTeams();

    void removePlayers();

    void removeTeams();

    Player getPlayerWithId(Integer id);

    Team getTeamWithId(Integer id);

}

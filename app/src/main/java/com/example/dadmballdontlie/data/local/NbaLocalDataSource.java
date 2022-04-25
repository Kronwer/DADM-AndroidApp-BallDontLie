package com.example.dadmballdontlie.data.local;

import androidx.lifecycle.LiveData;

import com.example.dadmballdontlie.data.model.Player;
import com.example.dadmballdontlie.data.model.Team;

import java.util.List;

public interface NbaLocalDataSource {

    void addPlayer(Player player);

    void updatePlayer(Player player);

    void updateTeam(Team team);

    void addTeam(Team team);

    void removePlayer(Player player);

    void removeTeam(Team team);

    LiveData<List<Player>> getAllPlayers();

    LiveData<List<Team>> getAllTeams();

    LiveData<List<Player>> getAllFavsPlayers();

    LiveData<List<Team>> getAllFavsTeams();

    void removePlayers();

    void removeTeams();

    LiveData<Player> getPlayerWithId(Integer id);

    LiveData<Team> getTeamWithId(Integer id);

}

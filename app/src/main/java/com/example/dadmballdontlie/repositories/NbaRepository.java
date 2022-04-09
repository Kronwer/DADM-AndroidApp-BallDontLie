package com.example.dadmballdontlie.repositories;

import androidx.lifecycle.LiveData;

import com.example.dadmballdontlie.data.model.Player;
import com.example.dadmballdontlie.data.model.Team;

import java.util.List;

public interface NbaRepository {

    void addPlayer(Player player);

    void addTeam(Team team);

    void removePlayer(Player player);

    void removeTeam(Team team);

    LiveData<List<Player>> getAllPlayers();

    LiveData<List<Team>> getAllTeams();

    void removePlayers();

    void removeTeams();

    LiveData<Player> getPlayerWithId(Integer id);

    LiveData<Team> getTeamWithId(Integer id);

}

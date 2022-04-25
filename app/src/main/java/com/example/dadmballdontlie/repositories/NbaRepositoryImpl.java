package com.example.dadmballdontlie.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.dadmballdontlie.data.local.NbaLocalDataSource;
import com.example.dadmballdontlie.data.local.NbaLocalDataSourceImpl;
import com.example.dadmballdontlie.data.model.Player;
import com.example.dadmballdontlie.data.model.Team;

import java.util.List;

public class NbaRepositoryImpl implements NbaRepository {

    private NbaLocalDataSource dataSource;

    public NbaRepositoryImpl(Context context) {
        dataSource = new NbaLocalDataSourceImpl(context);
    }

    @Override
    public void addPlayer(Player player) {
        dataSource.addPlayer(player);
    }

    @Override
    public void updatePlayer(Player player) {
        dataSource.updatePlayer(player);
    }

    @Override
    public void updateTeam(Team team) {
        dataSource.updateTeam(team);
    }

    @Override
    public void addTeam(Team team) {
        dataSource.addTeam(team);
    }

    @Override
    public void removePlayer(Player player) {
        dataSource.removePlayer(player);
    }

    @Override
    public void removeTeam(Team team) {
        dataSource.removeTeam(team);
    }

    @Override
    public LiveData<List<Player>> getAllPlayers() {
        return dataSource.getAllPlayers();
    }

    @Override
    public LiveData<List<Team>> getAllTeams() {
        return dataSource.getAllTeams();
    }

    @Override
    public LiveData<List<Player>> getAllFavsPlayers() {
        return dataSource.getAllFavsPlayers();
    }

    @Override
    public LiveData<List<Team>> getAllFavsTeams() {
        return dataSource.getAllFavsTeams();
    }

    @Override
    public void removePlayers() {
        dataSource.removePlayers();
    }

    @Override
    public void removeTeams() {
        dataSource.removeTeams();
    }

    @Override
    public LiveData<Player> getPlayerWithId(Integer id) {
        return dataSource.getPlayerWithId(id);
    }

    @Override
    public LiveData<Team> getTeamWithId(Integer id) {
        return dataSource.getTeamWithId(id);
    }

}

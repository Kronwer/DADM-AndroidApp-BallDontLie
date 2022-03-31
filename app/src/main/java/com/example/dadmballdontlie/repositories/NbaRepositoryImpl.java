package com.example.dadmballdontlie.repositories;

import android.content.Context;

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
    public List<Player> getAllPlayers() {
        return dataSource.getAllPlayers();
    }

    @Override
    public List<Team> getAllTeams() {
        return dataSource.getAllTeams();
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
    public Player getPlayerWithId(Integer id) {
        return dataSource.getPlayerWithId(id);
    }

    @Override
    public Team getTeamWithId(Integer id) {
        return dataSource.getTeamWithId(id);
    }

}

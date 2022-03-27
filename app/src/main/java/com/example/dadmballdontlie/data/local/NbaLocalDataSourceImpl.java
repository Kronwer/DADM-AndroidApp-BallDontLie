package com.example.dadmballdontlie.data.local;

import android.content.Context;

import com.example.dadmballdontlie.data.model.Player;
import com.example.dadmballdontlie.data.model.Team;

import java.util.List;

public class NbaLocalDataSourceImpl implements NbaLocalDataSource {

    private NbaDAO nbaDAO;

    public NbaLocalDataSourceImpl(Context context) {
        this.nbaDAO = NbaRoomDatabase.getInstance(context).nbaDAO();
    }

    @Override
    public void addPlayer(Player player) {
        new Thread(() -> nbaDAO.insertPlayer(player)).start();
    }

    @Override
    public void addTeam(Team team) {

    }

    @Override
    public void removePlayer(Player player) {

    }

    @Override
    public void removeTeam(Team team) {

    }

    @Override
    public List<Player> getAllPlayers() {
        return null;
    }

    @Override
    public List<Team> getAllTeams() {
        return null;
    }

    @Override
    public void removePlayers() {

    }

    @Override
    public void removeTeams() {

    }

    @Override
    public Player getPlayerWithId(Integer id) {
        return null;
    }

    @Override
    public Team getTeamWithId(Integer id) {
        return null;
    }

}

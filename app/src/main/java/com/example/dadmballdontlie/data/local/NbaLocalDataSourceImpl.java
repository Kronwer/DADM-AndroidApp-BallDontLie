package com.example.dadmballdontlie.data.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

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
    public void updatePlayer(Player player) {
        new Thread(() -> nbaDAO.updatePlayer(player)).start();
    }

    @Override
    public void updateTeam(Team team) {
        new Thread(() -> nbaDAO.updateTeam(team)).start();
    }


    @Override
    public void addTeam(Team team) {
        new Thread(() -> nbaDAO.insertTeam(team)).start();
    }


    @Override
    public void removePlayer(Player player) {
        new Thread(() -> nbaDAO.deletePlayer(player)).start();
    }


    @Override
    public void removeTeam(Team team) {
        new Thread(() -> nbaDAO.deleteTeam(team)).start();
    }


    @Override
    public LiveData<List<Player>> getAllPlayers() {
        return nbaDAO.getPlayers();
    }

    @Override
    public LiveData<List<Team>> getAllTeams() {
        return nbaDAO.getTeams();
    }

    @Override
    public LiveData<List<Player>> getAllFavsPlayers() {
        return nbaDAO.getAllFavsPlayers();
    }

    @Override
    public LiveData<List<Team>> getAllFavsTeams() {
        return nbaDAO.getAllFavsTeams();
    }

    @Override
    public void removePlayers() {
        new Thread(() -> nbaDAO.deletePlayers()).start();
    }

    @Override
    public void removeTeams() {
        new Thread(() -> nbaDAO.deleteTeams());
    }

    @Override
    public LiveData<Player> getPlayerWithId(Integer id) {
        return nbaDAO.getPlayer(id);
    }

    @Override
    public LiveData<Team> getTeamWithId(Integer id) {
        return nbaDAO.getTeam(id);
    }

}

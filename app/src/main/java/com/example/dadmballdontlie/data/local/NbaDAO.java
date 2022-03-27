package com.example.dadmballdontlie.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dadmballdontlie.data.model.Player;
import com.example.dadmballdontlie.data.model.Team;

import java.util.List;

@Dao
public abstract class NbaDAO {

    @Insert
    public abstract void insertPlayer(Player player);

    @Insert
    public abstract void insertTeam(Team team);

    @Delete
    public abstract void deletePlayer(Player player);

    @Delete
    public abstract void deleteTeam(Team team);

    @Query("select * from player")
    public abstract List<Player> getPlayers();

    @Query("select * from team")
    public abstract List<Team> getTeams();

    @Query("delete from player")
    public abstract void deletePlayers();

    @Query("delete from team")
    public abstract void deleteTeams();

    @Query("select * from player where id = :idPlayer")
    public abstract Player getPlayer(Integer idPlayer);

    @Query("select * from team where id = :idTeam")
    public abstract Team getTeam(Integer idTeam);

}

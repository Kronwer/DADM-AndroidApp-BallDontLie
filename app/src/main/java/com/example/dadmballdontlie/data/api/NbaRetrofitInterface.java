package com.example.dadmballdontlie.data.api;

import com.example.dadmballdontlie.data.model.Data;
import com.example.dadmballdontlie.data.model.Player;
import com.example.dadmballdontlie.data.model.PlayersResponse;
import com.example.dadmballdontlie.data.model.Stat;
import com.example.dadmballdontlie.data.model.Team;
import com.example.dadmballdontlie.data.model.TeamsResponse;

import java.util.Properties;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NbaRetrofitInterface {

    @GET("teams/{ID}")
    Call<Team> getTeamById(@Path("ID") int id);

    @GET("players/{ID}")
    Call<Player> getPlayerById(@Path("ID") int id);

    @GET("season_averages")
    Call<Data> getPlayerStatFromCurrentSeason(@Query("player_ids[]") int id);
    
    @GET("players")
    Call<PlayersResponse> getAllPlayers(@Query("page") String page, @Query("per_page") String per_page);

    @GET("teams")
    Call<TeamsResponse> getAllTeams();

    @GET("players")
    Call<PlayersResponse> getSearchPlayer(@Query("page") String page, @Query("per_page") String per_page, @Query("search") String search);

}

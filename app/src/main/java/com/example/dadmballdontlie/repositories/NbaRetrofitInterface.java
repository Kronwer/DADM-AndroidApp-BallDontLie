package com.example.dadmballdontlie.repositories;

import com.example.dadmballdontlie.data.model.Player;
import com.example.dadmballdontlie.data.model.PlayersResponse;
import com.example.dadmballdontlie.data.model.Team;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NbaRetrofitInterface {

    @GET("teams/{ID}")
    Call<Team> getTeamById(@Path("ID") int id);

    @GET("players/{ID}")
    Call<Player> getPlayerById(@Path("ID") int id);

}
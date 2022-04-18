package com.example.dadmballdontlie.repositories;

import com.example.dadmballdontlie.data.model.Data;
import com.example.dadmballdontlie.data.model.PlayersResponse;
import com.example.dadmballdontlie.data.model.TeamsResponse;

public interface ApiRepositoryCallBack {

    void receivedAllPlayers(PlayersResponse playersResponse);

    void onFailedAllPlayers();

    void receivedAllTeams(TeamsResponse teamsResponse);

    void onFailedAllTeams();

    void receivedStat(Data data);

    void onFailedStat();

}

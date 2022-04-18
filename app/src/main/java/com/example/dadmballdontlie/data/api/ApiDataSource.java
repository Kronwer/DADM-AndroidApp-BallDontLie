package com.example.dadmballdontlie.data.api;

import com.example.dadmballdontlie.data.model.Player;
import com.example.dadmballdontlie.data.model.Stat;
import com.example.dadmballdontlie.repositories.ApiRepositoryCallBack;

public interface ApiDataSource {

    void getAllPlayers(ApiDataSourceCallBack apiDataSourceCallBack);

    void getAllTeams(ApiDataSourceCallBack apiDataSourceCallBack);

    void getSearchPlayer(ApiDataSourceCallBack apiDataSourceCallBack, String search);

    void getStatFromCurrentSeason(ApiDataSourceCallBack apiDataSourceCallBack, Player player);
}

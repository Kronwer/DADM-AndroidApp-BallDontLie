package com.example.dadmballdontlie.data.api;

public interface ApiDataSource {

    void getAllPlayers(ApiDataSourceCallBack apiDataSourceCallBack);

    void getAllTeams(ApiDataSourceCallBack apiDataSourceCallBack);

    void getSearchPlayer(ApiDataSourceCallBack apiDataSourceCallBack, String search);

}

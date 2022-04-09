package com.example.dadmballdontlie.repositories;

public interface ApiRepository {

    void getAllPlayers(ApiRepositoryCallBack apiRepositoryCallBack);

    void getAllTeams(ApiRepositoryCallBack apiRepositoryCallBack);

    void getSearchPlayer(ApiRepositoryCallBack apiRepositoryCallBack, String search);

}

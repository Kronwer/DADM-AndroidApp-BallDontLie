package com.example.dadmballdontlie.repositories;

import com.example.dadmballdontlie.data.model.Player;

public interface ApiRepository {

    void getAllPlayers(ApiRepositoryCallBack apiRepositoryCallBack);

    void getAllTeams(ApiRepositoryCallBack apiRepositoryCallBack);

    void getSearchPlayer(ApiRepositoryCallBack apiRepositoryCallBack, String search);

    void getStatFromCurrentSeason(ApiRepositoryCallBack apiRepositoryCallBack, Player player);

}

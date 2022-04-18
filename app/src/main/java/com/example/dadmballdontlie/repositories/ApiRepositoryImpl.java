package com.example.dadmballdontlie.repositories;

import com.example.dadmballdontlie.data.api.ApiDataSource;
import com.example.dadmballdontlie.data.api.ApiDataSourceCallBack;
import com.example.dadmballdontlie.data.api.ApiDataSourceImpl;
import com.example.dadmballdontlie.data.model.Data;
import com.example.dadmballdontlie.data.model.Player;
import com.example.dadmballdontlie.data.model.PlayersResponse;
import com.example.dadmballdontlie.data.model.TeamsResponse;

public class ApiRepositoryImpl implements ApiRepository {

    private ApiDataSource apiDataSource;

    private ApiRepositoryCallBack apiRepositoryCallBack;

    final ApiDataSourceCallBack apiDataSourceCallBack =  new ApiDataSourceCallBack() {
        @Override
        public void receivedAllPlayers(PlayersResponse playersResponse) {
            apiRepositoryCallBack.receivedAllPlayers(playersResponse);
        }

        @Override
        public void onFailedAllPlayers() {
            apiRepositoryCallBack.onFailedAllPlayers();
        }

        @Override
        public void receivedAllTeams(TeamsResponse teamsResponse) {
            apiRepositoryCallBack.receivedAllTeams(teamsResponse);
        }

        @Override
        public void onFailedAllTeams() {
            apiRepositoryCallBack.onFailedAllTeams();
        }

        @Override
        public void receivedStat(Data data) {
            apiRepositoryCallBack.receivedStat(data);
        }

        @Override
        public void onFailedStat() {
            apiRepositoryCallBack.onFailedStat();
        }
    };

    public ApiRepositoryImpl() {
        apiDataSource = new ApiDataSourceImpl();
    }

    @Override
    public void getAllPlayers(ApiRepositoryCallBack apiRepositoryCallBack) {
        this.apiRepositoryCallBack = apiRepositoryCallBack;
        apiDataSource.getAllPlayers(apiDataSourceCallBack);
    }

    @Override
    public void getAllTeams(ApiRepositoryCallBack apiRepositoryCallBack) {
        this.apiRepositoryCallBack = apiRepositoryCallBack;
        apiDataSource.getAllTeams(apiDataSourceCallBack);
    }

    @Override
    public void getSearchPlayer(ApiRepositoryCallBack apiRepositoryCallBack, String search) {
        this.apiRepositoryCallBack = apiRepositoryCallBack;
        apiDataSource.getSearchPlayer(apiDataSourceCallBack, search);
    }

    @Override
    public void getStatFromCurrentSeason(ApiRepositoryCallBack apiRepositoryCallBack, Player player) {
        this.apiRepositoryCallBack = apiRepositoryCallBack;
        apiDataSource.getStatFromCurrentSeason(apiDataSourceCallBack, player);
    }

}

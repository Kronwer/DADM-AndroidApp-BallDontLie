package com.example.dadmballdontlie.data.api;

import com.example.dadmballdontlie.data.model.PlayersResponse;
import com.example.dadmballdontlie.data.model.TeamsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiDataSourceImpl implements ApiDataSource {

    private NbaRetrofitInterface nbaRetrofitInterface;
    private Retrofit retrofit;

    public ApiDataSourceImpl() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.balldontlie.io/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        nbaRetrofitInterface = retrofit.create(NbaRetrofitInterface.class);
    }

    @Override
    public void getAllPlayers(ApiDataSourceCallBack apiDataSourceCallBack) {
        Call<PlayersResponse> call = nbaRetrofitInterface.getAllPlayers("0", "100");

        call.enqueue(new Callback<PlayersResponse>() {
            @Override
            public void onResponse(Call<PlayersResponse> call, Response<PlayersResponse> response) {
                apiDataSourceCallBack.receivedAllPlayers(response.body());
            }

            @Override
            public void onFailure(Call<PlayersResponse> call, Throwable t) {
                apiDataSourceCallBack.onFailedAllPlayers();
            }
        });
    }

    @Override
    public void getAllTeams(ApiDataSourceCallBack apiDataSourceCallBack) {
        Call<TeamsResponse> call = nbaRetrofitInterface.getAllTeams();

        call.enqueue(new Callback<TeamsResponse>() {
            @Override
            public void onResponse(Call<TeamsResponse> call, Response<TeamsResponse> response) {
                apiDataSourceCallBack.receivedAllTeams(response.body());
            }

            @Override
            public void onFailure(Call<TeamsResponse> call, Throwable t) {
                apiDataSourceCallBack.onFailedAllTeams();
            }
        });
    }

    @Override
    public void getSearchPlayer(ApiDataSourceCallBack apiDataSourceCallBack, String search) {
        Call<PlayersResponse> call = nbaRetrofitInterface.getSearchPlayer("0", "100", search);

        call. enqueue(new Callback<PlayersResponse>() {
            @Override
            public void onResponse(Call<PlayersResponse> call, Response<PlayersResponse> response) {
                apiDataSourceCallBack.receivedAllPlayers(response.body());
            }

            @Override
            public void onFailure(Call<PlayersResponse> call, Throwable t) {
                apiDataSourceCallBack.onFailedAllPlayers();
            }
        });
    }


}

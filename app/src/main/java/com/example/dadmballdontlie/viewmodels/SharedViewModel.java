package com.example.dadmballdontlie.viewmodels;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.dadmballdontlie.data.model.Data;
import com.example.dadmballdontlie.data.model.Player;
import com.example.dadmballdontlie.data.model.PlayersResponse;
import com.example.dadmballdontlie.data.model.Stat;
import com.example.dadmballdontlie.data.model.Team;
import com.example.dadmballdontlie.data.model.TeamsResponse;
import com.example.dadmballdontlie.repositories.ApiRepository;
import com.example.dadmballdontlie.repositories.ApiRepositoryCallBack;
import com.example.dadmballdontlie.repositories.ApiRepositoryImpl;
import com.example.dadmballdontlie.repositories.NbaRepository;
import com.example.dadmballdontlie.repositories.NbaRepositoryImpl;
import com.example.dadmballdontlie.data.api.NbaRetrofitInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SharedViewModel extends AndroidViewModel {

    private Retrofit retrofit;
    private NbaRetrofitInterface retrofitInterface;

    private NbaRepository repository;
    private ApiRepository apiRepository;

    public LiveData<List<Team>> listTeamLocal;

    public LiveData<List<Player>> listPlayerLocal;
    public MutableLiveData<List<Player>> listPlayerSearch;
    public MediatorLiveData<List<Player>> mediatorListPlayer;

    public LiveData<List<Player>> listFavsPlayers;
    public LiveData<List<Team>> listFavsTeams;

    public MutableLiveData<Stat> stat;

    final ApiRepositoryCallBack apiRepositoryCallBack = new ApiRepositoryCallBack() {
        @Override
        public void receivedAllPlayers(PlayersResponse playersResponse) {
            if(playersResponse!= null && playersResponse.getData() != null && playersResponse.getData().size() != 0){
                listPlayerSearch.setValue(playersResponse.getData());
            }
        }

        @Override
        public void onFailedAllPlayers() {
            Log.e("FAILURE: ", "COULDN'T LOAD PLAYERS");
        }

        @Override
        public void receivedAllTeams(TeamsResponse teamsResponse) {

        }

        @Override
        public void onFailedAllTeams() {

        }

        @Override
        public void receivedStat(Data data) {
            stat.setValue(data.getFirstStat());
        }

        @Override
        public void onFailedStat() {

        }
    };

    public SharedViewModel(Application application) {

        super(application);

        stat = new MutableLiveData<>();

        mediatorListPlayer = new MediatorLiveData<>();

        listPlayerSearch = new MutableLiveData<>();

        repository = new NbaRepositoryImpl(application);
        apiRepository = new ApiRepositoryImpl();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());

        if (sharedPreferences.getBoolean("start", true)) {
            loadPlayersAndTeams();
            sharedPreferences.edit().putBoolean("start", false).apply();
        }

        initLiveData();

        mediatorListPlayer.addSource(listPlayerLocal, new Observer<List<Player>>() {
            @Override
            public void onChanged(List<Player> players) {
                mediatorListPlayer.setValue(players);
            }
        });

    }

    private void loadPlayersAndTeams() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.balldontlie.io/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(NbaRetrofitInterface.class);

        getAllPlayers();
        getAllTeams();
    }

    private void initLiveData() {
        listTeamLocal = repository.getAllTeams();
        listPlayerLocal = repository.getAllPlayers();
        stat.setValue(new Stat());
        listFavsPlayers = repository.getAllFavsPlayers();
        listFavsTeams = repository.getAllFavsTeams();
    }

    public void getPlayersSearch(String search) {
        if (!search.equals("")) {
            mediatorListPlayer.removeSource(listPlayerLocal);
            mediatorListPlayer.removeSource(listPlayerSearch);
            apiRepository.getSearchPlayer(apiRepositoryCallBack, search);

            mediatorListPlayer.addSource(listPlayerSearch, new Observer<List<Player>>() {
                @Override
                public void onChanged(List<Player> players) {
                    mediatorListPlayer.setValue(players);
                }
            });
        } else {
            mediatorListPlayer.removeSource(listPlayerSearch);
            mediatorListPlayer.removeSource(listPlayerLocal);

            mediatorListPlayer.addSource(listPlayerLocal, new Observer<List<Player>>() {
                @Override
                public void onChanged(List<Player> players) {
                    mediatorListPlayer.setValue(players);
                }
            });
        }
    }

    public void getStatFromCurrentSeason(Player player){
        apiRepository.getStatFromCurrentSeason(apiRepositoryCallBack, player);
    }

    public void getAllPlayers() {
        Call<PlayersResponse> call = retrofitInterface.getAllPlayers("0", "100");

        call.enqueue(new Callback<PlayersResponse>() {
            @Override
            public void onResponse(Call<PlayersResponse> call, Response<PlayersResponse> response) {
                loadRemainingPlayers(response.body().getMeta().getTotal_pages());
                savePlayersDB(response.body().getData());
            }

            @Override
            public void onFailure(Call<PlayersResponse> call, Throwable t) {
                Toast.makeText(getApplication().getApplicationContext(), "Error retrieving players", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getAllTeams() {
        Call<TeamsResponse> call = retrofitInterface.getAllTeams();

        call.enqueue(new Callback<TeamsResponse>() {
            @Override
            public void onResponse(Call<TeamsResponse> call, Response<TeamsResponse> response) {
                saveTeamsDB(response.body().getData());
            }

            @Override
            public void onFailure(Call<TeamsResponse> call, Throwable t) {
                Toast.makeText(getApplication().getApplicationContext(), "Error retrieving teams", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadRemainingPlayers(int total_pages) {
        for (int i = 2; i <= total_pages; i++) {
            Call<PlayersResponse> call = retrofitInterface.getAllPlayers(i + "", "100");

            call.enqueue(new Callback<PlayersResponse>() {
                @Override
                public void onResponse(Call<PlayersResponse> call, Response<PlayersResponse> response) {
                    savePlayersDB(response.body().getData());
                }

                @Override
                public void onFailure(Call<PlayersResponse> call, Throwable t) {
                    Log.e("FAILURE", "Couldn't load players");
                }
            });
        }
    }

    private void savePlayersDB(List<Player> players) {
        for (Player player: players) {
            repository.addPlayer(player);
        }
    }

    private void saveTeamsDB(List<Team> teams) {
        for (Team team: teams) {
            repository.addTeam(team);
        }
    }

    public void savePlayerToFavourites(Player player) {
        player.setFavourite(true);
        repository.updatePlayer(player);
    }

    public void saveTeamToFavourites(Team team) {
        team.setFavourite(true);
        repository.updateTeam(team);
    }

    public void removePlayerFromFavourites(Player player) {
        player.setFavourite(false);
        repository.updatePlayer(player);
        listFavsPlayers = repository.getAllFavsPlayers();
    }

    public void removeTeamFromFavourites(Team team) {
        team.setFavourite(false);
        repository.updateTeam(team);
    }

    public List<Player> getPlayersFromTeam(int teamId) {

        List<Player> res = new ArrayList<>();

        for(Player player : listPlayerLocal.getValue()) {
            if(player.getTeam().getId() == teamId) {
                res.add(player);
            }
        }

        return res;
    }
  
}

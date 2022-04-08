package com.example.dadmballdontlie;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.dadmballdontlie.data.local.NbaRoomDatabase;
import com.example.dadmballdontlie.data.model.Data;
import com.example.dadmballdontlie.data.model.Player;
import com.example.dadmballdontlie.data.model.PlayersResponse;
import com.example.dadmballdontlie.data.model.Stat;
import com.example.dadmballdontlie.data.model.Team;
import com.example.dadmballdontlie.data.model.TeamsResponse;
import com.example.dadmballdontlie.repositories.NbaRepository;
import com.example.dadmballdontlie.repositories.NbaRepositoryImpl;
import com.example.dadmballdontlie.repositories.NbaRetrofitInterface;

import java.util.ArrayList;
import java.util.Properties;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SharedViewModel extends AndroidViewModel {

    private final MutableLiveData<String> mText;
    private Retrofit retrofit;
    private NbaRetrofitInterface retrofitInterface;
    private NbaRepository repository;

    public SharedViewModel(Application application) {

        super(application);

        mText = new MutableLiveData<>();
        mText.setValue("This text comes from SharedViewModel");

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.balldontlie.io/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(NbaRetrofitInterface.class);

        repository = new NbaRepositoryImpl(application);
    }

    public LiveData<String> getText() {
        return mText;
    }

    // EXAMPLE CODE
    public void getFirstTeam() {

        Call<Team> call = retrofitInterface.getTeamById(14);

        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                mText.setValue(response.body().getName());
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                mText.setValue("Error while retrieving team");
            }
        });

    }

    // EXAMPLE CODE
    public void getPlayerName() {

        Call<Player> call = retrofitInterface.getPlayerById(237);

        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                mText.setValue(response.body().getFirst_name());
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                mText.setValue("Error while retrieving player");
            }
        });

    }

    // EXAMPLE CODE
    public void getPlayerStatFromCurrentSeason() {

        Call<Data> call = retrofitInterface.getPlayerStatFromCurrentSeason(237);

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                mText.setValue(response.body().getFirstStat().getFreeThrowsPercentage() + "");
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                mText.setValue("Error while retrieving stats from a player");
                Toast.makeText(getApplication().getApplicationContext(), "Error while retrieving stats from a player", Toast.LENGTH_SHORT);
            }
        });
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
                    mText.setValue("Error while retrieving player");
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

}

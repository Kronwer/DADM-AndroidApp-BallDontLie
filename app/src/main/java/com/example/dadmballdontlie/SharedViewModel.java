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
import com.example.dadmballdontlie.data.model.Stat;
import com.example.dadmballdontlie.data.model.Team;
import com.example.dadmballdontlie.repositories.NbaRepository;
import com.example.dadmballdontlie.repositories.NbaRepositoryImpl;
import com.example.dadmballdontlie.repositories.NbaRetrofitInterface;

import java.util.ArrayList;
import java.util.Properties;

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
        getPlayerStatFromCurrentSeason();

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

}

package com.example.dadmballdontlie.data.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class Converters {

    @TypeConverter
    public static String fromTeamJson(Team team) {
        return new Gson().toJson(team);
    }

    @TypeConverter
    public static Team toTeam(String jsonTeam) {
        return new Gson().fromJson(jsonTeam, Team.class);
    }

}

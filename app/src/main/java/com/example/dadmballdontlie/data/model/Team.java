package com.example.dadmballdontlie.data.model;

import android.os.Bundle;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.dadmballdontlie.R;

@Entity(tableName = "team")
public class Team {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "abbreviation")
    private String abbreviation;

    @ColumnInfo(name = "city")
    private String city;

    @ColumnInfo(name = "conference")
    private String conference;

    //{"Northwest", "Southwest", "Southeast", "Atlantic", "Pacific", "Central"}
    @ColumnInfo(name = "division")
    private String division;

    @ColumnInfo(name = "full_name")
    private String full_name;

    @ColumnInfo(name = "name")
    private String name;

    public Team(int id, String abbreviation, String city,String conference,
                String division, String full_name, String name) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.city = city;
        this.conference = conference;
        this.division = division;
        this.full_name = full_name;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("abbreviation", abbreviation);
        bundle.putString("city", city);
        bundle.putString("conference", conference);
        bundle.putString("division", division);
        bundle.putString("full_name", full_name);
        bundle.putString("name", name);
        bundle.putInt("team_logo", getTeamLogo());
        return bundle;
    }

    public int getTeamLogo() {
        switch (name) {
            case "Bucks": return R.drawable.ic_bucks;
            case "Bulls": return R.drawable.ic_bulls;
            case "Cavaliers": return R.drawable.ic_cavaliers;
            case "Celtics": return R.drawable.ic_celtics;
            case "Clippers": return R.drawable.ic_clippers;
            case "Grizzlies": return R.drawable.ic_grizzlies;
            case "Hawks": return R.drawable.ic_hawks;
            case "Heat": return R.drawable.ic_heat;
            case "Hornets": return R.drawable.ic_hornets;
            case "Jazz": return R.drawable.ic_jazz;
            case "Kings": return R.drawable.ic_kings;
            case "Knicks": return R.drawable.ic_knicks;
            case "Lakers": return R.drawable.ic_lakers;
            case "Magic": return R.drawable.ic_magic;
            case "Mavericks": return R.drawable.ic_maverick;
            case "Nets": return R.drawable.ic_nets;
            case "Nuggets": return R.drawable.ic_nuggets;
            case "Pacers": return R.drawable.ic_pacers;
            case "Pelicans": return R.drawable.ic_pelicans;
            case "Pistons": return R.drawable.ic_pistons;
            case "Raptors": return R.drawable.ic_raptors;
            case "Rockets": return R.drawable.ic_rockets;
            case "76ers": return R.drawable.ic_sixers;
            case "Spurs": return R.drawable.ic_spurs;
            case "Suns": return R.drawable.ic_suns;
            case "Thunder": return R.drawable.ic_thunders;
            case "Timberwolves": return R.drawable.ic_timberwolves;
            case "Trail Blazers": return R.drawable.ic_trailblazers;
            case "Warriors": return R.drawable.ic_warriors;
            case "Wizards": return R.drawable.ic_wizards;
            default: return 0;
        }
    }
}

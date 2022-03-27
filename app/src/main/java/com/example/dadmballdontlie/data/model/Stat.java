package com.example.dadmballdontlie.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "stat")
public class Stat {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "games_played")
    private int gamesPlayed;

    @ColumnInfo(name = "player_id")
    private int playerId;

    @ColumnInfo(name = "season")
    private int season;

    @ColumnInfo(name = "min")
    private String minutesPlayed;

    @ColumnInfo(name = "fgm")
    private double pointMade;

    @ColumnInfo(name = "fga")
    private double pointAttempted;

    @ColumnInfo(name = "fg3m")
    private double PointMade3;

    @ColumnInfo(name = "fg3a")
    private double GetPointAttempted3;

    @ColumnInfo(name = "ftm")
    private double freeThrowsMade;

    @ColumnInfo(name = "fta")
    private double freeThrowsAttempted;

    @ColumnInfo(name = "oreb")
    private double offensiveRebounds;

    @ColumnInfo(name = "dreb")
    private double defensiveRebounds;

    @ColumnInfo(name = "reb")
    private double rebounds;

    @ColumnInfo(name = "ast")
    private double assists;

    @ColumnInfo(name = "stl")
    private double steals;

    @ColumnInfo(name = "blk")
    private double blocks;

    @ColumnInfo(name = "turnover")
    private double turnover;

    @ColumnInfo(name = "pf")
    private double personalFaults;

    @ColumnInfo(name = "pts")
    private double points;

    //Percentage base 1
    @ColumnInfo(name = "fg_pct")
    private double pointPercentage;

    //Percentage base 1
    @ColumnInfo(name = "fg3_pct")
    private double point3Percentage;

    //Percentage base 1
    @ColumnInfo(name = "ft_pct")
    private double freeThrowsPercentage;


    public int getId() {
        return id;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getSeason() {
        return season;
    }

    public String getMinutesPlayed() {
        return minutesPlayed;
    }

    public double getPointMade() {
        return pointMade;
    }

    public double getPointAttempted() {
        return pointAttempted;
    }

    public double getPointMade3() {
        return PointMade3;
    }

    public double getGetPointAttempted3() {
        return GetPointAttempted3;
    }

    public double getFreeThrowsMade() {
        return freeThrowsMade;
    }

    public double getFreeThrowsAttempted() {
        return freeThrowsAttempted;
    }

    public double getOffensiveRebounds() {
        return offensiveRebounds;
    }

    public double getDefensiveRebounds() {
        return defensiveRebounds;
    }

    public double getRebounds() {
        return rebounds;
    }

    public double getAssists() {
        return assists;
    }

    public double getSteals() {
        return steals;
    }

    public double getBlocks() {
        return blocks;
    }

    public double getTurnover() {
        return turnover;
    }

    public double getPersonalFaults() {
        return personalFaults;
    }

    public double getPoints() {
        return points;
    }

    public double getPointPercentage() {
        return pointPercentage*100;
    }

    public double getPoint3Percentage() {
        return point3Percentage*100;
    }

    public double getFreeThrowsPercentage() {
        return freeThrowsPercentage*100;
    }
}

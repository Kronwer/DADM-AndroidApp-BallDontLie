package com.example.dadmballdontlie.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "stat")
public class Stat {

    @ColumnInfo(name = "player_id")
    @PrimaryKey
    @SerializedName("player_id")
    private int playerId;

    @ColumnInfo(name = "games_played")
    @SerializedName("games_played")
    private int gamesPlayed;

    @ColumnInfo(name = "season")
    private int season;

    @ColumnInfo(name = "min")
    @SerializedName("min")
    private String minutes;

    @ColumnInfo(name = "fgm")
    @SerializedName("fgm")
    private double pointsMade;

    @ColumnInfo(name = "fga")
    @SerializedName("fga")
    private double pointsAttempted;

    @ColumnInfo(name = "fg3m")
    @SerializedName("fg3m")
    private double points3Made;

    @ColumnInfo(name = "fg3a")
    @SerializedName("fg3a")
    private double points3Attempted;

    @ColumnInfo(name = "ftm")
    @SerializedName("ftm")
    private double freeThrowsMade;

    @ColumnInfo(name = "fta")
    @SerializedName("fta")
    private double freeThrowsAttempted;

    @ColumnInfo(name = "oreb")
    @SerializedName("oreb")
    private double offensiveRebounds;

    @ColumnInfo(name = "dreb")
    @SerializedName("dreb")
    private double defensiveRebounds;

    @ColumnInfo(name = "reb")
    @SerializedName("reb")
    private double rebounds;

    @ColumnInfo(name = "ast")
    @SerializedName("ast")
    private double assists;

    @ColumnInfo(name = "stl")
    @SerializedName("stl")
    private double steals;

    @ColumnInfo(name = "blk")
    @SerializedName("blk")
    private double blocks;

    @ColumnInfo(name = "turnover")
    private double turnover;

    @ColumnInfo(name = "pf")
    @SerializedName("pf")
    private double personalFaults;

    @ColumnInfo(name = "pts")
    @SerializedName("pts")
    private double points;

    //Percentage base 1
    @ColumnInfo(name = "fg_pct")
    @SerializedName("fc_pct")
    private double pointsPercentage;

    //Percentage base 1
    @ColumnInfo(name = "fg3_pct")
    @SerializedName("fg3_pct")
    private double points3Percentage;

    //Percentage base 1
    @ColumnInfo(name = "ft_pct")
    @SerializedName("ft_pct")
    private double freeThrowsPercentage;

    public Stat(int playerId, int gamesPlayed, int season, String minutes, double pointsMade, double pointsAttempted,
                double points3Made, double points3Attempted, double freeThrowsMade, double freeThrowsAttempted, double offensiveRebounds,
                double defensiveRebounds, double rebounds, double assists, double steals, double blocks, double turnover, double personalFaults,
                double points, double pointsPercentage, double points3Percentage, double freeThrowsPercentage) {
        this.playerId = playerId;
        this.gamesPlayed = gamesPlayed;
        this.season = season;
        this.minutes = minutes;
        this.pointsMade = pointsMade;
        this.pointsAttempted = pointsAttempted;
        this.points3Made = points3Made;
        this.points3Attempted = points3Attempted;
        this.freeThrowsMade = freeThrowsMade;
        this.freeThrowsAttempted = freeThrowsAttempted;
        this.offensiveRebounds = offensiveRebounds;
        this.defensiveRebounds = defensiveRebounds;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.turnover = turnover;
        this.personalFaults = personalFaults;
        this.points = points;
        this.pointsPercentage = pointsPercentage;
        this.points3Percentage = points3Percentage;
        this.freeThrowsPercentage = freeThrowsPercentage;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public double getPointsMade() {
        return pointsMade;
    }

    public void setPointsMade(double pointsMade) {
        this.pointsMade = pointsMade;
    }

    public double getPointsAttempted() {
        return pointsAttempted;
    }

    public void setPointsAttempted(double pointsAttempted) {
        this.pointsAttempted = pointsAttempted;
    }

    public double getPoints3Made() {
        return points3Made;
    }

    public void setPoints3Made(double points3Made) {
        this.points3Made = points3Made;
    }

    public double getPoints3Attempted() {
        return points3Attempted;
    }

    public void setPoints3Attempted(double points3Attempted) {
        this.points3Attempted = points3Attempted;
    }

    public double getFreeThrowsMade() {
        return freeThrowsMade;
    }

    public void setFreeThrowsMade(double freeThrowsMade) {
        this.freeThrowsMade = freeThrowsMade;
    }

    public double getFreeThrowsAttempted() {
        return freeThrowsAttempted;
    }

    public void setFreeThrowsAttempted(double freeThrowsAttempted) {
        this.freeThrowsAttempted = freeThrowsAttempted;
    }

    public double getOffensiveRebounds() {
        return offensiveRebounds;
    }

    public void setOffensiveRebounds(double offensiveRebounds) {
        this.offensiveRebounds = offensiveRebounds;
    }

    public double getDefensiveRebounds() {
        return defensiveRebounds;
    }

    public void setDefensiveRebounds(double defensiveRebounds) {
        this.defensiveRebounds = defensiveRebounds;
    }

    public double getRebounds() {
        return rebounds;
    }

    public void setRebounds(double rebounds) {
        this.rebounds = rebounds;
    }

    public double getAssists() {
        return assists;
    }

    public void setAssists(double assists) {
        this.assists = assists;
    }

    public double getSteals() {
        return steals;
    }

    public void setSteals(double steals) {
        this.steals = steals;
    }

    public double getBlocks() {
        return blocks;
    }

    public void setBlocks(double blocks) {
        this.blocks = blocks;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public double getPersonalFaults() {
        return personalFaults;
    }

    public void setPersonalFaults(double personalFaults) {
        this.personalFaults = personalFaults;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public double getPointsPercentage() {
        return pointsPercentage;
    }

    public void setPointsPercentage(double pointsPercentage) {
        this.pointsPercentage = pointsPercentage;
    }

    public double getPoints3Percentage() {
        return points3Percentage;
    }

    public void setPoints3Percentage(double points3Percentage) {
        this.points3Percentage = points3Percentage;
    }

    public double getFreeThrowsPercentage() {
        return freeThrowsPercentage;
    }

    public void setFreeThrowsPercentage(double freeThrowsPercentage) {
        this.freeThrowsPercentage = freeThrowsPercentage;
    }
}

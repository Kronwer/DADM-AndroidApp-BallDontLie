package com.example.dadmballdontlie.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "player")
public class Player {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "first_name")
    private String first_name;

    @ColumnInfo(name = "last_name")
    private String last_name;

    @ColumnInfo(name = "position")
    private String position;

    @ColumnInfo(name = "height_feet")
    private int height_feet;

    @ColumnInfo(name = "height_inches")
    private int height_inches;

    @ColumnInfo(name = "weight_pounds")
    private int weight_pounds;

    @ColumnInfo(name = "team")
    private Team team;

    public Player(int id, String first_name, String last_name, String position, int height_feet,
                  int height_inches, int weight_pounds, Team team) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.position = position;
        this.height_feet = height_feet;
        this.height_inches = height_inches;
        this.weight_pounds = weight_pounds;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getHeight_feet() {
        return height_feet;
    }

    public void setHeight_feet(int height_feet) {
        this.height_feet = height_feet;
    }

    public int getHeight_inches() {
        return height_inches;
    }

    public void setHeight_inches(int height_inches) {
        this.height_inches = height_inches;
    }

    public int getWeight_pounds() {
        return weight_pounds;
    }

    public void setWeight_pounds(int weight_pounds) {
        this.weight_pounds = weight_pounds;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

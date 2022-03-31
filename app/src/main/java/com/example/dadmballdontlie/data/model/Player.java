package com.example.dadmballdontlie.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "player")
public class Player {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "position")
    private String position;

    @ColumnInfo(name = "height_feet")
    private int heightFeet;

    @ColumnInfo(name = "height_inches")
    private int heightInches;

    @ColumnInfo(name = "weight_pounds")
    private int weightPounds;

    @ColumnInfo(name = "team")
    private Team team;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public int getHeightFeet() {
        return heightFeet;
    }

    public int getHeightInches() {
        return heightInches;
    }

    public int getWeightPounds() {
        return weightPounds;
    }

    public Team getTeam() {
        return team;
    }
}

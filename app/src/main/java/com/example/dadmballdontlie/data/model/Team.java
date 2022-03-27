package com.example.dadmballdontlie.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.dadmballdontlie.data.utils.Conference;
import com.example.dadmballdontlie.data.utils.Division;

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
    private Conference conference;

    @ColumnInfo(name = "division")
    private Division division;

    @ColumnInfo(name = "full_name")
    private String fullName;

    @ColumnInfo(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getCity() {
        return city;
    }

    public Conference getConference() {
        return conference;
    }

    public Division getDivision() {
        return division;
    }

    public String getFullName() {
        return fullName;
    }

    public String getName() {
        return name;
    }
}

package com.example.dadmballdontlie.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "team")
public class Team {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "abbreviation")
    private String abbreviation;

    @ColumnInfo(name = "city")
    private String city;

    //{"West","East"}
    @ColumnInfo(name = "conference")
    private String conference;

    //{"Northwest", "Southwest", "Southeast", "Atlantic", "Pacific", "Central"}
    @ColumnInfo(name = "division")
    private String division;

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

    public String getConference() {
        return conference;
    }

    public String getDivision() {
        return division;
    }

    public String getFullName() {
        return fullName;
    }

    public String getName() {
        return name;
    }
}

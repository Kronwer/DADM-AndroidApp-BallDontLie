package com.example.dadmballdontlie.data.utils;

import android.content.res.Resources;

import com.example.dadmballdontlie.R;

public enum Division {
    NORTHWEST("NW"),
    SOUTHWEST("SW"),
    SOUTHEAST("SE"),
    ATLANTIC("AT"),
    PACIFIC("PA"),
    CENTRAL("CE");

    private String divisionName;

    private Division (String name){
        if (name.equals("NW")) divisionName = Resources.getSystem().getString(R.string.division_NW);
        if (name.equals("SW")) divisionName = Resources.getSystem().getString(R.string.division_SW);
        if (name.equals("SE")) divisionName = Resources.getSystem().getString(R.string.division_SE);
        if (name.equals("AT")) divisionName = Resources.getSystem().getString(R.string.division_AT);
        if (name.equals("PA")) divisionName = Resources.getSystem().getString(R.string.division_PA);
        if (name.equals("CE")) divisionName = Resources.getSystem().getString(R.string.division_CE);
    }

    public String getDivisionName() {
        return divisionName;
    }
}

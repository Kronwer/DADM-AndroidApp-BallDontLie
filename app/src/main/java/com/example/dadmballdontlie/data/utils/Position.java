package com.example.dadmballdontlie.data.utils;

import android.content.res.Resources;

import com.example.dadmballdontlie.R;

public enum Position {
    C("C"),
    F("F"),
    G("G");

    private String positionName;

    private Position(String position){
        if (position.equals("C")) positionName = Resources.getSystem().getString(R.string.position_C);
        if (position.equals("F")) positionName = Resources.getSystem().getString(R.string.position_F);
        if (position.equals("G")) positionName = Resources.getSystem().getString(R.string.position_G);
    }

    public String getPosition() {
        return positionName;
    }
}

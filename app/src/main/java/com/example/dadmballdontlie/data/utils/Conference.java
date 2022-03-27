package com.example.dadmballdontlie.data.utils;

import android.content.res.Resources;

import com.example.dadmballdontlie.R;

public enum Conference {
    EAST("E"),
    WEST("W");

    private String conferenceName;

    private Conference (String name){
        if (name.equals("E")) conferenceName = Resources.getSystem().getString(R.string.conference_East);
        if (name.equals("W")) conferenceName = Resources.getSystem().getString(R.string.conference_West);
    }

    public String getConferenceName() {
        return conferenceName;
    }
}

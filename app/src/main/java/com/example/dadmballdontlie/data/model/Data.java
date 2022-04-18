package com.example.dadmballdontlie.data.model;

import java.util.ArrayList;

// Necesario ya que la llamada a la api devuelve este objeto
public class Data {

    private ArrayList<Stat> data;

    public ArrayList<Stat> getData() {
        return data;
    }

    public void setData(ArrayList<Stat> data) {
        this.data = data;
    }

    public Stat getFirstStat(){
        if (data.size() > 0) return data.get(0);
        else return new Stat();
    }
}

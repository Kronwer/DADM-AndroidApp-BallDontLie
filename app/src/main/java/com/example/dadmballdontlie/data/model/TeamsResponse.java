package com.example.dadmballdontlie.data.model;

import java.util.List;

public class TeamsResponse {

    private List<Team> data;
    private Meta meta;

    public TeamsResponse(List<Team> data, Meta meta) {
        this.data = data;
        this.meta = meta;
    }

    public List<Team> getData() {
        return data;
    }

    public void setData(List<Team> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}

package com.example.dadmballdontlie.data.model;

import java.util.ArrayList;
import java.util.List;

public class PlayersResponse {
    private List<Player> players;
    private Meta meta;

    public PlayersResponse(List<Player> players, Meta meta) {
        this.players = players;
        this.meta = meta;
    }
}

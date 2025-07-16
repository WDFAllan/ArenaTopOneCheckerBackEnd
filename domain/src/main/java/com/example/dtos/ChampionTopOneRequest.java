package com.example.dtos;

import java.util.List;

public class ChampionTopOneRequest {
    private String puuid;
    private List<String> arenaMatchesIds;

    public ChampionTopOneRequest() {}

    // Getters et setters
    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public List<String> getArenaMatchesIds() {
        return arenaMatchesIds;
    }

    public void setArenaMatchesIds(List<String> arenaMatchesIds) {
        this.arenaMatchesIds = arenaMatchesIds;
    }
}

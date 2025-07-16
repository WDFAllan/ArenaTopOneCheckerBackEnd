package com.example.services;

import com.example.port.RiotPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RiotService {

    private final RiotPort riotPort;

    public RiotService(RiotPort riotPort) {
        this.riotPort = riotPort;
    }

    public String getSummonerPuuid(String summonerName) {
        return riotPort.getSummonerPuuid(summonerName);
    }

    public List<String> getArenaMatchesIds(String puuid){
        return riotPort.getArenaMatchesIds(puuid);
    }

    public List<String> getArenaLastMatchesIds(String puuid){return riotPort.getArenaLastMatchesIds(puuid);}

    public Set<Integer> getChampionTop1(String puuid,List<String> arenaMatchesIds){
        return riotPort.getChampionTop1(puuid,arenaMatchesIds);
    }

}

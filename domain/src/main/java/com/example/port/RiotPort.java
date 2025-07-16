package com.example.port;

import java.util.List;
import java.util.Set;

public interface RiotPort {

    String getSummonerPuuid(String summonerName);

    List<String> getArenaMatchesIds(String puuid);

    List<String> getArenaLastMatchesIds(String puuid);

    Set<Integer> getChampionTop1(String puuid,List<String> arenaMatchesIds);


}

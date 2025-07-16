package com.example.services;

import com.example.domain.Champion;
import com.example.port.ChampionPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampionService {

    private final ChampionPort championPort;

    public ChampionService(ChampionPort championPort) {this.championPort = championPort;}

    public List<Champion> getAllChampions() {
        return championPort.getAllChampions();
    }

}

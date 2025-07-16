package com.example.api.controller;

import com.example.dtos.ChampionTopOneRequest;
import com.example.services.RiotService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/arena")
public class RiotController {

    private RiotService riotService;

    public RiotController(RiotService riotService) {this.riotService = riotService;}

    @GetMapping(path = "/summoner")
    public String getSummonerPuuid(@RequestParam String summonerName) {
        return riotService.getSummonerPuuid(summonerName);
    }

    @GetMapping(path = "/matchIds")
    public List<String> getArenaMatchesIds(@RequestParam String puuid){
        return riotService.getArenaMatchesIds(puuid);
    }

    @GetMapping(path = "/lastMatchIds")
    public List<String> getArenaLastMatchesIds(@RequestParam String puuid){
        return riotService.getArenaLastMatchesIds(puuid);
    }

    @PostMapping(path = "/hasWonChampionList")
    public Set<Integer> getChampionTop1(@RequestBody ChampionTopOneRequest request) {
        return riotService.getChampionTop1(
            request.getPuuid(),
            request.getArenaMatchesIds()
        );
    }

}

package com.example.api.controller;

import com.example.domain.Champion;
import com.example.services.ChampionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/champions")
public class ChampionController {

    private ChampionService championService;

    public ChampionController(ChampionService championService) {
        this.championService = championService;
    }

    @GetMapping(path="/getAllChampions")
    public List<Champion> getAllChampions() {
        return championService.getAllChampions();
    }
}

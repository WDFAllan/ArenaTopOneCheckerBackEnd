package com.example.adapters;


import com.example.domain.Champion;
import com.example.port.ChampionPort;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ChampionAdapter implements ChampionPort {

    private final List<Champion> champions;

    public ChampionAdapter() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/champions.json");

            if (inputStream == null) {
                throw new IllegalStateException("Le fichier champions.json est introuvable !");
            }

            this.champions = mapper.readValue(inputStream, new TypeReference<List<Champion>>() {});
        } catch (Exception e) {
            // Très utile : trace complète de l'exception à l'exécution
            e.printStackTrace();
            throw new RuntimeException("Erreur lors du chargement de champions.json", e);
        }
    }

    @Override
    public List<Champion> getAllChampions() {
        return champions;
    }
}

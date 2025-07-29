package com.example.adapters;

import com.example.port.RiotPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Component
public class RiotAdapter implements RiotPort {

    @Value("${api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private HttpEntity<Void> buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", apiKey);
        return new HttpEntity<>(headers);
    }

    @Override
    public String getSummonerPuuid(String summonerName) {
        System.out.println(summonerName);
        String[] parts = summonerName.split("#");

        if (parts.length != 2) {
            throw new IllegalArgumentException("Format attendu: Nom#TAG (ex: DarkVoid#EUW) :" + summonerName);
        }

        String username = parts[0];
        String tag = parts[1];

        String url = "https://europe.api.riotgames.com/riot/account/v1/accounts/by-riot-id/"+ username +"/"+ tag;

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, buildHeaders(), Map.class);

        return response.getBody().get("puuid").toString();
    }

    @Override
    public List<String> getArenaMatchesIds(String puuid) {
        List<String> matchesId = new ArrayList<>();
        int start = 0;
        int count = 100;
        boolean hasMore = true;

        while (hasMore) {
            String url = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/"+puuid+"/ids?queue=1700&start="+start+"&count="+count;

            ResponseEntity<String[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    buildHeaders(),
                    String[].class
            );

            String[] matchIds = response.getBody();
            if (matchIds == null || matchIds.length == 0) {
                hasMore = false;
            }else{
                matchesId.addAll(Arrays.asList(matchIds));
                start+=count;
                hasMore = false;
            }
        }
        return matchesId;
    }

    @Override
    public List<String> getArenaLastMatchesIds(String puuid) {
        List<String> matchesId = new ArrayList<>();
        int start = 0;
        int count = 97;
        boolean hasMore = true;

        while (hasMore) {
            String url = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/"+puuid+"/ids?queue=1700&start="+start+"&count="+count;

            ResponseEntity<String[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    buildHeaders(),
                    String[].class
            );

            String[] matchIds = response.getBody();
            if (matchIds == null || matchIds.length == 0) {
                hasMore = false;
            }else{
                matchesId.addAll(Arrays.asList(matchIds));
                start+=count;
                hasMore = false;
            }
        }
        return matchesId;
    }

    @Override
    public Set<Integer> getChampionTop1(String puuid,List<String> matchesId) {
        Set<Integer> championTop1 = new HashSet<>();
        for(String matchId : matchesId) {
            String url = "https://europe.api.riotgames.com/lol/match/v5/matches/"+matchId;

            ResponseEntity<Map> response = restTemplate.exchange(
                url,HttpMethod.GET,buildHeaders(),Map.class
            );

            Map<String, Object> body = response.getBody();
            if (body == null) continue;

            Map<String,Object> info = (Map<String, Object>) body.get("info");
            List<Map<String,Object>> participants = (List<Map<String, Object>>) info.get("participants");

            for(Map<String,Object> participant : participants) {
                if(puuid.equals(participant.get("puuid"))) {
                    if((int) participant.get("placement")==1){
                        int champion = (int) participant.get("championId");
                        championTop1.add(champion);
                    }
                    break;
                }
            }

        }


        return championTop1;
    }


}

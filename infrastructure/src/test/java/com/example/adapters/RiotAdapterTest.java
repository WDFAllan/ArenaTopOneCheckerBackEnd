package com.example.adapters;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class RiotAdapterTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RiotAdapter riotAdapter;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        // On remplace le RestTemplate "final" de la classe par notre mock
        Field restTemplateField = RiotAdapter.class.getDeclaredField("restTemplate");
        restTemplateField.setAccessible(true);
        restTemplateField.set(riotAdapter, restTemplate);

        // On simule une clé API (normalement injectée par Spring)
        Field apiKeyField = RiotAdapter.class.getDeclaredField("apiKey");
        apiKeyField.setAccessible(true);
        apiKeyField.set(riotAdapter, "FAKE_KEY");
    }

    @Test
    void shouldReturnPuuidWhenSummonerNameIsValid() {
        Map<String, Object> body = Map.of("puuid", "PUUID_1234");
        ResponseEntity<Map> mockResponse = new ResponseEntity<>(body, HttpStatus.OK);

        when(restTemplate.exchange(
                contains("WDFItachi/PRF"), eq(HttpMethod.GET), any(HttpEntity.class), eq(Map.class)
        )).thenReturn(mockResponse);

        String result = riotAdapter.getSummonerPuuid("WDFItachi#PRF");

        assertThat(result).isEqualTo("PUUID_1234");

        verify(restTemplate).exchange(
                contains("WDFItachi/PRF"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(Map.class)
        );
    }



}

package com.github.aveplen.alpha.service;

import com.github.aveplen.alpha.AlphaApplicationTests;
import com.github.aveplen.alpha.apis.OpenExchangeRates;
import com.github.aveplen.alpha.model.OxrDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
class RateServiceTest extends AlphaApplicationTests {

    @MockBean
    private OpenExchangeRates oxrClient;

    @Autowired
    private RateService rateService;

    @Test
    void givenYesterdayRateIsLower_raising_shouldReturnTrue() throws Exception {
        Double todayRate = 0.2;
        Double yesterdayRate = 0.1;

        when(oxrClient.latestRates(anyString()))
                .thenReturn(new OxrDto.Latest.Response("USD", Map.of("RUB", todayRate)));
        when(oxrClient.historicalRates(anyString(), anyString()))
                .thenReturn(new OxrDto.Latest.Response("USD", Map.of("RUB", yesterdayRate)));

        Assertions.assertTrue(rateService.raising("RUB"));
    }

    @Test
    void givenYesterdayRateIsHigher_raising_shouldReturnFalse() throws Exception {
        Double todayRate = 0.1;
        Double yesterdayRate = 0.2;

        when(oxrClient.latestRates(anyString()))
                .thenReturn(new OxrDto.Latest.Response("USD", Map.of("RUB", todayRate)));
        when(oxrClient.historicalRates(anyString(), anyString()))
                .thenReturn(new OxrDto.Latest.Response("USD", Map.of("RUB", yesterdayRate)));

        Assertions.assertFalse(rateService.raising("RUB"));
    }

    @Test
    void givenYesterdayRateIsSame_raising_shouldReturnFalse() throws Exception {
        Double todayRate = 0.1;
        Double yesterdayRate = 0.1;

        when(oxrClient.latestRates(anyString()))
                .thenReturn(new OxrDto.Latest.Response("USD", Map.of("RUB", todayRate)));
        when(oxrClient.historicalRates(anyString(), anyString()))
                .thenReturn(new OxrDto.Latest.Response("USD", Map.of("RUB", yesterdayRate)));

        Assertions.assertFalse(rateService.raising("RUB"));
    }
}
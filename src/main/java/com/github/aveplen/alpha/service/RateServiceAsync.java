package com.github.aveplen.alpha.service;

import com.github.aveplen.alpha.apis.OpenExchangeRates;
import com.github.aveplen.alpha.model.OxrDto;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Log4j2
@Service
@Setter
@RequiredArgsConstructor
public class RateServiceAsync {
    @Value("${apis.oxr.app_id}")
    private String oxrAppId;

    private final OpenExchangeRates oxr;

    @Async
    public CompletableFuture<Double> todayRate(String currency) throws Exception {
       log.info("getting today rate");

        OxrDto.Latest.Response stat = oxr.latestRates(oxrAppId);
        Map<String, Double> rates = stat.getRates();

        if (!rates.containsKey(currency)) {
            throw new Exception("unknown currency");
        }

        return CompletableFuture.completedFuture(rates.get(currency));
    }

    @Async
    public CompletableFuture<Double> yesterdayRate(String currency) throws Exception {
        log.info("getting yesterday rate");

        OxrDto.Latest.Response stat = oxr.historicalRates(
                oxrAppId,
                LocalDate.now().minusDays(1).toString());

        Map<String, Double> rates = stat.getRates();

        if (!rates.containsKey(currency)) {
            throw new Exception("unknown currency");
        }

        return CompletableFuture.completedFuture(rates.get(currency));
    }
}

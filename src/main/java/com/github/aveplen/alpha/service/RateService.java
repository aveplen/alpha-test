package com.github.aveplen.alpha.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

// RateService используется для вызова методов todayRate и yesterdayRate сервиса
// RateServiceAsync потому что они асинхронные => проксированные
@Log4j2
@Service
@RequiredArgsConstructor
public class RateService {
    @Value("${apis.oxr.app_id}")
    private String oxrAppId;

    private final RateServiceAsync rateService;

    // методы на получение рейтов асинхронные, потому что друг от друга не зависят и могут
    // выполняться параллельно + это IO => должно быть асинхронным
    public Boolean raising(String currency) throws Exception {
        log.info("getting raising status");

        CompletableFuture<Double> todayRate = rateService.todayRate(currency);
        CompletableFuture<Double> yesterdayRate = rateService.yesterdayRate(currency);

        CompletableFuture.allOf(todayRate, yesterdayRate).join();

        return todayRate.get() > yesterdayRate.get();
    }
}

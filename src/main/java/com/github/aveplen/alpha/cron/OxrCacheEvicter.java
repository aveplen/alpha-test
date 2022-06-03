package com.github.aveplen.alpha.cron;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class OxrCacheEvicter {

    // Не знаю, во какое время обновляются рейты, поэтому обновляю каждый час.
    // Чищу руками, потому что не хочу тащить гуаву
    @Async
    @Scheduled(cron = "0 0 * * * *")
    @CacheEvict("rates")
    public void evictRatesCache() {
        log.info("evicting rates cache");
    }
}

package com.github.aveplen.alpha.apis;

import com.github.aveplen.alpha.model.OxrDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// кэш чистится раз в час
@FeignClient(value = "oxr", url = "${apis.oxr.path.base_url}")
public interface OpenExchangeRates {
    @Cacheable("rates")
    @RequestMapping(method = RequestMethod.GET, value = "${apis.oxr.path.latest_ep}", produces = "application/json")
    OxrDto.Latest.Response latestRates(@RequestParam("app_id") String appId);

    @Cacheable("rates")
    @RequestMapping(method = RequestMethod.GET, value = "${apis.oxr.path.historical_ep}", produces = "application/json")
    OxrDto.Latest.Response historicalRates(@RequestParam("app_id") String appId, @PathVariable String date);
}

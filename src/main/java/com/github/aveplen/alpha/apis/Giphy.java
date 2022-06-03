package com.github.aveplen.alpha.apis;

import com.github.aveplen.alpha.model.GiphyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "giphy", url = "${apis.giphy.path.base_url}")
public interface Giphy {
    @RequestMapping(method = RequestMethod.GET, value = "${apis.giphy.path.search_ep}", produces = "application/json")
    GiphyDto.Search.Response searchGifs(@RequestParam("api_key") String apiKey, @RequestParam("q") String q);
}

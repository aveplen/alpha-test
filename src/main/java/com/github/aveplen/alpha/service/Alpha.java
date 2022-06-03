package com.github.aveplen.alpha.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class Alpha {
    private final GifService gifService;
    private final RateService rateService;

    // получение ембед-ссылки на рандомный гиф
    public String rateGifEmbedUrl(String currency) throws Exception {
       log.info("fetching random gif embed url");

        if (rateService.raising(currency)) {
            return gifService.randomRichGif().getEmbedUrl();
        }

        return gifService.randomBrokeGif().getEmbedUrl();
    }
}

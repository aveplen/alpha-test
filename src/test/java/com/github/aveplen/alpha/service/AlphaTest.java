package com.github.aveplen.alpha.service;

import com.github.aveplen.alpha.AlphaApplicationTests;
import com.github.aveplen.alpha.model.Gif;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
class AlphaTest extends AlphaApplicationTests {
    @MockBean
    private GifService gifService;

    @MockBean
    private RateService rateService;

    @Autowired
    private Alpha alphaService;

    @Test
    void rateGifEmbedUrl_shouldGetRichIfRaising()  throws Exception {
        String currency = "RUB";
        String embedUrl = "url";

        when(rateService.raising(anyString()))
                .thenReturn(true);

        when(gifService.randomRichGif())
                .thenReturn(new Gif("123", "url", embedUrl));

        String url = alphaService.rateGifEmbedUrl(currency);

        Assertions.assertEquals(url, embedUrl);
    }

    @Test
    void rateGifEmbedUrl_shouldGetBrokeIfNotRaising()  throws Exception {
        String currency = "RUB";
        String embedUrl = "url";

        when(rateService.raising(anyString()))
                .thenReturn(false);

        when(gifService.randomBrokeGif())
                .thenReturn(new Gif("123", "url", embedUrl));

        String url = alphaService.rateGifEmbedUrl(currency);

        Assertions.assertEquals(url, embedUrl);
    }
}
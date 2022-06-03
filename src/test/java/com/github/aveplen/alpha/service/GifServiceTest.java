package com.github.aveplen.alpha.service;

import com.github.aveplen.alpha.AlphaApplicationTests;
import com.github.aveplen.alpha.apis.Giphy;
import com.github.aveplen.alpha.model.Gif;
import com.github.aveplen.alpha.model.GiphyDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
class GifServiceTest extends AlphaApplicationTests {
    @MockBean
    private Giphy giphyClient;

    @Autowired
    private GifService gifService;

    @Test
    void randomRichGif() {
        String embedUrl = "embedurl";

        when(giphyClient.searchGifs(anyString(), anyString()))
                .thenReturn(new GiphyDto.Search.Response(List.of(new Gif("123", "url", embedUrl))));

        Gif gif = gifService.randomRichGif();

        Assertions.assertEquals(embedUrl, gif.getEmbedUrl());
    }

    @Test
    void randomBrokeGif() {
        String embedUrl = "embedurl";

        when(giphyClient.searchGifs(anyString(), anyString()))
                .thenReturn(new GiphyDto.Search.Response(List.of(new Gif("123", "url", embedUrl))));

        Gif gif = gifService.randomBrokeGif();

        Assertions.assertEquals(embedUrl, gif.getEmbedUrl());
    }
}
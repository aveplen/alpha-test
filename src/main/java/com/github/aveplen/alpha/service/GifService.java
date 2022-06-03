package com.github.aveplen.alpha.service;

import com.github.aveplen.alpha.apis.Giphy;
import com.github.aveplen.alpha.model.Gif;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Log4j2
@Service
@RequiredArgsConstructor
public class GifService {
    @Getter
    @AllArgsConstructor
    private enum GiphyQueries {
        RICH("rich"),
        BROKE("broke");

        private final String query;
    }

    @Value("${apis.giphy.api_key}")
    private String giphyApiKey;

    private final Giphy giphy;

    // выбирает рандомную гифку с первой страницы результатов поиски (50 результатов)
    private Gif randomGif(String query) {
        List<Gif> gifs = giphy.searchGifs(giphyApiKey, query).getData();
        return new Random()
                .ints(1, 0, gifs.size())
                .mapToObj(gifs::get)
                .toList()
                .get(0);
    }

    public Gif randomRichGif() {
        log.info("fetching random rich gif");
        return randomGif(GiphyQueries.RICH.getQuery());
    }

    public Gif randomBrokeGif() {
        log.info("fetching random broke gif");
        return randomGif(GiphyQueries.BROKE.getQuery());
    }
}

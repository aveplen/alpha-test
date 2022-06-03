package com.github.aveplen.alpha.controller;

import com.github.aveplen.alpha.service.Alpha;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@RestController
@RequestMapping("/redirect")
@RequiredArgsConstructor
public class ApiController {
    private final Alpha alphaService;

    @Value("${currency}")
    private String defaultCurrency;

    @RequestMapping
    @GetMapping("/")
    public RedirectView getRandomGif(
            @RequestParam(value = "cur", required = false) String currency
    ) throws Exception {

        log.info("getRandomGif endpoint called");

        if (currency == null || currency.length() == 0) {
            currency = defaultCurrency;
        }
        return new RedirectView(alphaService.rateGifEmbedUrl(currency));
    }
}

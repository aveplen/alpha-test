package com.github.aveplen.alpha.controller;

import com.github.aveplen.alpha.AlphaApplicationTests;
import com.github.aveplen.alpha.service.Alpha;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc

class ApiControllerTest extends AlphaApplicationTests {
    @MockBean
    private Alpha alphaService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getRandomGif()  throws Exception {
        String redirectUrl = "url";

        when(alphaService.rateGifEmbedUrl(any()))
                .thenReturn(redirectUrl);

        mockMvc.perform(get("/redirect/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(redirectUrl));
    }
}
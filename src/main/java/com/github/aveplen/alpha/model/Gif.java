package com.github.aveplen.alpha.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Gif {
    private String id;

    private String url;

    @JsonProperty("embed_url")
    private String embedUrl;
}

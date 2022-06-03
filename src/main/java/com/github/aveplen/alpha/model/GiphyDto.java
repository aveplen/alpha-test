package com.github.aveplen.alpha.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

// enum-dto паттерн нужен, чтобы объявлять неймспейсы и обращаться к вложенным
// классам в таком стиле: GiphyDto.Search.Response
public enum GiphyDto {;
    private interface Data {List<Gif> getData();}

    public enum Search {;
        @lombok.Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Response implements GiphyDto.Data {
            List<Gif> data;
        }
    }
}

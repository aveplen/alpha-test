package com.github.aveplen.alpha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

// enum-dto паттерн нужен, чтобы объявлять неймспейсы и обращаться к вложенным
// классам в таком стиле: OxrDto.Latest.Response
public enum OxrDto {;
    private interface Base { String getBase();}
    private interface Rates { Map<String, Double> getRates();}

    public enum Latest {;
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Response implements Base, Rates {
            String base;
            Map<String, Double> rates;
        }
    }
}

package com.estacionamento.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CarroUtils {

    public static String gerarRecibo() {
        LocalDateTime dataAtual = LocalDateTime.now();
        String recibo = dataAtual.toString().substring(0, 19);
        return recibo.replace("-", "").replace(":", "")
                .replace("T", "-");
    }
}

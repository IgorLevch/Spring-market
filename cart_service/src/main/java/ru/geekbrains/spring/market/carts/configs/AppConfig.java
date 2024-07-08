package ru.geekbrains.spring.market.carts.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean             // данный Бин предназначен для отправки запросов по РЕСТу
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }


}

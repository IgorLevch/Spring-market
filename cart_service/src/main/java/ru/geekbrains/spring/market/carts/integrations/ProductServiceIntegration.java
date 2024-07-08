package ru.geekbrains.spring.market.carts.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.spring.market.api.ProductDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {

    // CartDto microservice спокойно работает с core microservice через данный слой

    private final RestTemplate restTemplate;

    public Optional<ProductDto> getProductById(Long id){

        // отправляем гет-запрос на получение объекта
        // указываем адрес(того, куда мы пошлем наш запрос) и класс ответа, который ожидаем получить.

        return Optional.ofNullable(restTemplate.getForObject(
                "http://localhost:8189/winter/api/v1/products/"+id,
                ProductDto.class));
    }



}

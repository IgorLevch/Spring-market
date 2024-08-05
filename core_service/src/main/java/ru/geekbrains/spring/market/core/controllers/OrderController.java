package ru.geekbrains.spring.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ru.geekbrains.spring.market.core.services.OrderService;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
//@CrossOrigin("*")  т.к. за это теперь отвечает Гейтвей -- комментим
public class OrderController {


    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username){
        // т.е. это означет, что если польз-ль хочет оформить заказ, он обязан прокинуть токен через гейтвей ,
        // а Гейтвей прокинет мне это в виде Хедера -- и я этот Хедер просто использую

        // если в параметрах не будет String username - то мы получим ошибку
        // (раньше для этого писали Principal principal -- если этого не было, выдавало ошибку )
        // т.е. это своеобразная защита для того, чтобы юзер был при оформлении заказа
        orderService.createOrder(username);


    }


}

package ru.geekbrains.spring.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.spring.spring.market.entities.Product;

import ru.geekbrains.spring.spring.market.services.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart") // в корзине пишет cart в енд-пойнте , в отличие от обычного адреса, кот-й во мн ч.
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

/*
    @GetMapping
    public List<Cart> findAllProducts(){   // или оставить Product ?

        return cartService.findAll();
    }



    @GetMapping("/add/{id}")
    public List<Product> findProductById(@PathVariable Long id){

        cartService.addtoDB(id);

        return cartService.findAll();

    }


    @GetMapping("/delete/{id}")
    void deleteById(@PathVariable Long id){

           cartService.deleteById(id);
    }
*/



}

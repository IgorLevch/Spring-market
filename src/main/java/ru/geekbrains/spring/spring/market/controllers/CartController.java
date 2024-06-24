package ru.geekbrains.spring.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.spring.market.dtos.Cart;
import ru.geekbrains.spring.spring.market.entities.Product;

import ru.geekbrains.spring.spring.market.services.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart") // в корзине пишет cart в енд-пойнте , в отличие от обычного адреса, кот-й во мн ч.
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id){
        cartService.add(id);
    }


    @GetMapping
    public Cart getCurrentCart(){
        return cartService.getCurrentCart();
    }


    @DeleteMapping("/delete")
    public void  delete(){

     cartService.delete();

    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id){

        cartService.deleteById(id);

    }

}

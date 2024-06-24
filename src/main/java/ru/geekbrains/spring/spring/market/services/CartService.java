package ru.geekbrains.spring.spring.market.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.spring.market.dtos.Cart;
import ru.geekbrains.spring.spring.market.entities.Product;
import ru.geekbrains.spring.spring.market.exceptions.ResourceNotFoundException;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;
    private  Cart tempCart;

        @PostConstruct
        public void init(){
            tempCart = new Cart();
        }

    public Cart getCurrentCart(){

        return tempCart;
    } // этот метод в будущем модифицируем и распишем в будущем, какую кому корзину будем выдавать


    public void add(Long productId){
         Product product = productService.findById(productId).orElseThrow(() ->
        new ResourceNotFoundException("We can't find a product with id: " + productId +
                "to a Cart.Product not found"));
        tempCart.add(product);
    }


    public void delete(){
            tempCart.deleteAll();
    }

    public void deleteById(Long id){

            tempCart.deleteById(id);
    }

}

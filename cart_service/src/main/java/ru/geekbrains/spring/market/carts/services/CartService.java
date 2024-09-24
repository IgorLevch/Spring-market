package ru.geekbrains.spring.market.carts.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.geekbrains.spring.market.api.ProductDto;
import ru.geekbrains.spring.market.api.ResourceNotFoundException;
import ru.geekbrains.spring.market.carts.integrations.ProductServiceIntegration;
import ru.geekbrains.spring.market.carts.model.Cart;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {


    private final ProductServiceIntegration productServiceIntegration;
    private Cart tempCart; //   корзина пока что у нас одна


        @PostConstruct
        public void init(){
            tempCart = new Cart();
        }

    public Cart getCurrentCart(){

        return tempCart;
    } // этот метод в будущем модифицируем и распишем в будущем, какую кому корзину будем выдавать


    public void add(Long productId){

         ProductDto product = productServiceIntegration.getProductById(productId);

        tempCart.add(product);
    }


    public void delete(){
            tempCart.deleteAll();
    }

    public void deleteById(Long id){

            tempCart.deleteById(id);
    }

}

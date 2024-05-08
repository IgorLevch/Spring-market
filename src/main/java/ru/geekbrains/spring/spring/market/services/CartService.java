package ru.geekbrains.spring.spring.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.spring.market.entities.Product;
import ru.geekbrains.spring.spring.market.repositories.Cart;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;
    private final Cart cart;


    public List<Product> findAll(){
        return cart.getProductList();
    }

    public void addtoDB(Long id){
        Product product = productService.findById(id).get();
        cart.addProduct(product);

    }

    public void deleteById(Long id){
        Product product = productService.findById(id).get();
        for (int i = 0; i < cart.getProductList().size(); i++) {
            if (cart.getProductList().get(i).equals(product))
                cart.deleteProduct(product);
        }

    }

}

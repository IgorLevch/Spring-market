package ru.geekbrains.spring.spring.market.dtos;

import lombok.Data;
import ru.geekbrains.spring.spring.market.entities.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {

    private List<CartItem> items;
    private int totalPrice;


    public List<CartItem> getItems(){
        return Collections.unmodifiableList(items);
    }
    public Cart() {
        this.items = new ArrayList<CartItem>();
    }
    // добавление продуктов в корзину:
    public void add(Product product){ // TODO доработать в ДЗ
        items.add(new CartItem(product.getId(), product.getTitle(), 1,product.getPrice(), product.getPrice()));
        recalculate();
    }

    private void recalculate() {
        totalPrice = 0;
        for (CartItem cartItem : items) {
            totalPrice += cartItem.getPrice();
        }
    }

    public void deleteAll(){
        items.clear();
    }


    public void deleteById(Long id){

        items.remove(id);
    }

}

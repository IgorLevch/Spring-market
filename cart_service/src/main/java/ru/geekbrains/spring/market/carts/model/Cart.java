package ru.geekbrains.spring.market.carts.model;

import lombok.Data;
import ru.geekbrains.spring.market.api.ProductDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {

    private List<CartItem> items;
    private BigDecimal totalPrice;  // общая стоимость корзины


    // это мы делаем запрет того, чтобы кто-то снаружи мог получить данный список и как-то подменить его:
    // и это метод поиска айтимов:
//    public List<CartItem>  getItems(){
//        return Collections.unmodifiableList(items);
//    }


    public Cart(){
        this.items = new ArrayList<>();

    }


    // добавление продуктов в корзину:
    public void add(ProductDto product){
        for (CartItem item : items){
            if (product.getId().equals(item.getProductId())) {
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }

    // приватный метод пересчета стоимости:
    private void recalculate(){
        totalPrice = BigDecimal.ZERO;

        for (CartItem cartItem : items){
            totalPrice =totalPrice.add(cartItem.getPrice()).setScale(2, RoundingMode.HALF_UP);

        }
    }
    public void deleteAll(){
        items.clear();
        totalPrice =BigDecimal.ZERO;
    }

    public void deleteById(Long id){

       if (items.removeIf(item ->item.getProductId().equals(id))){
           recalculate();
       }
    }



}

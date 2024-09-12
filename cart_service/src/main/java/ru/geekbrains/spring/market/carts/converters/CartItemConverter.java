package ru.geekbrains.spring.market.carts.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.spring.market.api.CartItemDto;
import ru.geekbrains.spring.market.carts.model.CartItem;

@Component
public class CartItemConverter {

    // это бин, который позволит получить КартАйтемДто из модели данных
    //это маппер КартАйтимов
    public CartItemDto entityToDto(CartItem cartItem){

        CartItemDto cartItemDto = new CartItemDto();

        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setProductTitle(cartItem.getProductTitle());
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setPricePerProduct(cartItem.getPricePerProduct());

        return cartItemDto;

    }


}

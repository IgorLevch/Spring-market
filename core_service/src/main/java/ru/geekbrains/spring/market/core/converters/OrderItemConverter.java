package ru.geekbrains.spring.market.core.converters;

import org.springframework.stereotype.Component;

import ru.geekbrains.spring.market.api.OrderItemDto;

import ru.geekbrains.spring.market.core.entities.OrderItem;

@Component
public class OrderItemConverter {

    // это бин, который позволит получить КартАйтемДто из модели данных
    //это маппер КартАйтимов
    public OrderItemDto entityToDto(OrderItem orderItem){

        OrderItemDto orderItemDto = new OrderItemDto();

        orderItemDto.setId(orderItem.getId());
        orderItemDto.setPrice(orderItem.getPrice());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setPricePerProduct(orderItem.getPricePerProduct());
        orderItemDto.setProductTitle(orderItem.getProduct().getTitle());

        return orderItemDto;

    }


}

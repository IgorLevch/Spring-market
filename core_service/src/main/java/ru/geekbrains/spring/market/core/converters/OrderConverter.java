package ru.geekbrains.spring.market.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.market.api.OrderDto;
import ru.geekbrains.spring.market.api.OrderItemDto;
import ru.geekbrains.spring.market.core.entities.Order;
import ru.geekbrains.spring.market.core.entities.OrderItem;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {

    private final OrderItemConverter orderitemConverter;

    // это бин, который позволит получить КартАйтемДто из модели данных
    //это маппер КартАйтимов
    public OrderDto entityToDto(Order order){

      OrderDto orderDto = new OrderDto();
      orderDto.setId(order.getId());
      orderDto.setAddress(order.getAddress());
      orderDto.setPhone(order.getPhone());
      orderDto.setTotalPrice(order.getTotalPrice());
      orderDto.setUsername(order.getUsername());
      orderDto.setItems(order.getItems().stream().map(orderitemConverter::entityToDto).collect(Collectors.toList()));

       return orderDto;

    }


}

package ru.geekbrains.spring.market.core.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.spring.market.api.CartDto;
import ru.geekbrains.spring.market.core.entities.Order;
import ru.geekbrains.spring.market.core.entities.OrderItem;

import ru.geekbrains.spring.market.core.integrations.CartServiceIntegration;
import ru.geekbrains.spring.market.core.repositories.OrderItemRepository;
import ru.geekbrains.spring.market.core.repositories.OrderRepository;



import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CartServiceIntegration cartServiceIntegration;


    @Transactional
    public Order createOrder(String username){

    CartDto cartDto = cartServiceIntegration.getCurrentCart();// получаем корзину из Карт МС
    Order order = new Order();
    order.setUsername(username);
    order.setTotalPrice(cartDto.getTotalPrice());
    order.setItems(cartDto.getItems().stream().map(
            cartItem -> new OrderItem(
                    productService.findById(cartItem.getProductId()).get(),
                    order,
                    cartItem.getQuantity(),
                    cartItem.getPricePerProduct(),
                    cartItem.getPrice()
            )


    ).collect(Collectors.toList()));


    orderRepository.save(order);
        cartServiceIntegration.clear();
        return order;


    }

}

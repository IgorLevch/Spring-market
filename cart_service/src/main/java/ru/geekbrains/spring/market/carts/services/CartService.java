package ru.geekbrains.spring.market.carts.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import ru.geekbrains.spring.market.api.ProductDto;
import ru.geekbrains.spring.market.api.ResourceNotFoundException;
import ru.geekbrains.spring.market.carts.integrations.ProductServiceIntegration;
import ru.geekbrains.spring.market.carts.model.Cart;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {


    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;


    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;
  //  private Map<String, Cart> carts; //ключ - айдишник корзины, значение - сама по себе корзина.


//        @PostConstruct
//        public void init(){
//            carts = new HashMap<>();
//        }

    public Cart getCurrentCart(String uuid){

            String targetUuid = cartPrefix + uuid;
            if (!redisTemplate.hasKey(targetUuid)){  // если у редисТемлпейт нет данного uuid
                redisTemplate.opsForValue().set(targetUuid, new Cart()); // делаем новую корзину,  если обратились за несуществующей
            }  //  opsForValue() --  это выполнить некую операцию над значением:
        // в нашем случае:  set(targetUuid, new Cart()); --- под ключем Uuid закинуть туда новую корзину.
        // корзина создается и через redisTemplate кидается в Redis.  ТАм срабатывает ДЖексон сериализатор и преобразует нашу корзину
        // в джейсон вариант

        return (Cart) redisTemplate.opsForValue().get(targetUuid);
    }


    public void add(String uuid, Long productId){

         ProductDto product = productServiceIntegration.getProductById(productId);
         execute(uuid, cart->cart.add(product));

         // было до execute():
//         Cart cart = getCurrentCart(uuid);
//         //getCurrentCart(uuid).add(product); // мы в корзину uuid положили продукт
//        cart.add(product); // это мы поменяли корзину на локальном уровне, но не записали все в Редис
//        redisTemplate.opsForValue().set(cartPrefix + uuid, cart); // это мы отправили корзину обратно в редис

    }


    public void delete(String uuid){
//        Cart cart = getCurrentCart(uuid);
//        cart.deleteAll();
//        redisTemplate.opsForValue().set(cartPrefix + uuid, cart);   // было до создания метода execute()
       // execute(uuid, cart -> cart.deleteAll());
        execute(uuid, Cart::deleteAll);  // или вот так - переписываем верхнюю строку

    }

    public void deleteById(String uuid, Long id){

            execute(uuid,cart ->cart.deleteById(id));

//            Cart cart = getCurrentCart(uuid);   // было до execute()
//            cart.deleteById(id);
//            redisTemplate.opsForValue().set(cartPrefix + uuid, cart);

    }

    // функциональный интерфейс Consumer из Stream API(нужно вполнить опредленное действие над определенной штуковиной )
    private void execute(String uuid, Consumer<Cart> operation){ //ожидаем получения Консьюмера , который обрабатывает корзину
        Cart cart = getCurrentCart(uuid);  //достаем корзину по ююиду
        operation.accept(cart);  //хотим выполнить операцию над этой корзиной
        redisTemplate.opsForValue().set(cartPrefix + uuid, cart);  //  когда операция выполнена, хотим эту корзину запихнуть обратно в редис


    }

}

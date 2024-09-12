package ru.geekbrains.spring.market.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.market.api.CartDto;
import ru.geekbrains.spring.market.api.StringResponse;
import ru.geekbrains.spring.market.carts.converters.CartConverter;
import ru.geekbrains.spring.market.carts.services.CartService;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/cart") // в корзине пишет cart в енд-пойнте , в отличие от обычного адреса, кот-й во мн ч.
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {


    // пока мы в микросервисах не настраиваем безопасность
//@CrossOrigin("http://localhost:3000")  -- это, например, если хотим, чтобы имели доступ к контроллеру только с порта 3000
//(например, мы знаем, что наш фронт развернут на порту 3000  -  и будем от него запросы обрабатывать)

    private final CartService cartService;
    private final CartConverter cartConverter;


    @GetMapping("/generate_uuid")  // фронт генерит для себя айдишники корзин
    public StringResponse generateUuid(){
                                            // это если если не приходит uuid с фронта:
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{uuid}/add/{id}")
    public void addToCart(@RequestHeader(name = "username", required = false) String username,  @PathVariable String uuid, @PathVariable Long id){

        String targetUuid = getCartUuid(username, uuid);
        cartService.add(targetUuid, id); // если приходят username и uuid корзины,то у username - приоритет выше.
    }


    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid){

        String targetUuid = getCartUuid(username, uuid);
        return cartConverter.entityToDto(cartService.getCurrentCart(targetUuid));
    }


    @GetMapping("/{uuid}/delete")
    public void  delete(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid){
        String targetUuid = getCartUuid(username, uuid);
     cartService.delete(targetUuid);

    }

    @GetMapping("/{uuid}/delete/{id}")
    public void deleteProductById(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid, @PathVariable Long id){

        String targetUuid = getCartUuid(username, uuid);
        cartService.deleteById(targetUuid, id);

    }

    // кастомный метод (из контрллера лучше убрать )
        private String getCartUuid(String username, String uuid){
            if (username != null){
                return username;
            } else {
                return uuid;
            }

        }
}

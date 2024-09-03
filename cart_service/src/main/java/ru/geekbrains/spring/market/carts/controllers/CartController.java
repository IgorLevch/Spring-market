package ru.geekbrains.spring.market.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.market.api.CartDto;
import ru.geekbrains.spring.market.carts.converters.CartConverter;
import ru.geekbrains.spring.market.carts.services.CartService;



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

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id){
        cartService.add(id);
    }


    @GetMapping
    public CartDto getCurrentCart(){
        return cartConverter.entityToDto(cartService.getCurrentCart());
    }


    @DeleteMapping("/delete")
    public void  delete(){

     cartService.delete();

    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id){

        cartService.deleteById(id);

    }

}

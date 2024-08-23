package ru.geekbrains.spring.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.geekbrains.spring.market.api.CartDto;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    // Cart microservice спокойно работает с core microservice через данный слой

    // private final RestTemplate restTemplate; после создания WebClient , он нам не нужен
    private final WebClient cartServiceWebClient;


    public CartDto getCurrentCart(){

        return cartServiceWebClient.get()
                .uri("api/v1/cart")
                // все, что идет до retrieve - конфигурирование нашего запроса (можем тело насторить, хедеры добавить, куки добавить и т.д.)
                .retrieve()  //отправляем запрос и хотим получить ответ
                .bodyToMono(CartDto.class) //если не пришло никаких непонятных объектов (см. onStatus), то мы преобразуем тело ответа к классу
                // в скобках (к классу ProductDto)
                .block();   // включит синхронный режим работы (я дожидаюсь, когда мне ответ придет )
        // Слово Mono означает , что придет когда-то ответ. но неизвестно когда (асинхронность) -- и мы как бы на него вешаем Callback
        // и говорим - как придет, надо сделать то-то и то-то. Т.е. Моно - это некий результат, кот-й будет получен в ближайшем будущем.
        // Противоположность Mono - это Flux. ОЗначает, что придет целая пачка объектов. И придет последовательно (синхронность).
        // Mono - это объект. Flux - это коллекция объектов.
        // .bodyToMono + .block -- означает, что нам когда-то ответ придет и мы его точно дождемся.

    }



    public void clear(){
        cartServiceWebClient.get()  //   мы посылаем гет запрос
                .uri("/api/v1/cart/delete") // ендпойнт к нашему uri
                .retrieve()  // вернет асинхронный вариант
                .toBodilessEntity() //это просто дождаться ответа без тела (если мы знаем, что в ответе не будет никакого текста или важной инфо)
                //    это противоположность .bodyToMono
                .block(); // включит синхронный режим работы (я дожидаюсь, когда мне ответ придет )

    }

    // Ниже для примеров :
    // public CartDto getUserCart(String username){

    //     CartDto cart = productServiceWebClient.get()
    //         .uri("/api/v1/cart/0/delete") // ендпойнт к нашему uri
    //         .header("username", username)
    //         // .bodyValue(body)  // for POST
    //         .retrieve()  // ожидаем ответ
    //         .onStatus(    // позволяет нам по-разному вести себя в зав-сти от ответа (реагировать на статусы по -разному)
    //             httpStatus -> httpStatus.is4xxClientError(), // HttpStatus::is4xxClientError
    //             clientResponse -> clientResponse.bodyToMono(CartServiceAppError.class).map(
    //                 body -> {
    //                     if(body.getCode().equals(CartServiceAppError.CartServiceErrors.CART_NOT_FOUND.name())){

    //                         return new CartServiceIntegrationException("ВЫполнен некорректный запрос к сервису корзины: корзина не найдена");
    //                     }
    //                     if(body.getCode().equals(CartServiceAppError.CartServiceErrors.CART_IS_BROKEN.name())){

    //                         return new CartServiceIntegrationException("ВЫполнен некорректный запрос к сервису корзины: корзина сломана");
    //                     }
    //                     return new CartServiceIntegrationException("ВЫполнен некорректный запрос к сервису корзины: причина неизвестна");
    //                 }
    //             )

    //         )
    //         .bodyToMono(CartDto.class) // если не пришло никаких непонятных объектов (см. onStatus), то мы преобразуем тело ответа к классу
    //         // в скобках (к классу CartDto)
    //         .block(); // дожидаемся этого ответа.
    //         return cart;

    //     }



}

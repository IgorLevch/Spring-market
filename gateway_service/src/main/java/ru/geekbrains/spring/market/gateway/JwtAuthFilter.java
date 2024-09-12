package ru.geekbrains.spring.market.gateway;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthFilter extends AbstractGatewayFilterFactory<JwtAuthFilter.Config>{


    // это нужно , чтобы в ЯМл файле прописывать имя класса (в разделе filters)

// когда запрос будет пролетать через Гейтвей - наша задача преобразовать его к заголовку Юзернейм и роли.

    @Autowired
    private JwtUtil jwtUtil;

    public JwtAuthFilter(){
        super(Config.class);
    }


    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) ->{
            ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();  // получаем ссылку на запрос, который сквозь нас пролетает

            if (request.getHeaders().containsKey("username")){
                return this.onError(exchange, "Authorization header is invalid", HttpStatus.BAD_GATEWAY);

            }



            if (!isAuthMissing(request)) { // если в запросе есть headerAuthorization
                final String token = getAuthHeader(request); // то мы из запроса выдергиваем Хедер Authorization (без слова Беарер)
                // это мы по сути выше выдернули токен из заголовка Ауторизейшн
                if (jwtUtil.isInvalid(token)) {// проверяем, валиден ли токен или нет
                    return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);
                    // если не валиден, кидаем эту ошибку выше
                }

                populateRequestWithHeaders(exchange, token); // если все хорошо, немножко корректируем запрос
            }
            //use builder to manipulate the request
            return chain.filter(exchange); // если же хедер Авторизейшна нет, то мы по цепочке фильтров дальше прокидываем запрос
            // т.е. по сути ничего не делаем
        };
        }

    public static class Config {

    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus){

        ServerHttpResponse response = (ServerHttpResponse) exchange.getResponse();
        response.setStatusCode(httpStatus);
        return ((ReactiveHttpOutputMessage) response).setComplete();

    }

    private String getAuthHeader(ServerHttpRequest request){
        //  мы из запроса выдергиваем Хедер Authorization (без слова Беарер)
        return request.getHeaders().getOrEmpty("Authorization").get(0).substring(7);

    }

    private boolean isAuthMissing(ServerHttpRequest request){
        if (!request.getHeaders().containsKey("Authorization")) {
            return true;
        }
        if (!request.getHeaders().getOrEmpty("Authorization").get(0).startsWith("Bearer")) {
            return true;
        }
        return false;

    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token){
        // получаем запрос, получаем токен и токен преобразоывае к юзернейму

        Claims claims = jwtUtil.getAllClaimsFromToken(token);// из токена доставем все полезные данные (все клеймсы)
        exchange.getRequest().mutate() // и говорим, что хотим чуть чуть подкорректировть запрос
                .header("username", claims.getSubject())
                // и добавляем хедер, который назывется юзернейм и добавим в него то, что в живет в
                // subject - а там живет юзернейм
                // .header("role", String.valueOf(claims.get("role")))   --- это если хотим подшить роли
                .build();


        // итого. этот метод заставляет наш гейтвей, когда через него пролетает запрос с заголовком Authorization
        //  выдернуть оттуда имя польз-ля и подшить его в кач-ве Хедера.

    }

}


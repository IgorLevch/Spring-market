package ru.geekbrains.spring.market.core.configs;


import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;
import ru.geekbrains.spring.market.core.properties.CartServiceIntegrationProperties;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(
        CartServiceIntegrationProperties.class
)
public class AppConfig {

    private final CartServiceIntegrationProperties cartServiceIntegrationProperties;

    // RestTemplate - это синхронный вариант отправки запросов. А синхронность - это плоховато.
    // WebClient - асинхронный
    @SuppressWarnings("deprecation")
    @Bean
    public WebClient cartServiceWebClient() { //для интеграции с каждым сервисом мы создаем свой веб-клиент
        // наш интегрируется с карт сервисом, поэтому пишем cartServiceWebClient

        TcpClient tcpClient = TcpClient   // создаем tcp клиента
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, cartServiceIntegrationProperties.getConnectTimeout())
                // выше настраиваем таймаут в миллисекундах на подключение
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(cartServiceIntegrationProperties.getReadTimeout(),
                            TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(cartServiceIntegrationProperties.getWriteTimeout(),
                            TimeUnit.MILLISECONDS));

                });  // добавляем 2 тайм-аута на чтение и отправку данных серверу (тайм-ауты очень важны,
        // чтобы мы не зависали до бесконечности
        // в ожидании непонятных ответов)

        return WebClient
                .builder()
                .baseUrl(cartServiceIntegrationProperties.getUrl())  // куда он будет подключаться
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
        // как настраивать эту штуку - см. в yaml файле :

        //         integrations:
        // product-service:
        //   url: http://localhost:0/winter/
        //   read-timeout: 10000    --    это 10 секунд
        //   write-timeout: 5000    --- это 5 секунд
        //   connect-teimeout: 5000


    }

}

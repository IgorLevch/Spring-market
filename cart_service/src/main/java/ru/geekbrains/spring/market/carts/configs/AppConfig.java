package ru.geekbrains.spring.market.carts.configs;

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
import ru.geekbrains.spring.market.carts.properties.ProductServiceIntegrationProperties;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(
        ProductServiceIntegrationProperties.class
)
@RequiredArgsConstructor
public class AppConfig {

    private final ProductServiceIntegrationProperties productServiceIntegrationProperties;
    @Bean
    public WebClient productServiceWebClient(){ //для интеграции с каждым сервисом мы создаем свой веб-клиент
        // наш интегрируется с продуктовым сервисом, поэтому пишем productServiceWebClient

        TcpClient tcpClient = TcpClient  // создаем tcp клиента
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, productServiceIntegrationProperties.getConnectTimeout())
        // выше настраиваем таймаут в миллисекундах на подключение
                .doOnConnected(connection-> {
                    connection.addHandlerLast(new ReadTimeoutHandler(productServiceIntegrationProperties.getReadTimeout(),
                            TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(productServiceIntegrationProperties.getWriteTimeout(),
                            TimeUnit.MILLISECONDS));  // добавляем 2 тайм-аута на чтение и отправку данных серверу
                    // (тайм-ауты очень важны, чтобы мы не зависали до бесконечности
                    // в ожидании непонятных ответов)
                });

                    return WebClient
                            .builder()
                            .baseUrl(productServiceIntegrationProperties.getUrl()) // куда он будет подключаться
                            .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                            .build();

                }

    }




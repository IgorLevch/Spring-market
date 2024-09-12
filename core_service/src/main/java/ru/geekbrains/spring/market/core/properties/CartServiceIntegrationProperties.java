package ru.geekbrains.spring.market.core.properties;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix= "integrations.cart-service")
@Data
@NoArgsConstructor
public class CartServiceIntegrationProperties {

    // это мы целый кусок пропертей из ямл фала записали сюда
    // (где дефис - пишем кемелКейс)  ---   т.е. мы упаковали пачку настроек в один бин.

    private String url;
    private Integer readTimeout;
    private Integer writeTimeout;
    private Integer connectTimeout;


    @ConstructorBinding
    public CartServiceIntegrationProperties(String url, Integer connectTimeout, Integer readTimeout,
                                            Integer writeTimeout) {
        this.url = url;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.writeTimeout = writeTimeout;
    }


}

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.spring.market.core.entities.Product;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // это означает: запустись на случ-м порту, подними весь бек-енд
@ActiveProfiles("test")    // это означает, что БД берем из файла тестовой БД (см.    readme.txt)
public class FullServerRunTest {

    @Autowired
    private TestRestTemplate restTemplate; // данный бин позволяет посылать запросы ( а TestRestTemplate повзоляет посылать запросы нашему сервису
    // тем самым, тестируя его)

    @Test
    public void fullRestTest(){

        List<Product> products = restTemplate.getForObject("/api/v1/products", List.class); // c пом-ю бина restTemplate взаимодействуем с бек-ендом
        // например, посылаем список запросов  на /api/v1/products .  List.class -- это то, что должны получить в ответ.
        assertThat(products).isNotNull(); // и проверяем, что коллекия не нулл и не пустая
        assertThat(products).isNotEmpty();


    }



}

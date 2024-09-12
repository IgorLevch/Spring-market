import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import ru.geekbrains.spring.market.core.entities.Product;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JsonTests {

    //тестируем джейсон-- применяется редко

    @Autowired
    private JacksonTester<Product> jackson; // инжектим джексон тестер

    @Test
    public void jsonSerializationTest() throws IOException { // проверяем сериализацию

        Product product = new Product(1L,"sugar", BigDecimal.valueOf(50));

        assertThat(jackson.write(product)) // через джексон пребразуем наш объект в кусок текста (в джейсонину)
                .hasJsonPathNumberValue("$.id") // проверяем, что если мы обратимся к этому объекту (с указанным id), то получим число 1
                .extractingJsonPathStringValue("$.title").isEqualTo("sugar");  // и когда мы выцепим поле title, то должны получить sugar
    }


    public void jsonDeserializationTest() throws IOException{ // проверяем десериализацию

        String content = "{\"id\": 2,\"title\":\"butter\", \"price\":51}"; // это у нас есть Джейсон
        Product product1 = new Product(2L,"butter", BigDecimal.valueOf(51));

        assertThat(jackson.parse(content)).isEqualTo(product1); // с пом-ю джексона парсим джейсон и проверяем,
        //чтобы он был полностью равен нашему продукту -- проверяем объекты
        assertThat(jackson.parseObject(content).getTitle()).isEqualTo("butter");
        // или -- выше - парсим объект , выдергиваем из него значение названия и проверяем, что оно будет равно нашему значению -- проверяем поля


    }



}

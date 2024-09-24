import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ru.geekbrains.spring.market.core.entities.Product;
import ru.geekbrains.spring.market.core.repositories.ProductRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;    // это заглушка на место бек-енда (приложение запускается, но не происходит никакого поднятия сервера)
    //  создается эмуляция диспетчер - сервлета -- и мы посылаем запрос в него, а он уже его маршрутизирует по нужным контроллерам
    // (эмуляция бека)

    @MockBean
    private ProductRepository productRepository;



    @Test
    public void getAllProductsTest() throws Exception{



        Product sugar = new Product(1L,"sugar", BigDecimal.valueOf(50));

        List<Product> allProducts = new ArrayList<>();
        allProducts.add(sugar);

        BDDMockito.given(productRepository.findAll()).willReturn(allProducts);
        // выше когда запросят найти все продукты, вернется только наш продукт

        mvc  // далее идет эмуляция

                .perform(
                        MockMvcRequestBuilders.get("/api/v1/products") // хотим сэмулировать отправку ГЕТ запроса
                        //  .contentType(MediaType.APPLICATION_JSON)  // конфигурируется запрос тут (Хедеры) --- что то не запускается
                )

                .andDo(MockMvcResultHandlers.print()) // отпечатать ответ в логи
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())  // мы спрашиваем - является ли корневой объект массивом
                .andExpect(jsonPath("$",    Matchers.hasSize(1)))  // имеет ли наш массив размер 1
                .andExpect(jsonPath("$[0].title",  CoreMatchers.is(allProducts.get(0).getTitle()))); // здесь мы спрашиваем:
        //дай нам наименование 0-го объекта массива

        // $ - ссылка на пришедший нам объект.  В нашем случае - на массив.



    }

}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.spring.market.api.CartDto;
import ru.geekbrains.spring.market.api.CartItemDto;
import ru.geekbrains.spring.market.core.entities.Category;
import ru.geekbrains.spring.market.core.entities.Order;
import ru.geekbrains.spring.market.core.entities.Product;
import ru.geekbrains.spring.market.core.integrations.CartServiceIntegration;
import ru.geekbrains.spring.market.core.repositories.OrderRepository;
import ru.geekbrains.spring.market.core.services.OrderService;
import ru.geekbrains.spring.market.core.services.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderServiceTest {

    // берем бин ,тестируем его логику. А все остальные бины, с которыми он связан -- из них делаем Мок-бины (заглушки)
    // и задаем идеальное поведение
    // это Юнит-тест Ордер-Сервиса

    @Autowired
    private OrderService orderService;   // это то, что мы будем тестировать

    @MockBean  // этой аннотацией мы подменяем бин, говорим, что он как-будто работает
    // чтобы карт-сервис думал, что он работает с интеграцией, а на самом деле , он работает с подменой
    private CartServiceIntegration cartServiceIntegration;

    @MockBean
    private ProductService productService;

    @MockBean
    private OrderRepository orderRepository;


    @Test
    public void createOrderTest(){
        CartDto cartDto = new CartDto(); // далее делаем «корзину», как мы хотели бы, чтобы заказ как будто оформлялся в ней
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductTitle("Juice");
        cartItemDto.setPricePerProduct( BigDecimal.valueOf(120));
        cartItemDto.setQuantity(2);
        cartItemDto.setPrice( BigDecimal.valueOf(240));
        cartItemDto.setProductId(19224L);
        cartDto.setTotalPrice(BigDecimal.valueOf(240));
        cartDto.setItems(List.of(cartItemDto));
        // выше мы сформировали некую тестовую корзину ( ее не существует, но мы ее придумали для теста)


        Mockito.doReturn(cartDto).when(cartServiceIntegration).getCurrentCart();  // Mockito - библиотека для создания заглушек
        //и выше мы говорим: Mockito - давай, ты нам вернешь cartDto, когда мы у нашего объекта , у cartServiceIntegration, вызовем
        // метод getCurrentCart()

        Category category = new Category();
        category.setId(1L);
        category.setTitle("Other");


        Product product = new Product(); // создаем «искусственный» продукт (а выше «создали» под него категорию)
        product.setId(19224L);
        product.setPrice(BigDecimal.valueOf(120));
        product.setTitle("Juice");
        product.setCategory(category);


        Mockito.doReturn(Optional.of(product)).when(productService).findById(19224L);
        // возвращаем product, когда мы у productService вызываем метод findById с указанным номером
        // мы подменили продуктовый Сервис


        Order order = orderService.createOrder("Bob");
        Assertions.assertEquals(order.getTotalPrice(), 240);   // проверим, что у заказа тотал прайс будет равен 240

        // ниже хотим проверить, что при формировании заказа у нашего orderRepository был вызван один раз метод save с любым аргументом
        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }



}

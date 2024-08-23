import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.geekbrains.spring.market.core.utils.Counter;

// ниже для ускорения прогона теста. И тогда в контекст попадет только этот Бин. Остальные бины никак не понадобятся
@SpringBootTest(classes = Counter.class)
public class CounterTest {


    @Autowired
    private Counter counter;

    @Test
    public void simpleCounterTest(){
        for (int i = 0; i < 10; i++) {
            counter.inc();
        }

        Assertions.assertEquals(10, counter.getValue()); // 10 - это expected
    }


}

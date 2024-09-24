import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SpyTest {



    @Spy  //  мы можем отслеживать поведение бинов (для этого инжектим бин. и помечаем его @Spy)
    private List<Integer> spiedList = new ArrayList<>();
    // и бибилиотека mockito начинает за бином следить
    // и мы можем проверить , что у бина были вызваны такие -то методы с такими то параметрами (после опредленного общения с ним)
    // также можем бину- шпиону подменить поведение

    @Test
    public void spyTest(){

        spiedList.add(1);  // в момент теста добавляем в наш ArrayList объект 1, объект 2, объект 3
        spiedList.add(2);  // и добавили 3 объекта
        spiedList.add(3);

        Mockito.verify(spiedList).add(1); //проверяем, что были выполнены вызовы с 1, 2, 3
        Mockito.verify(spiedList).add(2);
        Mockito.verify(spiedList).add(3);

        assertEquals(3, spiedList.size()); // проверяем. что после данных действий у нас в листе будет жить 3 объекта

        Mockito.doReturn(100).when(spiedList).size();// мы говорим шпиону: когда у тебя спросят size --  верни, пожалуйстка, значение 100

        assertEquals(100, spiedList.size()); // и он будет возвращать значение 100

        System.out.println(spiedList.getClass().getName());


    }
}

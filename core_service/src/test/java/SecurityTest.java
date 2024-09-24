// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.security.test.context.support.WithMockUser;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;

// // коммитю, т.к. не знаю, какие там нужны импорты

// @SpringBootTest
// @AutoConfigureMockMvc  // Эта аннотация нужна для того, чтобы появилась возможность внедрить в тестовый класс бин MockMvc
//public class SecurityTest {

//    Если используем модуль Спринг Секьюрити, то было б неплохо его тестировать.


//     @Autowired
//     private MockMvc mockMvc;  // это не веб окружение, это подмененный диспетчер сервлет
//     // (там создается бин, которому какбы направляются запросы)
//      // еще:  Класс MockMvc предназначен для тестирования контроллеров. Он позволяет
//       //тестировать контроллеры без запуска http-сервера. То есть при выполнении тестов сетевое соединение не создается.

//     @Test
//     public void securityAccessAllowedTest() throws Exception{

//         mockMvc.perform(get("/api/v1/products")) // обращаемся к незащищенному енд-пойнту
//                 .andDo(print())
//                 .andExpect(status().isOk())  // проверяем , что статус будет 200
//                 .andExpect(jsonPath("$.content").isArray()); //  и нам будут отданы продукты
//                 // это выше считается самый простой тест -- постучались по енд-понту и получили опредленный ответ
//     }


//     @Test
//     public void securityAccessDeniedTest(){
//         mockMvc.perform(get("/api/v1/orders")) // посылаем запрос в защищенную область
//         .andDo(print())
//         .andExpect(status().isUnauthorized());  // в РЕСТЕ если мы сами токен не прикрутим, то он работать не будет и
//         // при попытке отправить запрос в защищенную область,  должны получить 401

//     }
//     @Test
//     @WithMockUser(username = "Bob", roles = "ADMIN") // в контексте юзер с именем и ролью такими-то
//     //  есть он или нет в базе никто проверять не будет
//     public void securityCheckUserTest(){
//         mockMvc.perform(get("/api/v1/orders")) // посылаем запрос в защищенную область
//         .andDo(print())
//         .andExpect(status().isOk());   // и ожидаем получить 200

//     }

//     @Test
//     public void securityTokenTest(){
//         String jsonRequest = "{\n" +  // формируем запрос на получение токена с реальными данными (bob , 100)
//         "\t\"username\": \"bob\",\n" +
//         "\t\"password\": \"100\"\n" +
//         "}";
//         MvcResult result = mockMvc.perform(
//                     post("/auth")  // послать пост-запрос на auth
//                     .content(jsonRequest)  // в тело запроса подшить json
//                     .contentType(MediaType.APPLICATION_JSON) // сказать, что это АППЛИКЕЙШН ДЖЕЙСОН
//         )
//         .andExpect(status().isOk())  // выполнить запрос
//         .andReturn();    // получить ответ   (в ответ мы получим джейсон с токеном)

//             String token = result.getResponse().getContentAsString();  // потом тело ответа выдергиваем в виде строки
//             token = token.replace("{\"token\":\"","").replace("\"}", ""); // отпиливаем все лишнее
//             // так чтобы остался только джейсон веб токен в строке (реплейом убираем все лишнее)

//             mockMvc.perform(get("/api/v1/orders")
//                 .header("Authorization","Bearer"+token)) //  на ордерс посылаем запрос с Ауторизейшн заголовком и токеном, который сейчас получили
//                 .andExpect(status().isOk());// и проверяем, что будет все ок.



//     }



// }




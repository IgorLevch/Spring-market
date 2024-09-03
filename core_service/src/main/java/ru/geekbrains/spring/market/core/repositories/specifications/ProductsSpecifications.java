package ru.geekbrains.spring.market.core.repositories.specifications;


import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.spring.market.core.entities.Product;

//    аналог creteria API в Хибернет
public class ProductsSpecifications {

        public static Specification<Product> priceGreaterOrEqualThan(Integer price){
            return (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);

        }

        // root - это ссылка на объект, с которым имеем дело. в нашем случае - на Product
     // criteriaBuilder - позволяет формировать куски запросов
    // root.get("price"), price)  --- мы у значения поля прайс продукта (root):root.get("price")
    // запрашиваем цену больше или равно той, которую ввели в аргументе метода: price

        public static Specification<Product>  priceLessThanOrEqualsThan(Integer price){
            return (root,criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
        }

        public static Specification<Product> titleLike(String titlePart){
            return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));

            //%s  -  это просто String.format.  А еще %% перед ним -  это экранированный процент
            // означает, что мы хотим в эту строку впихнуть процент, а не подставить какой-то элемент (например, %d)
            //а %% - в конце --  это тоже преобразовывается в 1 %.
            // т.е. %% (в начале ) преобразовывается в один %. %s - это название. и %% в конце тоже преобразовываются в один %.
        }



}

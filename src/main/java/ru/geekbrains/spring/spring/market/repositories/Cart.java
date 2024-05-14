package ru.geekbrains.spring.spring.market.repositories;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.spring.market.entities.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class Cart {

    private List<Product> productList;

    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);  // неизменяемая коллекция, чтобы др. разработчик не
                                                         // внес изменения в корзину ( и при этом может поменяться
                                                         // стоимость)
    }

    @PostConstruct
    public void init(){
    this.productList=new ArrayList<>();

    }
    public void addProduct(Product product){
        productList.add(product);

    }

    public void deleteProduct(Product product){

        productList.remove(product);
    }

}

package ru.geekbrains.spring.spring.market.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.spring.market.dtos.ProductDto;
import ru.geekbrains.spring.spring.market.entities.Category;
import ru.geekbrains.spring.spring.market.entities.Product;
import ru.geekbrains.spring.spring.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.spring.market.services.CategoryService;

@Component
@RequiredArgsConstructor
public class ProductConverter {  // преобразовывает сущность в ДТО и наоборот

    private final CategoryService categoryService;
    public ProductDto entityToDto(Product product){

        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(),
                product.getCategory().getTitle());
    }

    public Product dtoToEntity(ProductDto productDto){
        Product p =new Product();
        p.setId(productDto.getId());
        p.setTitle(productDto.getTitle());
        p.setPrice(productDto.getPrice());
        Category c = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(()->
                new ResourceNotFoundException("Category not found"));
        p.setCategory(c);
        return p;
    }


}

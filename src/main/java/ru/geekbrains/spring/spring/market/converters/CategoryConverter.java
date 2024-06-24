package ru.geekbrains.spring.spring.market.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.spring.market.dtos.CategoryDto;
import ru.geekbrains.spring.spring.market.dtos.ProductDto;
import ru.geekbrains.spring.spring.market.entities.Category;

import ru.geekbrains.spring.spring.market.services.CategoryService;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryConverter {

    private final ProductConverter productConverter;
    public CategoryDto entityToDto(Category category){ // категорию к ДТО
        CategoryDto c = new CategoryDto();
        c.setId(category.getId());
        c.setTitle(category.getTitle());
        c.setProducts(category.getProducts().stream().map(productConverter::entityToDto).collect(Collectors.toList()));

        return c;
    }



}

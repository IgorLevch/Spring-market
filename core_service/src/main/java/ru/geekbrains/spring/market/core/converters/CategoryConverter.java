package ru.geekbrains.spring.market.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.market.api.CategoryDto;
import ru.geekbrains.spring.market.core.entities.Category;


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


    public Category dtoToEntity(CategoryDto categoryDto){
        Category c =new Category();
        c.setId(categoryDto.getId());
        c.setTitle(categoryDto.getTitle());
        c.setProducts(categoryDto.getProducts().stream().map(productConverter::dtoToEntity).collect(Collectors.toList()));

        return c;
    }




}

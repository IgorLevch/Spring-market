package ru.geekbrains.spring.spring.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.spring.market.entities.Category;
import ru.geekbrains.spring.spring.market.entities.Product;
import ru.geekbrains.spring.spring.market.repositories.CategoryRepository;
import ru.geekbrains.spring.spring.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title){

        return categoryRepository.findByTitle(title);
    }



}

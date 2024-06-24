package ru.geekbrains.spring.spring.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.spring.market.dtos.ProductDto;
import ru.geekbrains.spring.spring.market.entities.Category;
import ru.geekbrains.spring.spring.market.entities.Product;
import ru.geekbrains.spring.spring.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.spring.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<Product> findAll(){

        return productRepository.findAll();
    }


    public Optional<Product> findById(Long id){

        return productRepository.findById(id);
    }


    public void deleteById(Long id){

        productRepository.deleteById(id);
    }

    public Product createNewProduct(ProductDto productDto){
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(product.getTitle());

        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() ->
                new ResourceNotFoundException("Category not found"));
        product.setCategory(category);
        productRepository.save(product);

        return product;
    }


}

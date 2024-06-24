package ru.geekbrains.spring.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.spring.market.converters.ProductConverter;
import ru.geekbrains.spring.spring.market.dtos.ProductDto;
import ru.geekbrains.spring.spring.market.entities.Category;
import ru.geekbrains.spring.spring.market.entities.Product;
import ru.geekbrains.spring.spring.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.spring.market.services.CategoryService;
import ru.geekbrains.spring.spring.market.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;


    @GetMapping
    public List<ProductDto> findAllProducts(){

        return productService.findAll().stream().map(productConverter::entityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id){
        Product p = productService.findById(id).orElseThrow(()->
                new ResourceNotFoundException("product not found id: "+id));
        return productConverter.entityToDto(p);
    }

    @PostMapping
    public  ProductDto createNewProduct(@RequestBody ProductDto productDto){
        Product product = productService.createNewProduct(productDto);
        return productConverter.entityToDto(product);

    }



    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id){

        productService.deleteById(id);

    }








}

package ru.geekbrains.spring.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.market.api.ProductDto;
import ru.geekbrains.spring.market.api.ResourceNotFoundException;
import ru.geekbrains.spring.market.core.converters.ProductConverter;
import ru.geekbrains.spring.market.core.entities.Product;
import ru.geekbrains.spring.market.core.services.ProductService;


import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@CrossOrigin("*") //  т.к. за это теперь отвечает Гейтвей -- комментим
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;


    @GetMapping
    public List<ProductDto> findProducts(
            @RequestParam(required = false, name = "min_price") Integer minPrice,
            @RequestParam(required = false, name = "max_price") Integer maxPrice,
            @RequestParam(required = false, name = "title") String title,
            @RequestParam(defaultValue = "1", name = "p") Integer page // всегда указываем 1, т.к. по умолчанию, если польз-ль не указал, но попасть он должен всегда
            ){

        if (page < 1){  // если кто-то случайно вобьет отрицательную величину
            page=1;
        }

        Specification<Product> spec = productService.createSpecByFilters(minPrice, maxPrice, title);


        return productService.findAll(spec, page-1).map(productConverter::entityToDto).getContent();
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

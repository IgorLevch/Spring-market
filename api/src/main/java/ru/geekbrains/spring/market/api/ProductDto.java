package ru.geekbrains.spring.market.api;


import java.math.BigDecimal;

public class ProductDto {   // это не сущность

// джесоновские объекты должны быть всегда с дефолтным конструктором (джексон, когда получает/отдает объект
// в джейсоне, использует дефолтный конструктор для его формирования)


    private Long id;
    private String title;
    private BigDecimal price;
    private String categoryTitle;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public ProductDto(Long id, String title, BigDecimal price, String categoryTitle) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.categoryTitle = categoryTitle;
    }


    public ProductDto() {
    }
}

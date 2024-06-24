package ru.geekbrains.spring.spring.market.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {   // это не сущность

// джесоновские объекты должны быть всегда с дефолтным конструктором (джексон, когда получает/отдает объект
// в джейсоне, использует дефолтный конструктор для его формирования)


    private Long id;
    private String title;
    private int price;
    private String categoryTitle;


}

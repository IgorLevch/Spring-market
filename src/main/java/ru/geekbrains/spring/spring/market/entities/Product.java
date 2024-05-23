package ru.geekbrains.spring.spring.market.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="products")
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="price")
    private int price;


    @CreationTimestamp   // это хибернейтовская аннотация/ фиксирует время создания
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp      // это хибернейтовская аннотация / хибернейт обновляет объект текущим временем при апдете
    @Column(name="updated_at")
    private LocalDateTime updatedAt;



}

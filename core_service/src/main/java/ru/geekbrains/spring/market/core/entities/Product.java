package ru.geekbrains.spring.market.core.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
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
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @CreationTimestamp   // это хибернейтовская аннотация/ фиксирует время создания
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp      // это хибернейтовская аннотация / хибернейт обновляет объект текущим временем при апдете
    @Column(name="updated_at")
    private LocalDateTime updatedAt;



    public Product(Long id, String title, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }


}

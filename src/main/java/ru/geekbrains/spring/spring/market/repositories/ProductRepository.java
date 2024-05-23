package ru.geekbrains.spring.spring.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.spring.market.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

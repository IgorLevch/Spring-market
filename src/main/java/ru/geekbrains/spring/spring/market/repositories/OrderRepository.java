package ru.geekbrains.spring.spring.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.spring.market.entities.Order;
import ru.geekbrains.spring.spring.market.entities.User;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


}

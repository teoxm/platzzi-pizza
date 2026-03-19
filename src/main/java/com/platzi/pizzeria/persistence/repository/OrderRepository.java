package com.platzi.pizzeria.persistence.repository;

import com.platzi.pizzeria.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByOrderDateAfter(LocalDateTime orderDate);
    List<OrderEntity> findAllByMethodIn(List<String> methods);
}

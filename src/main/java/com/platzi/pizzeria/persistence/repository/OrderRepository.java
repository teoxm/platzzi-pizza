package com.platzi.pizzeria.persistence.repository;

import com.platzi.pizzeria.persistence.entity.OrderEntity;
import com.platzi.pizzeria.persistence.proyection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByOrderDateAfter(LocalDateTime orderDate);

    List<OrderEntity> findAllByMethodIn(List<String> methods);

    @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :id", nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("id") Integer idCustomer);

    @Query(value = """
    SELECT 
        po.id_order AS "idOrder",
        c.name AS "customerName",
        po.order_date AS "orderDate",
        po.total AS "orderTotal",
        CAST(LISTAGG(p.name, ', ') WITHIN GROUP (ORDER BY p.name) AS VARCHAR2(4000)) AS "pizzaNames"
    FROM pizza_order po
    JOIN customer c ON po.id_customer = c.id_customer
    JOIN order_item oi ON po.id_order = oi.id_order
    JOIN pizza p ON oi.id_pizza = p.id_pizza
    WHERE po.id_order = ?1
    GROUP BY po.id_order, c.name, po.order_date, po.total
""", nativeQuery = true)
    List<OrderSummary> findSummary(int orderId);}

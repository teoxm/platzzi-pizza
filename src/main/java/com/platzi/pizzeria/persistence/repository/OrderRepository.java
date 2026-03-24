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

    @Query(value =
            "SELECT po.ID_ORDER AS idOrder, " +
                    "       po.TOTAL AS orderTotal, " +
                    "       cu.NAME AS customerName, " +
                    "       TO_CHAR(po.ORDER_DATE, 'YYYY-MM-DD') AS orderDate, " +
                    "       po.METHOD AS method, " +
                    "       LISTAGG(pi.NAME, ' ') WITHIN GROUP (ORDER BY pi.NAME) AS pizzaNames " +
                    "FROM pizza_order po " +
                    "INNER JOIN customer cu ON po.ID_CUSTOMER = cu.ID_CUSTOMER " +
                    "INNER JOIN order_item oi ON po.ID_ORDER = oi.ID_ORDER " +
                    "INNER JOIN pizza pi ON oi.ID_PIZZA = pi.ID_PIZZA " +
                    "WHERE po.ID_ORDER = :idOrder " +
                    "GROUP BY po.ID_ORDER, po.TOTAL, cu.NAME, po.ORDER_DATE, po.METHOD",
            nativeQuery = true
    )
    OrderSummary findSummary(@Param("idOrder") int orderId);}

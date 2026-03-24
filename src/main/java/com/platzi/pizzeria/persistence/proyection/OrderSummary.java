package com.platzi.pizzeria.persistence.proyection;

import java.time.LocalDateTime;

public interface OrderSummary {
    Integer getIdOrder();
    String getCustomerName();
    String getOrderDate();
    Double getOrderTotal();
    String getPizzaNames();
}

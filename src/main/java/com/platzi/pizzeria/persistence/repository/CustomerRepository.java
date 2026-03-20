package com.platzi.pizzeria.persistence.repository;

import com.platzi.pizzeria.persistence.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends ListCrudRepository<Customer, Integer> {
    @Query(value = "SELECT c FROM Customer c WHERE c.phoneNumber = :phone")
    Customer findByPhone(@Param("phone") String phone);
}

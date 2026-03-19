package com.platzi.pizzeria.persistence.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_seq", sequenceName = "CUSTOMER_SEQ", allocationSize = 1)
    @Column(name = "id_customer", nullable = false)
    private Integer idCustomer;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(name = "address",nullable = false, length = 60)
    private String addres;

    @Column(nullable = false, length = 30)
    private String email;

    @Column(name = "phone_number",nullable = false, length = 30)
    private String phoneNumber;

}

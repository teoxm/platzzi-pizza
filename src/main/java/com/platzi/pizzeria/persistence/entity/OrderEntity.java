package com.platzi.pizzeria.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pizza_order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "ORDER_SEQ", allocationSize = 1)
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;


    @Column(name="id_costumer", nullable = false, length = 15)
    private String idCostumer;

    @Column(name ="order_date",nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime orderDate;

    @Column(nullable = false, columnDefinition = "NUMBER(6,2)")
    private Double total;

    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private String method;

    @Column(name = "additional_notes", length = 200)
    private String additionalNotes;

    @OneToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "id_customer", insertable = false, updatable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrdenItemEntity> items;
}

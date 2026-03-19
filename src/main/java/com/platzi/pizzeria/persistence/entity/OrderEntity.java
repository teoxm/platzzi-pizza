package com.platzi.pizzeria.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pizza_order")
@Getter
@Setter
@NoArgsConstructor

public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "ORDER_SEQ", allocationSize = 1)
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;


    @Column(name="id_customer", nullable = false, length = 15)
    private String idCostumer;

    @Column(name ="order_date",nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime orderDate;

    @Column(nullable = false, columnDefinition = "NUMBER(6,2)")
    private Double total;

    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private String method;

    @Column(name = "additional_notes", length = 200)
    private String additionalNotes;

    @OneToOne(fetch = FetchType.LAZY)// no trae todo lo relacionado con esta relacion, a menos que se utilice, ne se realizan las consultas a customer
    @JoinColumn(name = "id_customer", referencedColumnName = "id_customer", insertable = false, updatable = false)
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER) // al utilizarce el entity de esta clase automaticamente me trae la realacion
    private List<OrdenItemEntity> items;
}

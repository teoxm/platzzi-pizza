package com.platzi.pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@IdClass(OrdenItemId.class)//anotacion para indicar que tiene una clave primaria compuesta
@Getter
@Setter
@NoArgsConstructor
public class OrdenItemEntity {

    @Id
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Id
    @Column(name = "id_item", nullable = false)
    private Integer idItem;

    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(nullable = false, columnDefinition = "NUMBER(2,1)")
    private Double quantity;

    @Column(nullable = false, columnDefinition = "NUMBER(5,2)")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id_order", insertable = false, updatable = false)
    private OrderEntity order;

    //Relacion entre entidades entre OrdenItemEntity con PizzaEntity
    @OneToOne//Anotacion para indicar que la relacion es uno a uno
    @JoinColumn(name = "id_pizza", referencedColumnName = "id_pizza", insertable = false, updatable = false)//indica desde que colummna ocurre el join
    private PizzaEntity pizza;
}

package com.platzi.pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="pizza")
@Getter
@Setter//estas anotaciones generan automaticamente los gtters y los setters de manera interna

@NoArgsConstructor//genera un constructor sin parametros
public class PizzaEntity {

    @Id//indica que este atributo es la clave primaria de la tala pizza
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pizza_seq")//Genera un valor automatico de id al momento de ingresar un objeto a la tabla
    @SequenceGenerator(name = "pizza_seq", sequenceName = "PIZZA_SEQ", allocationSize = 1)
    @Column(name = "id_pizza", nullable = false)//anotacion que identifica el nomnre de la columna de la tabla
    private Integer idPizza;

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(name = "description", nullable = false, length = 150)
    private String descripcion;

    @Column(nullable = false, columnDefinition = "NUMBER(5,2)")
    private Double price;

    @Column(columnDefinition = "NUMBER(1)")
    private Boolean vegetarian;

    @Column(columnDefinition = "NUMBER(1)")
    private Boolean vegan;

    @Column(columnDefinition = "NUMBER(1)")
    private Boolean available;
}

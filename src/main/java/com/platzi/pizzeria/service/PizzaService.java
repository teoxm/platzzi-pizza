package com.platzi.pizzeria.service;

import com.platzi.pizzeria.persistence.entity.PizzaEntity;
import com.platzi.pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> getAll(){
        return this.pizzaRepository.findAll();
    }
    public List<PizzaEntity> getAvailable(){
        System.out.println(this.pizzaRepository.countByVeganTrue());;
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    public PizzaEntity getByName(String name){
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElseThrow(()-> new RuntimeException("La pizza no existe"));
    }

    public List<PizzaEntity> getWith(String ingrediente){
        return this.pizzaRepository.findAllByAvailableTrueAndDescripcionContainingIgnoreCase(ingrediente);
    }
    public List<PizzaEntity> getWithOut(String ingrediente){
        return this.pizzaRepository.findAllByAvailableTrueAndDescripcionNotContainingIgnoreCase(ingrediente);
    }
    public List<PizzaEntity> getCheapest(double price){
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public PizzaEntity get(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza){
        return this.pizzaRepository.save(pizza);
    }

    public void delete(int idPizza){
        this.pizzaRepository.deleteById(idPizza);
    }

    public Boolean existe(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }


}

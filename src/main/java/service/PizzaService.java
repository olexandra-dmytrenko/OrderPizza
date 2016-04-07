package service;

import domain.Pizza;

import java.util.List;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public interface PizzaService {
    Pizza find(Integer id);
    Pizza save(Pizza pizza);
    List<Pizza> findAll();
}

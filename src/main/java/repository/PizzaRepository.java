package repository;

import domain.pizza.Pizza;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public interface PizzaRepository {
   public Pizza find(int id);
}

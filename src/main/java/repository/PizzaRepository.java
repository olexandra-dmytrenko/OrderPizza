package repository;

import domain.Pizza;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public interface PizzaRepository {
   Pizza find(int id);

   Pizza save(Pizza pizza);
}

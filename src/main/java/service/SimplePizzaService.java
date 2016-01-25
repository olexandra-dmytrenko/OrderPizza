package service;

import domain.pizza.Pizza;
import repository.PizzaRepository;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class SimplePizzaService implements PizzaService {
    public Pizza find(int id) {
        JavaConfig config = new JavaConfig();
        PizzaRepository pizzaRepository = (PizzaRepository) config.getImpl("pizzaRepository");

        return pizzaRepository.find(id);
    }

}

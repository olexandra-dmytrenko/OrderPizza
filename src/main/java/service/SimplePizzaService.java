package service;

import domain.pizza.Pizza;
import repository.PizzaRepository;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class SimplePizzaService implements PizzaService {
    PizzaRepository pizzaRepository;

    public SimplePizzaService() {
        JavaConfig config = new JavaConfig();
        try {
            pizzaRepository = (PizzaRepository) config.getImpl("pizzaRepository");
        } catch (Exception e) {
            System.out.print(e);
        }
    }


    public Pizza find(int id) {

        return pizzaRepository.find(id);
    }

}

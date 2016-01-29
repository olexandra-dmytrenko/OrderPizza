package repository;

import domain.Pizza;

import java.util.HashMap;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class InMemPizzaRepository implements PizzaRepository {
    private static HashMap<Integer, Pizza> pizzas;

    public InMemPizzaRepository() {
        init();
    }

    void init(){

        pizzas = new HashMap<Integer, Pizza>();
        pizzas.put(new Integer(1), new Pizza("Sea"));
        pizzas.put(new Integer(2), new Pizza("Meat"));
        pizzas.put(new Integer(3), new Pizza("Cheese"));
        pizzas.put(new Integer(4), new Pizza("Margarita"));
        pizzas.put(new Integer(5), new Pizza("Tropic"));
    }

    public Pizza find(int id) {
        return pizzas.get(id);
    }
    public static HashMap<Integer, Pizza> getPizzas() {
        return pizzas;
    }

}

package repository;

import domain.BenchMark;
import domain.Pizza;

import java.util.HashMap;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class InMemPizzaRepositoryNoSpring implements PizzaRepository {
    private HashMap<Integer, Pizza> pizzas;

    public InMemPizzaRepositoryNoSpring() {
        //       init();
    }

    /**
     * This method is invoked at the initialization time
     */
    public void init() {
//        setPizzas(pizzas);
        pizzas = new HashMap();
        pizzas.put(1, new Pizza("Sea", 1));
        pizzas.put(2, new Pizza("Meat", 2));
        pizzas.put(3, new Pizza("Cheese", 3));
        pizzas.put(4, new Pizza("Margarita", 4));
        pizzas.put(5, new Pizza("Tropic", 5));
        System.out.println(initializeMessage() + ". Really.");
    }

    @BenchMark
    @Override
    public Pizza find(int id) {
        return pizzas.get(id);
    }

    public HashMap<Integer, Pizza> getPizzas() {
        return pizzas;
    }

    @BenchMark
    public void setPizzas(HashMap<Integer, Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}

package domain.pizza;

import java.util.HashMap;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class Pizza {

    private static HashMap<Integer, Pizza> pizzas;

    static {

        pizzas = new HashMap<Integer, Pizza>();
        pizzas.put(new Integer(1), new Pizza("One"));
        pizzas.put(new Integer(2), new Pizza("Two"));
        pizzas.put(new Integer(3), new Pizza("Three"));
        pizzas.put(new Integer(4), new Pizza("Four"));
        pizzas.put(new Integer(5), new Pizza("Five"));
    }

    private final String name;

    public Pizza(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static HashMap<Integer, Pizza> getPizzas() {
        return pizzas;
    }
}

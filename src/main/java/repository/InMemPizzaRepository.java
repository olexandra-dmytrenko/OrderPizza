package repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.Pizza;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class InMemPizzaRepository implements PizzaRepository {
    private HashMap<java.lang.Integer, Pizza> pizzas;

    public InMemPizzaRepository() {

        // init();
    }

    void init() {
        System.out.println("All pizzas have been initialized through init method");
        setPizzas(pizzas);

        // pizzas = new HashMap<Integer, Pizza>();
        // pizzas.put(new Integer(1), new Pizza("Sea"));
        // pizzas.put(new Integer(2), new Pizza("Meat"));
        // pizzas.put(new Integer(3), new Pizza("Cheese"));
        // pizzas.put(new Integer(4), new Pizza("Margarita"));
        // pizzas.put(new Integer(5), new Pizza("Tropic"));
    }

    public Pizza find(int id) {
        return pizzas.get(id);
    }

    public HashMap<java.lang.Integer, Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(HashMap<java.lang.Integer, Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    @Override
    public List<Pizza> findAll() {
        return pizzas.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    public Pizza updateByName(Pizza pizza) {
        pizzas.entrySet().stream().filter(p -> p.getValue().getName().equals(pizza.getName()))
                .forEach(p -> p.getValue().setPrice(pizza.getPrice()));
        return pizza;
    }

    @Override
    public Pizza save(Pizza pizza) {
        try {
            return updateByName(pizza);
        } catch (NullPointerException e) {
            return pizzas.put(pizzas.size(), pizza);
        }
    }
}

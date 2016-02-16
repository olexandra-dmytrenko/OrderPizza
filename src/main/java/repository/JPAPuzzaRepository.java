package repository;

import domain.Pizza;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;

/**
 * Created by Oleksandra_Dmytrenko on 2/16/2016.
 */
public class JPAPuzzaRepository {

    @PersistenceContext
    private EntityManager em;
    private HashMap<Integer, Pizza> pizzas;

    // public InMemPizzaRepository() {
    //
    // //init();
    // }

    @Transactional(readOnly = true)
    public Pizza find(int id) {
        return pizzas.get(id);
    }

    @Transactional
    public void save(Pizza pizza) {

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

    public HashMap<java.lang.Integer, Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(HashMap<java.lang.Integer, Pizza> pizzas) {
        this.pizzas = pizzas;
    }

}

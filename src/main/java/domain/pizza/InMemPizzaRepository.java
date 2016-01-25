package domain.pizza;

import repository.PizzaRepository;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class InMemPizzaRepository implements PizzaRepository {

    public Pizza find(int id) {
        return Pizza.getPizzas().get(id);
    }
}

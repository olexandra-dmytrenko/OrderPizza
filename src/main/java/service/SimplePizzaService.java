package service;

import java.util.ArrayList;
import java.util.List;

import domain.customer.Customer;
import domain.order.InMemOrderRepository;
import domain.order.Order;
import domain.pizza.Pizza;
import repository.OrderRepository;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class SimplePizzaService implements PizzaService {
    public Pizza find(int id) {
        return Pizza.getPizzas().get(id);
    }

}

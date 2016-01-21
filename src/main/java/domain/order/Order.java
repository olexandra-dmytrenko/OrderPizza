package domain.order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.customer.Customer;
import domain.pizza.Pizza;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class Order {
    private static List<Order> orders = new ArrayList<Order>();

    private final Customer customer;
    List<Pizza> pizzas;
    int id = 0;

    public Order(Customer customer, List<Pizza> pizzas) {
        this.customer = customer;
        this.pizzas = pizzas;
        id += 1;
    }

    public static List<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", pizzas=" + pizzas.stream().map(p -> p.getName()).collect(Collectors.joining(", ")) +
                ", id=" + id +
                '}';
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}

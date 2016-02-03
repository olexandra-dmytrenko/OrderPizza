package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class Order {
    private static List<Order> orders = new ArrayList<Order>();

    private Customer customer;
    List<Pizza> pizzas;
    AtomicLong id = new AtomicLong(0);

    public Order() {

    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public Order(Customer customer
            , List<Pizza> pizzas
                 ) {
        this.customer = customer;
        this.pizzas = pizzas;
        id.incrementAndGet();
    }

    public static List<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", pizzas=" + pizzas.stream().map(Pizza::getName).collect(Collectors.joining(", ")) +
                ", id=" + id +
                '}';
    }


    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

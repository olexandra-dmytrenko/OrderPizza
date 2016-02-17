package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class Order implements OrderActions {
    public static final double DISCOUNT_30_PERCENT = 0.3;
    public static final int PIZZA_AMOUNT_FOR_DISCOUNT = 4;
    private static List<Order> orders = new ArrayList<>();
    private Status status;

    private Customer customer;
    List<Pizza> pizzas;
    long number;
    static AtomicLong id = new AtomicLong(0);


//    public Order() {
//    }

    public void setOrders(List<Order> newOrders) {
        orders = newOrders;
    }

    public static List<Order> getOrders() {
        return orders;
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

    public Order(Customer customer, List<Pizza> pizzas) {
        this.customer = customer;
        this.pizzas = pizzas;
        this.number = id.incrementAndGet();
        this.status = Status.NEW;
    }

    @Override
    public boolean switchStatusTo(Status newStatus) {
        if (Arrays.asList(this.status.getStatuses()).contains(newStatus.toString())) {
            this.status = newStatus;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", pizzas=" + pizzas.stream().map(Pizza::getName).collect(Collectors.joining(", ")) +
                ", order number=" + number +
                ", order status=" + status +
                '}';
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public double countTotalPrice() {
        return pizzaPricesStream().sum();
    }

    @Override
    public double countTotalPriceWithPossibleDiscount() {
        return applyDiscount();
    }

    private double applyDiscount() {
        double totalPrice = countTotalPrice();
        if (pizzas.size() > PIZZA_AMOUNT_FOR_DISCOUNT) {
            double discount = pizzaPricesStream().max().getAsDouble() * DISCOUNT_30_PERCENT;
            totalPrice = totalPrice - discount;
        }
        return totalPrice;
    }

    private DoubleStream pizzaPricesStream() {
        return pizzas.stream().mapToDouble(Pizza::getPrice);
    }
}

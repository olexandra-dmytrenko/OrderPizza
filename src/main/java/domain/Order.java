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
    public static final double THIRTY_PERCENT_MULTIPLIER = 0.3;
    public static final int PIZZA_AMOUNT_FOR_DISCOUNT = 4;
    private static List<Order> orders = new ArrayList<>();
    private Status status;

    private Customer customer;
    List<Pizza> pizzas;
    long number;
    static AtomicLong id = new AtomicLong(0);

    public Order(Customer customer, List<Pizza> pizzas) {
        this.customer = customer;
        this.pizzas = pizzas;
        this.number = id.incrementAndGet();
        this.status = Status.NEW;
        customer.getPromoCard().setBlockedAmount(countTotalPriceWithPossiblePizzaAmountDiscount());
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public boolean switchStatusTo(Status newStatus) {
        if (Arrays.asList(this.status.getStatuses()).contains(newStatus.toString())) {
            this.status = newStatus;
            if (newStatus.equals(Status.DONE)) {
                customer.getPromoCard().updateAmountFromBlocked();
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", pizzas=" + pizzas.stream().map(Pizza::getName).collect(Collectors.joining(", ")) +
                ", order id=" + number +
                ", order status=" + status +
                '}';
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public double countTotalPriceNoDiscounts() {
        double sum = pizzaPricesStream().sum();
        System.out.println("Total price without discount = " + sum);
        return sum;
    }

    public double countTotalPriceWithPossiblePizzaAmountDiscount() {
        return countTotalPriceNoDiscounts() - getPizzaAmountDiscount();
    }

    @Override
    public double getPromoDiscount() {
        double discount = this.customer.getPromoCard().getDiscount(countTotalPriceWithPossiblePizzaAmountDiscount());
        System.out.println("Pizza promo discount = " + discount);
        return discount;
    }

    @Override
    public double getPizzaAmountDiscount() {
        double discount = 0.0;
        if (pizzas.size() > PIZZA_AMOUNT_FOR_DISCOUNT) {
            discount = pizzaPricesStream().max().getAsDouble() * THIRTY_PERCENT_MULTIPLIER;
        }
        System.out.println("Pizza amount discount = " + discount);
        return discount;
    }

    private DoubleStream pizzaPricesStream() {
        return pizzas.stream().mapToDouble(Pizza::getPrice);
    }
}

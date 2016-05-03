package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
@Entity
@Table(name = "ORDERS", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class Order implements OrderActions {
    public static final double THIRTY_PERCENT_MULTIPLIER = 0.3;
    public static final int PIZZA_AMOUNT_FOR_DISCOUNT = 4;
    private static List<Order> orders = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    Integer id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;
    // static AtomicLong id = new AtomicLong(0);

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinTable(name = "ORDER_PIZZA", joinColumns = {
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
    @JoinColumn(name = "PIZZA_ID", referencedColumnName = "ID") })
    // @JoinColumn(name = "PIZZA_ID", nullable = false)
    List<Pizza> pizzas;

    public Order() {
    }

    public Order(Customer customer, List<Pizza> pizzas) {
        this.customer = customer;
        this.pizzas = pizzas;
        // this.number = id.incrementAndGet();
        this.status = Status.NEW;
        // TODO: I've commented the piece because for it to be implemented the get has to be
        // executes. I've got to make either Transaction in transaction or spread it into 2 actions.
        // customer.getPromoCard().setBlockedAmount(countTotalPriceWithPossiblePizzaAmountDiscount());
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
        return "Order{" + "customer=" + customer + ", pizzas="
        // TODO: pizzas are not got while saving order
        // + pizzas.stream().map(Pizza::getName).collect(Collectors.joining(", "))
                + ", order id=" + id + ", order status=" + status + '}';
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}

package domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */

// @BenchMark
@Entity
@Table(name = "PIZZAS")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE", nullable = true, length = 20, precision = 2)
    private double price;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "ORDER_ID", nullable = false)
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "pizzas", cascade = {CascadeType.PERSIST})
    private List<Order> orders;

    // private double price;
    // private PizzaType type;

    // public Pizza(String name) {
    // this.name = name;
    // }

    public Pizza() {
    }

    public Pizza(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Pizza(String name, double price) {
        this.price = price;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    @BenchMark
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Pizza{" + "name='" + name + '\'' + ", price='" + price + '\'' + ", id=" + id + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Pizza))
            return false;

        Pizza pizza = (Pizza) o;

        return Double.compare(pizza.price, price) == 0 && name.equals(pizza.name);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}

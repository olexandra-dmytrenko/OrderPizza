package domain;

import javax.persistence.*;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */

// @BenchMark
@Entity
@Table(name = "PIZZAS")
public class Pizza {

    @Column(name = "NAME")
    private final String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PIZZA_ID")
    private Integer id;
    @Column(name = "PRICE", nullable = true, length = 20, precision = 2)
    private double price;
    // private double price;
    // private PizzaType type;

    // public Pizza(String name) {
    // this.name = name;
    // }

    public Pizza(String name, int id) {
        this.name = name;
        this.id = id;
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
}

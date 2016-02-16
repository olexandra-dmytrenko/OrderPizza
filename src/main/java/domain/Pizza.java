package domain;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */

//@BenchMark
public class Pizza {

    private int id;
    private final String name;
    private double price;
//    private double price;
    //  private PizzaType type;

//    public Pizza(String name) {
//        this.name = name;
//    }

    public Pizza(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
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
        return "Pizza{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", id=" + id +
                '}';
    }
}

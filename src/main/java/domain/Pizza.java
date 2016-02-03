package domain;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class Pizza {


    private final String name;
    private int id;
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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}

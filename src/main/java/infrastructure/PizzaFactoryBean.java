package infrastructure;

import domain.Pizza;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by Oleksandra_Dmytrenko on 2/3/2016.
 */
// in <> write which object will be returned
public class PizzaFactoryBean implements FactoryBean<Pizza> {
    private String name;
    private int id;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    @Required
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Required
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Pizza getObject() throws Exception {
        System.out.println("PizzaFactoryBean: getObject");
        Pizza pizza = new Pizza(getName(), getId());
        pizza.setPrice(getPrice());
        return pizza;
    }

    @Override
    public Class<?> getObjectType() {
        System.out.println("PizzaFactoryBean: getObjectType");
        return Pizza.class;
    }

    // this is type of Pizza, that's why it's singleton
    @Override
    public boolean isSingleton() {
        System.out.println("PizzaFactoryBean: isSingleton");
        return false;
    }
}

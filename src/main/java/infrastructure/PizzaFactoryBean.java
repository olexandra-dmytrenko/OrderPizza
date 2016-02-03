package infrastructure;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.FactoryBean;

import domain.Pizza;

/**
 * Created by Oleksandra_Dmytrenko on 2/3/2016.
 */
// in <> write which object will be returned
public class PizzaFactoryBean implements FactoryBean<Pizza> {
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Pizza getObject() throws Exception {
        return new Pizza(null, 1);
    }

    @Override
    public Class<?> getObjectType() {
        return Pizza.class;
    }

    // this is type of Pizza, that's why it's singleton
    @Override
    public boolean isSingleton() {
        return false;
    }
}

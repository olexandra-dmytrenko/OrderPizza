package service;

import domain.order.InMemOrderRepository;
import domain.pizza.InMemPizzaRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class JavaConfig {
    public static final Map<String, Object> ifc2Class;

    static {
        ifc2Class = new HashMap<>();
        ifc2Class.put("pizzaRepository", new InMemPizzaRepository());
        ifc2Class.put("orderRepository", new InMemOrderRepository());
    }

//    public <T> Class<T> getImpl(String impl) {
//        return ifc2Class.get(impl);
//    }
    public Object getImpl(String impl) {
        return ifc2Class.get(impl);
    }
 }

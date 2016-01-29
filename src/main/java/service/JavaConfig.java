package service;

import infrastructure.Config;
import repository.InMemOrderRepository;
import repository.InMemPizzaRepository;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class JavaConfig implements Config {
    private Map<String, Class<?>> ifc2Class = new HashMap<>();

    public JavaConfig(HashMap<Object, Object> beans) {
        ifc2Class.put("pizzaRepository", InMemPizzaRepository.class);
        ifc2Class.put("orderRepository", InMemOrderRepository.class);
        ifc2Class.put("pizzaService", SimplePizzaService.class);
        ifc2Class.put("orderService", SimpleOrderService.class);
    }

    @Override
    public <T> Class<T> getImpl(String impl) {
        return (Class<T>) ifc2Class.get(impl);
    }
}

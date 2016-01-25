package service;

import domain.customer.Customer;
import domain.order.Order;
import domain.pizza.Pizza;
import repository.OrderRepository;
import repository.PizzaRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class SimpleOrderService implements Service {
    private OrderRepository orderRepository;// = new InMemOrderRepository();
    private PizzaRepository pizzaRepository; //= new InMemPizzaRepository();
    private SimplePizzaService simplePizzaService = new SimplePizzaService();

    public SimpleOrderService() {
        JavaConfig config = new JavaConfig();
        orderRepository = (OrderRepository) config.getImpl("orderRepository");
        pizzaRepository = (PizzaRepository) config.getImpl("pizzaRepository");
    }

    public Order placeNewOrder(Customer customer, Integer... pizzasID) {
        List<Pizza> pizzas = new ArrayList();

        for (Integer id : pizzasID) {
            pizzas.add(find(id)); // get Pizza from predifined in-memory list
        }
        Order newOrder = new Order(customer, pizzas);

        saveOrder(newOrder); // set Order Id and save Order to in-memory list
        return newOrder;
    }

    private Order saveOrder(Order newOrder) {
        return orderRepository.saveOrder(newOrder);
    }

//    private Pizza find(int id) {return Pizza.getPizzas().get(id);}

    private Pizza find(int id) {
//        return pizzaRepository.find(id);
        return simplePizzaService.find(id);
    }
}

package service;

import domain.Customer;
import domain.Order;
import domain.Pizza;
import repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class SimpleOrderService implements OrderService {
    Order order;
    private OrderRepository orderRepository;// = new InMemOrderRepository();
    //    private PizzaRepository pizzaRepository; //= new InMemPizzaRepository();
    private PizzaService simplePizzaService;// = new SimplePizzaService();

    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService) {
        this.orderRepository = orderRepository;
        this.simplePizzaService = pizzaService;

    }

    public SimpleOrderService() {
//        JavaConfig config = new JavaConfig();
//        orderRepository = (OrderRepository) config.getImpl("orderRepository");
        //      pizzaRepository = (PizzaRepository) config.getImpl("pizzaRepository");
    }

    @Override
    public Order placeNewOrder(Customer customer, int... pizzasID) {
        List<Pizza> pizzas = new ArrayList();

        for (Integer id : pizzasID) {
            pizzas.add(findPizzaByID(id)); // get Pizza from predifined in-memory list
        }
        Order newOrder = new Order(customer
                , pizzas
                );

        saveOrder(newOrder); // set Order Id and save Order to in-memory list
        return newOrder;
    }

    private Order saveOrder(Order newOrder) {
        return orderRepository.saveOrder(newOrder);
    }

    private Pizza findPizzaByID(int id) {
//        return Pizza.getPizzas().get(id);
//        return pizzaRepository.findPizzaByID(id);
        return simplePizzaService.find(id);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return Order.getOrders().toString();
    }
}

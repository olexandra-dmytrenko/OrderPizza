package service;

import domain.Customer;
import domain.Order;
import domain.Pizza;
import infrastructure.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import repository.OrderRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
// put abstract infront of class
public class SimpleOrderService implements OrderService// , ApplicationContextAware
{
    Order order;
    private OrderRepository orderRepository;// = new InMemOrderRepository();
    // private PizzaRepository pizzaRepository; //= new InMemPizzaRepository();
    private PizzaService simplePizzaService;// = new SimplePizzaService();
    private ApplicationContext appContext;

    @Autowired
    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService) {
        this.orderRepository = orderRepository;
        this.simplePizzaService = pizzaService;

    }

    public SimpleOrderService() {
        // JavaConfig config = new JavaConfig();
        // orderRepository = (OrderRepository) config.getImpl("orderRepository");
        // pizzaRepository = (PizzaRepository) config.getImpl("pizzaRepository");
    }

    public void setAppContext(ApplicationContext appContext) {
        this.appContext = appContext;
    }

    @Override
    public Order placeNewOrder(Customer customer, int... pizzasID)
            throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        List<Pizza> pizzas = new ArrayList();

        for (Integer id : pizzasID) {
            pizzas.add(findPizzaByID(id)); // get Pizza from predifined in-memory list
        }
        Order newOrder = createNewOrder();
        newOrder.setCustomer(customer);
        newOrder.setPizzas(pizzas);// new Order(customer, pizzas);

        saveOrder(newOrder); // set Order Id and save Order to in-memory list
        return newOrder;
    }

    //abstract
    Order createNewOrder() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
     return (Order) appContext.getBean("order");}
    // return new Order();

    private Order saveOrder(Order newOrder) {
        return orderRepository.saveOrder(newOrder);
    }

    private Pizza findPizzaByID(int id) {
        // return Pizza.getPizzas().get(id);
        // return pizzaRepository.findPizzaByID(id);
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
        return order.getOrders().toString();
    }
}

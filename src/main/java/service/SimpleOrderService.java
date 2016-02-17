package service;

import domain.*;
import infrastructure.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.OrderRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
// put abstract infront of class
    @Service
public abstract class SimpleOrderService implements OrderService// , ApplicationContextAware
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

//    public SimpleOrderService() {
    // JavaConfig config = new JavaConfig();
    // orderRepository = (OrderRepository) config.getImpl("orderRepository");
    // pizzaRepository = (PizzaRepository) config.getImpl("pizzaRepository");
    //   }

    public void setAppContext(ApplicationContext appContext) {
        this.appContext = appContext;
    }

    @Override
    public Order placeNewOrder(Customer customer, List<PizzaAmount> pizzaAmountList)
            throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        List<Pizza> pizzas = new ArrayList<>();
        fillPizzasList(pizzaAmountList, pizzas);
        //Order newOrder = createNewOrder();
//        newOrder.setCustomer(customer);
//        newOrder.setPizzas(pizzas);// new Order(customer, pizzas);
        Order newOrder = new Order(customer, pizzas);
        System.out.println("ORDER = " + newOrder.toString());
        saveOrder(newOrder); // set Order Id and save Order to in-memory list
        return newOrder;
    }

    private void fillPizzasList(List<PizzaAmount> pizzaAmountList, List<Pizza> pizzas) {
        int allPizzasAmount = Utils.countPizzaAmount(pizzaAmountList);
        (new Utils() {}).ifPizzaAmountIsGreaterThanMax(allPizzasAmount);
        for (PizzaAmount pizzaAmount : pizzaAmountList) {
            for (int amount = 1; amount <= pizzaAmount.getAmount(); amount++) {
                pizzas.add(findPizzaByID(pizzaAmount.getPizzaId())); // get Pizza from predifined in-memory list
            }
        }
    }

    @Override
    public double countTotalPrice() {
        return order.countTotalPrice();
    }

    @Override
    public double countTotalPriceWithPossibleDiscount() {
        return order.countTotalPriceWithPossibleDiscount();
    }

    public abstract Order createNewOrder() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException
            ;
    //  {return (Order) appContext.getBean("order");}
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
        return "All orders: " + Order.getOrders().toString();
    }
}

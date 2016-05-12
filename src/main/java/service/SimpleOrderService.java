package service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.*;
import repository.OrderRepository;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
// put abstract in front of class

@Service
public class SimpleOrderService implements OrderService {

    @Autowired
    Order order;
    @Autowired
    private OrderRepository orderRepository;// = new InMemOrderRepository();
    @Autowired
    private PizzaService simplePizzaService;// = new SimplePizzaService();
    @Autowired
    private CustomerService customerService;

    @Autowired
    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService,
            CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.simplePizzaService = pizzaService;
        this.customerService = customerService;
    }

    // @Transactional
    @Override
    public Order placeNewOrder(Customer customer, List<PizzaAmount> pizzaAmountList)
            throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Customer customerFromDB = customerService.find(customer.getName());
        if (customerFromDB != null) {
            customer = customerFromDB;
        }

        List<Pizza> pizzas = new ArrayList<>();
        fillPizzasList(pizzaAmountList, pizzas);
        order = new Order(customer, pizzas);

        customer.addOrder(order);
        System.out.println("ORDER = " + order.toString());
        return saveOrder(order); // set Order Id and save Order to in-memory list
    }

    private void fillPizzasList(List<PizzaAmount> pizzaAmountList, List<Pizza> pizzas) {
        int allPizzasAmount = Utils.countPizzaAmount(pizzaAmountList);
        (new Utils() {
        }).ifPizzaAmountIsGreaterThanMax(allPizzasAmount);
        for (PizzaAmount pizzaAmount : pizzaAmountList) {
            for (int amount = 1; amount <= pizzaAmount.getAmount(); amount++) {
                pizzas.add(findPizzaByID(pizzaAmount.getPizzaId())); // get Pizza from predifined
                // in-memory list
            }
        }
    }

    @Override
    public double countTotalPrice() {
        return order.countTotalPriceNoDiscounts();
    }

    public double countTotalPriceWithPossibleDiscounts() {
        return countTotalPrice() - order.getPizzaAmountDiscount() - order.getPromoDiscount();
    }

    public Order createNewOrder() {
        return null;
    }

    private Order saveOrder(Order newOrder) {
        return orderRepository.save(newOrder);
    }

    private Pizza findPizzaByID(int id) {
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

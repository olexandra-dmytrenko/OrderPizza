package run;

import infrastructure.ApplicationContext;
import infrastructure.JavaConfig;
import infrastructure.JavaConfigApplicationContext;
import repository.PizzaRepository;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class DeliveryApp {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//        Customer customer = null;
//        Order order;
//
//        SimpleOrderService orderService = new SimpleOrderService();
//        order = orderService.placeNewOrder(customer, 1, 2, 3);
//
//        System.out.println(order);
        // first in hierarchy object to run the others
        ApplicationContext context = new JavaConfigApplicationContext(new JavaConfig());
        PizzaRepository pizzaRepository = (PizzaRepository) context.getBean("pizzaRepository"); //names should be same but from lower case
        System.out.println(pizzaRepository.find(1).getName());
        System.out.println(pizzaRepository.initializeMessage());

//        OrderService orderService = (OrderService) context.getBean("orderService");
//        Order order = orderService.placeNewOrder(new Customer("Olexandra"), 3, 2, 5);
//        System.out.println(order);

        //  getBean(orderService)

    }

}

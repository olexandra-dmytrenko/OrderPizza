package run;

import domain.customer.Customer;
import domain.order.Order;
import service.SimpleOrderService;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class PizzaApp {
    public static void main(String[] args) {
        Customer customer = null;
        Order order;

        SimpleOrderService orderService = new SimpleOrderService();
        order = orderService.placeNewOrder(customer, 1, 2, 3);

        System.out.println(order);
    }

}

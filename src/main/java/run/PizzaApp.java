package run;

import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import repository.InMemOrderRepository;
import domain.Order;
import repository.InMemPizzaRepository;
import service.SimpleOrderService;
import service.SimplePizzaService;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class PizzaApp {
    public static void main(String[] args) {
        Customer customer = null;
        Order order;

//        SimpleOrderService orderService = new SimpleOrderService();
//        SimpleOrderService orderService = new SimpleOrderService(new InMemOrderRepository(), new SimplePizzaService(new InMemPizzaRepository()));
//        order = orderService.placeNewOrder(customer, 1, 2, 3);

 //       System.out.println(order);
    }

}

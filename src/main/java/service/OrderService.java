package service;

import domain.Customer;
import domain.Order;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public interface OrderService {
    Order placeNewOrder(Customer customer, int... pizzasID);
//    default Order placeNewOrder(Customer customer, int... pizzasID){
//        List<Pizza> pizzas = new ArrayList();
//
//        for (Integer id : pizzasID) {
//            pizzas.add(findPizzaByID(id)); // get Pizza from predifined in-memory list
//        }
//        Order newOrder = new Order(customer
//                , pizzas
//        );
//
//        saveOrder(newOrder); // set Order Id and save Order to in-memory list
//        return newOrder;
//
//        placeNewOrder(new Order(customer, pizzaID));
//    }
//    default Order placeNewOrder(Order order){};
}

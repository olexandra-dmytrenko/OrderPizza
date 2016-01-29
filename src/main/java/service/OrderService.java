package service;

import domain.Customer;
import domain.Order;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public interface OrderService {
    Order placeNewOrder(Customer customer, int... pizzaID);

}

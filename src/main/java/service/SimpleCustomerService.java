package service;

import domain.Customer;
import domain.Order;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class SimpleCustomerService {
    public Customer createCustomer(String name) {
        return new Customer(name);
    }
}

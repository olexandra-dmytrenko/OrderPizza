package service;

import domain.Customer;

/**
 * Created by olexandra on 4/26/16.
 */
public interface CustomerService {
    Customer find(String name);

    Customer save(Customer customer);
}

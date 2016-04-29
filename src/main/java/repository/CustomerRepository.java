package repository;

import domain.Customer;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public interface CustomerRepository {

    Customer find(String name);

    Customer save(Customer customer);
}

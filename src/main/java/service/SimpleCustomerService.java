package service;

import domain.Customer;
import repository.CustomerRepository;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class SimpleCustomerService implements CustomerService {

    CustomerRepository customerRepository;

    public SimpleCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer find(String name) {
        return customerRepository.find(name);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}

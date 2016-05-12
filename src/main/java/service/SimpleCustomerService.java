package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Customer;
import repository.CustomerRepository;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
@Service
public class SimpleCustomerService implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    public SimpleCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer find(String name) {
        return customerRepository.findByName(name);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}

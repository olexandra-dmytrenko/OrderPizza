package repository;

import domain.Address;
import domain.Customer;
import domain.Order;
import domain.PizzaAmount;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.CustomerService;
import service.OrderService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Oleksandra_Dmytrenko on 4/18/2016.
 */
@Rollback(true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/database/DataSource.xml", "classpath:/database/Hibernate.xml",
        "classpath:/database/RepositoryContextJPA.xml" })
@ActiveProfiles("win")
public class JPAOrderRepositoryTest {
    @Autowired
    OrderService orderService;
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private CustomerService customerService;

    private Customer prepareCustomer() {
        Customer newCustomer = new Customer("Vasyly10");
        Address address = new Address("Budapest", "Hungary");
        address.setCustomer(newCustomer);
        newCustomer.addAddress(address);
        assertNull(newCustomer.getId());
        return newCustomer;
    }

    /**
     * Works when there is Persist for Customer. But this Persist prevents from merging. So I've deleted it and save customer first than order or use old customer.
     *Then I've updated the logic of saving order and it started working! :) THe test passes when the customer is both new and old.
     * @throws Exception
     */
    @Test
    @Rollback(true)
//    @Ignore
    public void testSaveNewOrderAndCustomer() throws Exception {
        Customer newCustomer = prepareCustomer();
        assertNull(customerService.find(newCustomer.getName()));
        Order order = orderService.placeNewOrder(newCustomer, Arrays.asList(new PizzaAmount(1, 2)));
        assertNotNull(order.getId());
        assertNotNull(newCustomer.getId());
    }

    @Test
    @Rollback(true)
    public void testSaveNewOrderAndOldCustomer() throws Exception {
        Customer newCustomer = prepareCustomer();
        customerService.save(newCustomer);
        assertNotNull(newCustomer.getId());
        Order order = orderService.placeNewOrder(newCustomer, Arrays.asList(new PizzaAmount(1, 2)));
        assertNotNull(order.getId());
    }
}
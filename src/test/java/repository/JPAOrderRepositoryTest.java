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
        Customer newCustomer = new Customer("Vasyl3");
        Address address = new Address("Budapest", "Hungary");
        address.setCustomer(newCustomer);
        newCustomer.addAddress(address);
        assertNull(newCustomer.getId());
        return newCustomer;
    }

    @Test
    @Rollback(true)
    @Ignore
    public void testSaveNewOrderAndCustomer() throws Exception {
        Customer newCustomer = prepareCustomer();
        Order order = orderService.placeNewOrder(newCustomer, Arrays.asList(new PizzaAmount(1, 2)));
        assertNotNull(order.getId());
    }

    @Test
    @Rollback(false)
    public void testSaveNewOrderAndOldCustomer() throws Exception {
        Customer newCustomer = prepareCustomer();
        customerService.save(newCustomer);
        assertNotNull(newCustomer.getId());
        Order order = orderService.placeNewOrder(newCustomer, Arrays.asList(new PizzaAmount(1, 2)));
        assertNotNull(order.getId());
    }
}
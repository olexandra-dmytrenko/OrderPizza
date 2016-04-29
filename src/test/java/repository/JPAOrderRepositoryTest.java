package repository;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Address;
import domain.Customer;
import domain.Order;
import domain.PizzaAmount;
import service.CustomerService;
import service.OrderService;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Oleksandra_Dmytrenko on 4/18/2016.
 */
@Rollback(false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/database/DataSource.xml", "classpath:/database/Hibernate.xml",
        "classpath:/database/RepositoryContextJPA.xml" })
@ActiveProfiles("h2")
public class JPAOrderRepositoryTest {
    @Autowired
    OrderService orderService;
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private CustomerService customerService;

    @Test
    public void testSave() throws Exception {
        Customer olga = new Customer("Olga13");
        Address address = new Address("Kyiv", "Ukraine");
        address.setCustomer(olga);
        olga.addAddress(address);
        customerService.save(olga);
        assertNotNull(olga.getId());

        Order order = orderService.placeNewOrder(olga, Arrays.asList(new PizzaAmount(1, 2)));
        assertNotNull(order.getId());
    }
}
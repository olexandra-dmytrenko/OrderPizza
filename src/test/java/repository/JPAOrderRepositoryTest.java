package repository;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.OrderService;
import service.PizzaService;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Oleksandra_Dmytrenko on 4/18/2016.
 */
@Rollback(false)
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
    private PizzaService pizzaService;

    @Test
    public void testSave() throws Exception {
        Order order = new Order();
        Customer olga = new Customer("Olga");
        olga.addAddress(new Address("Kyiv", "Ukraine"));
        order.setCustomer(olga);
        order.setPizzas(Arrays.asList(new Pizza()));
        order = orderService.placeNewOrder(new Customer("Olga"), Arrays.asList(new PizzaAmount(1, 2)));
        em.flush();
        assertNotNull(order.getId());
    }
}
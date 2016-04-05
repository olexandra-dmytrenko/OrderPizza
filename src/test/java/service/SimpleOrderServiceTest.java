package service;

import domain.Customer;
import domain.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by olexandra on 2/5/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
// для того, чтоб джейюнит автоматом понимал, что надо использовать спринг
@ContextConfiguration(locations = {
        "classpath:/appContext.xml",
        "classpath:/repoContext.xml"
})
public class SimpleOrderServiceTest {

    private final ConfigurableApplicationContext appContext;
    private final ConfigurableApplicationContext appContextParent;
    @Autowired
    private OrderService orderService;
    @Autowired
    private Order order;

    public SimpleOrderServiceTest() {
        this.appContextParent
                // = new ClassPathXmlApplicationContext("src/test/repository/appContext.xml");
                = new ClassPathXmlApplicationContext(new String[]{"springForLocalRepos/repoContext.xml"});
        // указали что у апп контекста есть родитель репозитори контекст
        this.appContext = new ClassPathXmlApplicationContext(
                new String[]{"springForLocalRepos/appContext.xml"}, appContextParent);
    }

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPlaceNewOrder() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Customer customer = null;
        int[] pizzas = {1};
//        OrderService orderService = (OrderService) appContext.getBean("orderService");
        // Order order = (Order) appContext.getBean("order1");
        //Order order = orderService.placeNewOrder(customer, pizzas);
        assertNotNull(order);
    }
}
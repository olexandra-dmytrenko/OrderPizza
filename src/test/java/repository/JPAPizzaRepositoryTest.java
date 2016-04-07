package repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Pizza;
import service.PizzaService;

/**
 * Created by Oleksandra_Dmytrenko on 4/7/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/database/DataSource.xml", "classpath:/database/Hibernate.xml",
        "classpath:/database/RepositoryContextJPA.xml" })
public class JPAPizzaRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private PizzaService pizzaService;

    @Test
    public void testFind() throws Exception {

    }

    @Test
    public void testFindAll() throws Exception {

    }

    @Test
    public void testSave() throws Exception {
        Pizza pizza = new Pizza("Verona", 156.00);
        Assert.assertNull("Pizza wasn't saved to the DB Correctly", pizza.getId());
        Pizza result = pizzaService.save(pizza);
        em.flush();
        System.out.println(result.getId());

        Assert.assertNotNull("Pizza wasn't saved to the DB Correctly", result.getId());
    }
}
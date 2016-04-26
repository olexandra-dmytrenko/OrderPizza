package repository;

import domain.Pizza;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.PizzaService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Oleksandra_Dmytrenko on 4/7/2016.
 */
@Rollback(false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/database/DataSource.xml", "classpath:/database/Hibernate.xml",
        "classpath:/database/RepositoryContextJPA.xml"})
@ActiveProfiles("mac")
public class JPAPizzaRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private PizzaService pizzaService;

    @Test
    public void testFind() throws Exception {
        final String insertQuery = "INSERT INTO pizzas (name, price) VALUES ('Margarita', 127.99)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS), keyHolder);
        Integer id = keyHolder.getKey().intValue();
        pizzaService.find(id);
//        em.getTransaction().commit();
        System.out.println(id);
        assertNotNull(id);
    }

    @Test
    public void testFindAll() throws Exception {
        final String insertQuery = "INSERT INTO pizzas (name, price) VALUES ('Margarita', 127.99)";
        jdbcTemplate.execute(insertQuery);
        List<Pizza> pizzas = pizzaService.findAll();
//        em.getTransaction().commit();
        pizzas.stream().forEach(System.out::println);
        Pizza insertedPizza = new Pizza("Margarita", 127.99);
        assertTrue(pizzas.contains(insertedPizza));

    }

    @Test
    @Rollback(true)
    public void testUpdate() throws Exception {
        Pizza pizza = new Pizza("Updatable Pizza", 200.50);
        Assert.assertNull("This is not newly created pizza and it has id" + pizza, pizza.getId());
        Pizza result = pizzaService.save(pizza);
        em.flush();
        assertNotNull("Pizza wasn't saved to the DB Correctly", result.getId());
        pizza = new Pizza("Updatable Pizza", 120.10);
        result = pizzaService.save(pizza);
        em.flush();
        System.out.println(result.getPrice());

        assertEquals("Pizza wasn't saved to the DB Correctly", result.getPrice(), 120.10, 0.001);
    }

    @Test
    @Rollback(true)
    public void testSave() throws Exception {
        Pizza pizza = new Pizza("New Pizza", 100.01);
        Assert.assertNull("This is not newly created pizza and it has id", pizza.getId());
        Pizza result = pizzaService.save(pizza);
        em.flush();
        System.out.println(result.getId());
        assertNotNull("Pizza wasn't saved to the DB Correctly", result.getId());
    }
}
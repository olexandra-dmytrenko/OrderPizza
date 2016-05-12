package repository;

import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Pizza;
import service.PizzaService;

import static org.junit.Assert.*;

/**
 * Created by Oleksandra_Dmytrenko on 4/7/2016.
 */
@Rollback(true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/database/DataSource.xml", "classpath:/database/Hibernate.xml",
        "classpath:/database/RepositoryContextJPA.xml" })
@ActiveProfiles("win")
public class JPAPizzaRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static Logger log = LoggerFactory.getLogger(JPAPizzaRepositoryTest.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private PizzaService pizzaService;

    @Test
    public void testFind() throws Exception {
        final String insertQuery = "INSERT INTO pizzas (name, price) VALUES ('Margarita', 128.88)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS), keyHolder);
        Integer id = keyHolder.getKey().intValue();
        Pizza foundPizza = pizzaService.find(id);
        log.info("Id of inserted item = {}", id);
        assertNotNull(id);
        Pizza insertedPizza = new Pizza("Margarita", 128.88);
        insertedPizza.setId(id);
        assertEquals(foundPizza, insertedPizza);
    }

    @Test
    public void testFindAll() throws Exception {
        final String insertQuery = "INSERT INTO pizzas (name, price) VALUES ('Margarita', 125.55)";
        jdbcTemplate.execute(insertQuery);
        List<Pizza> pizzas = pizzaService.findAll();
        pizzas.forEach(p -> log.info("Pizza from DB: {}", p));
        Pizza insertedPizza = new Pizza("Margarita", 125.55);
        assertTrue(pizzas.stream().anyMatch(insertedPizza::equals));
    }

    @Test
    @Rollback(true)
    public void testUpdate() throws Exception {
        long pizzasAmountStart = pizzaService.count();
        log.info("Amount of pizzas before insert = {}", pizzasAmountStart);
        Pizza pizza = new Pizza("Updatable Pizza", 200.50);
        Assert.assertNull("This is not newly created pizza and it has id" + pizza, pizza.getId());
        Pizza result = pizzaService.save(pizza);
        long pizzasAmountEnd = pizzaService.count();
        log.info("Amount of pizzas after insert = {}", pizzasAmountEnd);
        assertNotNull("Pizza wasn't saved to the DB Correctly", result.getId());
        assertEquals("The amount of pizzas didn't grow on one", pizzasAmountEnd - pizzasAmountStart, 1);
        assertEquals("Pizza wasn't saved to the DB Correctly", result.getPrice(), 200.50, 0.001);
    }

    @Test
    @Rollback(false)
    public void testSave() throws Exception {
        Pizza pizza = new Pizza("New Pizza", 100.01);
        Assert.assertNull("This is not newly created pizza and it has id", pizza.getId());
        Pizza result = pizzaService.save(pizza);
        em.flush();
        System.out.println(result.getId());
        assertNotNull("Pizza wasn't saved to the DB Correctly", result.getId());
    }
}
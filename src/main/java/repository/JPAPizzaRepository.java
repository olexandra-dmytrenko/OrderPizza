package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import domain.Pizza;

/**
 * Created by Oleksandra_Dmytrenko on 4/6/2016.
 */
public class JPAPizzaRepository implements PizzaRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public Pizza find(int id) {
        return em.find(Pizza.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pizza> findAll() {
        return em.unwrap(Session.class).createCriteria(Pizza.class).list();
        // return em.createQuery("SELECT * from PIZZA", Pizza.class).getResultList();
    }

    @Transactional
    public Pizza updatePizzaWithId(Pizza pizza) {
        try {
            Pizza oldPizza = em.createQuery("FROM domain.Pizza WHERE name = :name", Pizza.class)
                    .setParameter("name", pizza.getName()).getSingleResult();
            pizza.setId(oldPizza.getId());

        } catch (NoResultException e) {
            LoggerFactory.getLogger(this.getClass()).info("The new {} will be created ", pizza);
        }
        return pizza;
    }

    @Override
    @Transactional
    public Pizza save(Pizza pizza) {
        pizza = updatePizzaWithId(pizza);
        if (pizza.getId() == null) {
            em.persist(pizza);
        } else
            em.merge(pizza);
        return pizza;
    }
}

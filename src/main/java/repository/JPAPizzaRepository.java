package repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import domain.Pizza;

/**
 * Created by Oleksandra_Dmytrenko on 4/6/2016.
 */
@Repository
public class JPAPizzaRepository implements PizzaRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Pizza save(Pizza pizza) {
        if (pizza.getId() == null) {
            em.persist(pizza);
        } else
            em.merge(pizza);
        return pizza;
    }

    @Override
    @Transactional
    public Pizza find(int id) {
        return null;
    }

}

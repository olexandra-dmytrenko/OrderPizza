package repository;

import domain.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Oleksandra_Dmytrenko on 4/18/2016.
 */
public class JPAOrderRepository implements OrderRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Order save(Order newOrder) {
//        em.refresh(newOrder);
        if (newOrder.getId() == null) {
            em.persist(newOrder);
        } else
            em.merge(newOrder);
        return newOrder;
    }
}

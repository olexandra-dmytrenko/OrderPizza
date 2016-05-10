package repository;

import domain.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Oleksandra_Dmytrenko on 4/18/2016.
 */
public interface JPAOrderRepository extends OrderRepository, CrudRepository<Order, Integer> {
    /*@PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Order save(Order newOrder) {
        if (newOrder.getId() == null && newOrder.getCustomer().getId() == null) {
            em.persist(newOrder);
        } else
            newOrder = em.merge(newOrder);
        em.flush();
        return newOrder;
    }*/
}
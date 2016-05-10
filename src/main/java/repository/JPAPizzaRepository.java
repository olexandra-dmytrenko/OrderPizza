package repository;

import domain.Pizza;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Oleksandra_Dmytrenko on 4/6/2016.
 */
public interface JPAPizzaRepository extends PizzaRepository, CrudRepository<Pizza, Integer> {
//
//    @Override
//    public default Pizza findBy(int id) {
//        return findBy(id);
//    }
//
//    Pizza findBy(int id);

//    @Override
//    public List<Pizza> findAll() {
//        return findBy();
//        // return em.createQuery("SELECT * from PIZZA", Pizza.class).getResultList();
//    }
//
//
//    @Transactional
//    public Pizza updatePizzaWithId(Pizza pizza) {
//        try {
//            Pizza oldPizza = em.createQuery("FROM domain.Pizza WHERE name = :name", Pizza.class)
//                    .setParameter("name", pizza.getName()).getSingleResult();
//            pizza.setId(oldPizza.getId());
//
//        } catch (NoResultException e) {
//            LoggerFactory.getLogger(this.getClass()).info("The new {} will be created ", pizza);
//        }
//        return pizza;
//    }
//
//    @Override
//    @Transactional
//    public Pizza save(Pizza pizza) {
//        pizza = updatePizzaWithId(pizza);
//        if (pizza.getId() == null) {
//            em.persist(pizza);
//        } else
//            em.merge(pizza);
//        return pizza;
//    }
}

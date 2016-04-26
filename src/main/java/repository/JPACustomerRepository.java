package repository;

import domain.Customer;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by olexandra on 4/26/16.
 */
public class JPACustomerRepository implements CustomerRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Customer find(String name) {
        return null;
    }
}

package repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import domain.Customer;

/**
 * Created by olexandra on 4/26/16.
 */
public class JPACustomerRepository implements CustomerRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Customer find(String name) {

//        SessionFactory sessionFactory = em.unwrap(SessionFactory.class);
        Session session = em.unwrap(Session.class);
//        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Customer customer = (Customer) session.bySimpleNaturalId(Customer.class).load(name);
//        session.getTransaction().commit();
        System.out.println(customer);
        return customer;
    }
}

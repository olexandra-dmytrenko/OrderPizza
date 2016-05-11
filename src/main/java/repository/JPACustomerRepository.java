package repository;

import domain.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by olexandra on 4/26/16.
 */
public interface JPACustomerRepository extends  CrudRepository<Customer, Integer> {

/*
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Customer findById(String name) {

        // SessionFactory sessionFactory = em.unwrap(SessionFactory.class);
        Session session = em.unwrap(Session.class);
        // Session session = sessionFactory.openSession();
        session.beginTransaction();

        Customer customer = (Customer) session.bySimpleNaturalId(Customer.class).load(name);
        System.out.println(customer);
        return customer;
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        Customer customerInDB = findById(customer.getName());
        if (!em.contains(customer) && customerInDB == null) {
            em.persist(customer);
        } else if (customerInDB != null) {
            updateIdsFromDBCustomer(customer, customerInDB);
            em.merge(customer);
        }
        em.flush();
        return customer;
    }

    private void updateIdsFromDBCustomer(Customer customer, Customer customerInDB) {
        customer.setId(customerInDB.getId());
        customer.getPromoCard().setId(customerInDB.getPromoCard().getId());
        customer.getAddresses().stream()
                .forEach(address -> address.setId
                        (customerInDB.getAddresses().stream()
                                .filter(adFromDB -> (
                                        adFromDB.getCountry().equals(address.getCountry())
                                                && adFromDB.getCity().equals(address.getCity()))).findAny().get().getId())
                );
    }
*/
}

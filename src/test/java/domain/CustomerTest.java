package domain;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hibernate.HibernateUtil;
import junit.framework.TestCase;

/**
 * Created by Oleksandra_Dmytrenko on 4/4/2016.
 */
public class CustomerTest extends TestCase {
    private static Logger log = LoggerFactory.getLogger(CustomerTest.class);
    private static Session session = null;

    public void setUp() throws Exception {
        // Get Session
        session = HibernateUtil.getSessionAnnotationFactory().getCurrentSession();
    }

    public void testSetCustomer() throws Exception {
        Customer customer = new Customer("Valera");
        Address address = new Address("Buharest", "Romania");
        customer.setAddress(address);
        PromoCard card = new PromoCard(238);
        card.setBlockedAmount(32);
        // you've got to do that so that promocard when written has the place to take the id from
        customer.setPromoCard(card);
        card.setCustomer(customer);
        address.setCustomer(customer);

        session.beginTransaction();
        long numbOfLinesBeforeAdd = ((Long) session.createCriteria("domain.Customer")
                .setProjection(Projections.rowCount()).uniqueResult()).intValue();
        session.save(customer);
        session.save(customer);
        long numbOfLinesAfterAdd = ((Long) session.createCriteria("domain.Customer")
                .setProjection(Projections.rowCount()).uniqueResult()).intValue();
        session.getTransaction().commit();
        assertEquals(numbOfLinesBeforeAdd + 1, numbOfLinesAfterAdd);
    }

    public void tearDown() throws Exception {

    }

    public void testDeleteCustomerAndHisPromo() throws Exception {
        // given
        session.beginTransaction();

        // values to check before modification
        long numbOfCustomerLinesBeforeDelete = ((Long) session.createCriteria("domain.Customer")
                .setProjection(Projections.rowCount()).uniqueResult()).intValue();
        long numbOfPromoLinesBeforeDelete = (Long) session.createCriteria("domain.PromoCard")
                .setProjection(Projections.rowCount()).uniqueResult();
        Number lastIdBeforeDelete = ((Number) session.createCriteria("domain.Customer")
                .setProjection(Projections.max("id")).uniqueResult());

        // when
        Customer customer = (Customer) session.load(Customer.class, lastIdBeforeDelete);
         log.info("The customer who gets deleted:{}", customer.toString());
        session.delete(customer);
        // to sync the session in memory
        session.flush();

        // then
        // values to check after modification
        long numbOfCustomerLinesAfterDelete = (Long) session.createCriteria("domain.Customer")
                .setProjection(Projections.rowCount()).uniqueResult();
        long numbOfPromoLinesAfterDelete = (Long) session.createCriteria("domain.PromoCard")
                .setProjection(Projections.rowCount()).uniqueResult();
        Number lastIdAfterDelete = (Number) session.createCriteria("domain.Customer")
                .setProjection(Projections.max("id")).uniqueResult();
        session.getTransaction().commit();

        // checks
        assertEquals(numbOfCustomerLinesBeforeDelete - 1, numbOfCustomerLinesAfterDelete);
        assertEquals(numbOfPromoLinesBeforeDelete - 1, numbOfPromoLinesAfterDelete);
        if (numbOfCustomerLinesAfterDelete > 0) {
            assertTrue("The last id is greater than the id which was deleted",
                    lastIdAfterDelete.intValue() < lastIdBeforeDelete.intValue());
        }
    }
}
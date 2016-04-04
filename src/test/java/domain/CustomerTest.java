package domain;

import hibernate.HibernateUtil;
import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        customer.setAddress(new Address("Buharest", "Romania"));
        customer.setPromoCardAmount(279.21);

        session.beginTransaction();
        long numbOfLinesBeforeAdd = ((Long) session.createCriteria("domain.Customer")
                .setProjection(Projections.rowCount()).uniqueResult()).intValue();
        session.save(customer);
        session.save(customer);
        long numbOfLinesAfterAdd = ((Long) session.createCriteria("domain.Customer")
                .setProjection(Projections.rowCount()).uniqueResult()).intValue();
        session.getTransaction().commit();
        System.out.println(numbOfLinesBeforeAdd + " " + numbOfLinesAfterAdd);
        assertEquals(numbOfLinesBeforeAdd + 1, numbOfLinesAfterAdd);
    }

    public void tearDown() throws Exception {

    }

    public void testGetAddress() throws Exception {

    }

    public void testSetAddress() throws Exception {

    }

    public void testGetPromoCard() throws Exception {

    }

    public void testSetPromoCard() throws Exception {

    }
}
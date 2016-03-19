package hibernate;

import domain.Address;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.junit.AfterClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

/**
 * Created by olexandra on 3/18/16.
 */
public class AddressTest {
    private static Logger log = LoggerFactory.getLogger(AddressTest.class);

    @Test
    public void addOneAddress() {
        Address address = new Address("Kiev");
        address.setCountry("Ukraine");
        //address.setInsertTime(new Date());

        //Get Session
        Session session = HibernateUtil.getSessionJavaConfigFactory().getCurrentSession();

        //start transaction
        session.beginTransaction();

        //last values before addition of row
        Number numbOfLinesBeforeAdd = (Number) session.createCriteria("domain.Address").setProjection(Projections.rowCount()).uniqueResult();
        int idBeforeSave = (int) session.createCriteria("domain.Address").setProjection(Projections.max("id")).uniqueResult();

        //Save the Model object
        session.save(address);

        //last values after addition of row
        int idAfterSave = address.getId();
        Number numbOfLinesAfterAdd = (Number) session.createCriteria("domain.Address").setProjection(Projections.rowCount()).uniqueResult();
        Address addedAddress = (Address) session.get(Address.class, idAfterSave);

        //Commit transaction
        session.getTransaction().commit();

        //Assertions
        int amountOfAddedRows = numbOfLinesAfterAdd.intValue() - numbOfLinesBeforeAdd.intValue();
        log.info("Difference between amount of rows before addition {} and after addition {} is: {}", numbOfLinesBeforeAdd, numbOfLinesAfterAdd, amountOfAddedRows);
        log.info("ID before save is {}, ID after save is {}", idBeforeSave, idAfterSave);
        log.info("The address read from the Address: {} with id {}", addedAddress.toString(), addedAddress.getId());

        //terminate session factory, otherwise program won't end
        HibernateUtil.getSessionAnnotationFactory().close();
        assertEquals("There were more or less than one added rows", 1, amountOfAddedRows);
        assertEquals("ID is not greater than one than the last previous id was", ++idBeforeSave, idAfterSave);
        assertEquals("The added address is wrong", address.toString(), addedAddress.toString());

    }

    @Test
    public void addSetOfAddresses() {
        Address address = new Address("Kiev");
        address.setCountry("Ukraine");
//        address.setCity("Kiev");
//        address.setInsertTime(new Date());

        //Get Session
        Session session = HibernateUtil.getSessionJavaConfigFactory().getCurrentSession();
        int numberOfRecordsBeforeAddition = 0, numberOfRecordsAfterAddition = 0;
        int amountOfAddedLines = 2;
        try {
            //start transaction
            session.beginTransaction();
            numberOfRecordsBeforeAddition = ((Long) session.createQuery("SELECT COUNT(*) FROM domain.Address").uniqueResult()).intValue();
            for (int i = 0; i < amountOfAddedLines; i++) {
                Address addr = new Address("Riga " + i, "Latvia");
                session.saveOrUpdate(addr);
                log.info("Want to write Address: {}", addr);
            }
            numberOfRecordsAfterAddition = ((Long) session.createQuery("SELECT COUNT(*) FROM domain.Address").uniqueResult()).intValue();
            session.getTransaction().commit();

        } catch (HibernateException e) {
            log.error(e.getMessage());
            session.getTransaction().rollback();
        }

        //terminate session factory, otherwise program won't end
        HibernateUtil.getSessionAnnotationFactory().close();

        assertEquals(String.format("There were not %d lines added", amountOfAddedLines),
                numberOfRecordsBeforeAddition + amountOfAddedLines, numberOfRecordsAfterAddition);
    }

    @AfterClass
    public static void tearDown() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().getCurrentSession();
        session.beginTransaction();
        String deleteQueryString = String.format("DELETE FROM %s", "domain.Address");
        Query deleteQuery = session.createQuery(deleteQueryString);
        deleteQuery.executeUpdate();
        session.getTransaction().commit();
        log.info("All the data from {} table was deleted (truncated)", "Address");
    }
}

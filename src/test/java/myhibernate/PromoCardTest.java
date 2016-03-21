package myhibernate;

import domain.PromoCard;
import hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * Created by olexandra on 3/18/16.
 */
public class PromoCardTest {
    private static Logger log = LoggerFactory.getLogger(PromoCardTest.class);

    @Test
    public void addOnePromoCard() {
        PromoCard promoCard = new PromoCard(200.02);
        promoCard.setLocalDateTime(LocalDateTime.now());
        //promoCard.setInsertTime(new Date());

        //Get Session
        Session session = HibernateUtil.getSessionAnnotationFactory().getCurrentSession();

        //start transaction
        session.beginTransaction();

        //last values before addition of row
        Number numbOfLinesBeforeAdd = (Number) session.createCriteria("domain.PromoCard").setProjection(Projections.rowCount()).uniqueResult();
//        int idBeforeSave = (int) session.createCriteria("domain.PromoCard").setProjection(Projections.max("id")).uniqueResult();

        //Save the Model object
        session.save(promoCard);

        //last values after addition of row
        long idAfterSave = promoCard.getId();
        Number numbOfLinesAfterAdd = (Number) session.createCriteria("domain.PromoCard").setProjection(Projections.rowCount()).uniqueResult();
//        Address addedAddress = (Address) session.get(Address.class, idAfterSave);

        //Commit transaction
        session.getTransaction().commit();

        //Assertions
        int amountOfAddedRows = numbOfLinesAfterAdd.intValue() - numbOfLinesBeforeAdd.intValue();
        log.info("Difference between amount of rows before addition {} and after addition {} is: {}", numbOfLinesBeforeAdd, numbOfLinesAfterAdd, amountOfAddedRows);
//        log.info("ID before save is {}, ID after save is {}", idBeforeSave, idAfterSave);
//        log.info("The promoCard read from the Address: {} with id {}", addedAddress.toString(), addedAddress.getId());

        //terminate session factory, otherwise program won't end
        HibernateUtil.getSessionAnnotationFactory().close();
        assertEquals("There were more or less than one added rows", 1, amountOfAddedRows);
//        assertEquals("ID is not greater than one than the last previous id was", ++idBeforeSave, idAfterSave);
//        assertEquals("The added promoCard is wrong", promoCard.toString(), addedAddress.toString());

    }

    @Test
    public void addSetOfAddresses() {
        PromoCard promoCard = new PromoCard(200.02);
        promoCard.setBlockedAmount(100.43);
        LocalDateTime myDate = LocalDateTime.of(2015, 04, 30, 15, 43, 12);
        promoCard.setLocalDateTime(myDate);
        //promoCard.setInsertTime(new Date());

        //Get Session
        Session session = HibernateUtil.getSessionAnnotationFactory().getCurrentSession();
        int numberOfRecordsBeforeAddition = 0, numberOfRecordsAfterAddition = 0;
        int amountOfAddedLines = 1;

        try {
            //start transaction
            session.beginTransaction();
            numberOfRecordsBeforeAddition = ((Long) session.createQuery("SELECT COUNT(*) FROM domain.PromoCard").uniqueResult()).intValue();
            session.saveOrUpdate(promoCard);
            log.info("Want to write PromoCard {}", promoCard);
            log.info("Want to write PromoCard {}", promoCard);

            numberOfRecordsAfterAddition = ((Long) session.createQuery("SELECT COUNT(*) FROM domain.PromoCard").uniqueResult()).intValue();
//            int numberOfIdOfDate = ((Long) session.createQuery("SELECT count(*) FROM domain.PromoCard WHERE CREATE_DATE='2015-04-30 15:43:12'").uniqueResult()).intValue();
//            int numberOfIdOfDate = ((Long) session.createQuery("SELECT count(*) FROM domain.PromoCard WHERE CREATE_DATE= :my_date")
//                    .setParameter("my_date", "2015-04-30 15:43:12").uniqueResult()).intValue();
            int numberOfIdOfDate = ((Long) session.createQuery("SELECT count(*) FROM domain.PromoCard WHERE CREATE_DATE= :my_date")
                    .setParameter("my_date", myDate.toString()).uniqueResult()).intValue();
            log.info("Count of records with date '{}' is {}", myDate, numberOfIdOfDate);
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

}

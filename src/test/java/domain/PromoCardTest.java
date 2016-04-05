package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hibernate.HibernateUtil;
import jdk.nashorn.internal.ir.annotations.Ignore;

import static org.junit.Assert.assertEquals;

/**
 * Created by olexandra on 3/18/16.
 */
public class PromoCardTest {
    private static Logger log = LoggerFactory.getLogger(PromoCardTest.class);
    private static Session session = null;

    @AfterClass
    public static void tearDown() {
        // terminate session factory, otherwise program won't end
        // HibernateUtil.getSessionAnnotationFactory().close();

    }

    @Before
    public void setUp() {
        // Get Session
        session = HibernateUtil.getSessionAnnotationFactory().getCurrentSession();
    }

    @Test
    @Ignore // worked before connecting with Customer
    public void addOnePromoCard() {
        PromoCard promoCard = new PromoCard(200.02);
        promoCard.setLocalDateTime(LocalDateTime.now());
        // promoCard.setInsertTime(new Date());

        session = HibernateUtil.getSessionAnnotationFactory().getCurrentSession();
        // start transaction
        session.beginTransaction();

        // last values before addition of row
        Number numbOfLinesBeforeAdd = (Number) session.createCriteria("domain.PromoCard")
                .setProjection(Projections.rowCount()).uniqueResult();
        int idBeforeSave = ((Long) session.createCriteria("domain.PromoCard").setProjection(Projections.max("id"))
                .uniqueResult()).intValue();

        // Save the Model object
        session.save(promoCard);

        // last values after addition of row
        long idAfterSave = promoCard.getId();
        Number numbOfLinesAfterAdd = (Number) session.createCriteria("domain.PromoCard")
                .setProjection(Projections.rowCount()).uniqueResult();
        PromoCard addedPromoCard = (PromoCard) session.get(PromoCard.class, idAfterSave);

        // Commit transaction
        session.getTransaction().commit();

        // Assertions
        int amountOfAddedRows = numbOfLinesAfterAdd.intValue() - numbOfLinesBeforeAdd.intValue();
        log.info("Difference between amount of rows before addition {} and after addition {} is: {}",
                numbOfLinesBeforeAdd, numbOfLinesAfterAdd, amountOfAddedRows);
        log.info("ID before save is {}, ID after save is {}", idBeforeSave, idAfterSave);
        log.info("The PromoCard is read after Save: {} with id {}", addedPromoCard.toString(), addedPromoCard.getId());

        assertEquals("There were more or less than one added rows", 1, amountOfAddedRows);
        assertEquals("ID is not greater than one than the last previous id was", ++idBeforeSave, idAfterSave);
        assertEquals("The added promoCard is wrong", promoCard.toString(), addedPromoCard.toString());

    }

    @Test
    @Ignore // worked before connecting with Customer
    public void addAndSearchPromoCardByDate() {
        // Given
        PromoCard promoCard = new PromoCard(200.02);
        promoCard.setBlockedAmount(100.43);
        LocalDateTime myDate = LocalDateTime.of(2015, 4, 30, 15, 43, 12);
        promoCard.setLocalDateTime(myDate);
        // promoCard.setInsertTime(new Date());

        // Get Session
        // Session session = HibernateUtil.getSessionAnnotationFactory().getCurrentSession();
        int amountOfAddedLines = 1;
        List<Long> idOfDateBeforeAdd = new ArrayList<>(), idOfDateAfterAdd = new ArrayList<>();

        try {
            // start transaction
            session.beginTransaction();
            log.info("Want to write PromoCard {}", promoCard);
            idOfDateBeforeAdd = getRecordsWithDate(myDate, session);

            // When
            session.saveOrUpdate(promoCard);

            // Then
            idOfDateAfterAdd = getRecordsWithDate(myDate, session);
            log.info(
                    "Record ids with date '{}' \n" + "before update: amount = {}, ids = {} \n"
                            + "after  update: amount = {}, ids = {}",
                    myDate, idOfDateBeforeAdd.size(), idOfDateBeforeAdd, idOfDateAfterAdd.size(), idOfDateAfterAdd);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            log.error(e.getMessage());
            session.getTransaction().rollback();
        }

        // terminate session factory, otherwise program won't end
        // HibernateUtil.getSessionAnnotationFactory().close();

        assertEquals(String.format("There were not %d lines added", amountOfAddedLines),
                idOfDateBeforeAdd.size() + amountOfAddedLines, idOfDateAfterAdd.size());
    }

    private List getRecordsWithDate(LocalDateTime myDate, Session session) {
        return session.createQuery("SELECT id FROM domain.PromoCard WHERE CREATE_DATE= :my_date")
                .setParameter("my_date", myDate.toString()).list();
    }
}

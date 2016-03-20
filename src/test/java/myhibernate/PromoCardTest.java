package myhibernate;

import domain.PromoCard;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
}

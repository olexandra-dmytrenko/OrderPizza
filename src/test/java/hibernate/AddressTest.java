package hibernate;

import domain.Address;
import org.hibernate.Session;
import org.junit.Test;

/**
 * Created by olexandra on 3/18/16.
 */
public class AddressTest {

    @Test
    public void writeAddress() {
        Address address = new Address("Kiev");
        address.setCountry("Ukraine");
//        address.setCity("Kiev");
//        address.setInsertTime(new Date());

        //Get Session
        Session session = HibernateUtils.getSessionJavaConfigFactory().getCurrentSession();
        //start transaction
        session.beginTransaction();
        //Save the Model object
        session.save(address);
        //Commit transaction
        session.getTransaction().commit();
        System.out.println("Address ID=" + address.getId());


//        Address a = (Address) session.get(Address.class, 2);
        //terminate session factory, otherwise program won't end
        HibernateUtils.getSessionAnnotationFactory().close();

    }
}

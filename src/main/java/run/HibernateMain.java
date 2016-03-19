package run;

import domain.Address;
import hibernate.HibernateUtils;
import org.hibernate.Session;

/**
 * Created by olexandra on 3/18/16.
 */
public class HibernateMain {
    public static void main(String[] args) {
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
        System.out.println("Address ID="+address.getId());

        //terminate session factory, otherwise program won't end
        HibernateUtils.getSessionAnnotationFactory().close();
    }

}

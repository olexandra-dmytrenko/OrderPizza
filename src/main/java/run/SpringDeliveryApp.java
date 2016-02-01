package run;

import domain.Customer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by olexandra on 1/27/16a
 */
public class SpringDeliveryApp {
    public static void main(String[] args) {

        ConfigurableApplicationContext appContextParent
//            = new ClassPathXmlApplicationContext("src/main/repository/appContext.xml");
                = new ClassPathXmlApplicationContext(new String[]{"repoContext.xml"});
        //указали что у апп контекста есть родитель репозитори контекст
        ConfigurableApplicationContext appContext
                = new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, appContextParent);
        Customer customer = appContext.getBean("customer", Customer.class);

        org.springframework.context.ApplicationContext parent = appContext.getParent();
        System.out.println(parent);
//
//    PizzaRepository pizzaRepository = (PizzaRepository) appContext.getBean("pizzaRepo");
//    System.out.println(pizzaRepository.find(1));

/* `Не сработают дистрой методы если не закрыть контекст */
        appContext.close();
        appContextParent.close();

    }
}


package run;

import domain.Pizza;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import domain.Customer;
import service.SimpleOrderService;

/**
 * Created by olexandra on 1/27/16a
 */
public class SpringDeliveryApp {
    public static void main(String[] args) {

        ConfigurableApplicationContext appContextParent
        // = new ClassPathXmlApplicationContext("src/main/repository/appContext.xml");
        = new ClassPathXmlApplicationContext(new String[] { "repoContext.xml" });
        // указали что у апп контекста есть родитель репозитори контекст
        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(
                new String[] { "appContext.xml" }, appContextParent);

        ///////// parent/////////
        ApplicationContext parent = appContext.getParent();
        System.out.println("Parent = " + parent);

        ///////// customers/////////
        Customer customer = appContext.getBean("customer", Customer.class);
        Customer newCustomer = appContext.getBean("newCustomer", Customer.class);
        System.out.println(customer.toString());
        System.out.println(newCustomer.toString());

        SimpleOrderService os = appContext.getBean("orderService", SimpleOrderService.class);
        // os.placeNewOrder(customer, os.getOrder());
//        System.out.println(os.getOrder().toString());
        System.out.println(os.toString());
        //
        // PizzaRepository pizzaRepository = (PizzaRepository) appContext.getBean("pizzaRepo");
        // System.out.println(pizzaRepository.find(1));

        /* `Не сработают дистрой методы если не закрыть контекст */

        // appContext.getBeanDefinionNames().stream().forEach(e->System.out.println(e));

//        Pizza pizza = appContext.getBean("pizzaFactoryBean", Pizza.class);
//        System.out.println(pizza);
        // надо реализовать подписчиков и тогда сможем получать сообщения
   //     appContext.addApplicationListener();
        appContext.publishEvent(new ApplicationEvent(appContext) {
        });
        appContext.close();
        appContextParent.close();

    }
}

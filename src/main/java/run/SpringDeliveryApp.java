package run;

import domain.Customer;
import domain.Pizza;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.InMemPizzaRepository;
import repository.PizzaRepository;
import service.SimpleOrderService;

/**
 * Created by olexandra on 1/27/16a
 */
public class SpringDeliveryApp {

    public static final String SEPARATOR = "------------------------------------------------";

    public static void main(String[] args) {

        ConfigurableApplicationContext appContextParent
                // = new ClassPathXmlApplicationContext("src/main/repository/appContext.xml");
                = new ClassPathXmlApplicationContext(new String[]{"repoContext.xml"});
        // указали что у апп контекста есть родитель репозитори контекст
        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(
                new String[]{"appContext.xml"}, appContextParent);

        /****************** Parent ******************/
        ApplicationContext parent = appContext.getParent();
        System.out.println("Parent = " + parent);

        /****************** Pizzas (Repo) ******************/
        System.out.println(SEPARATOR);
        System.out.println("The list of available pizzas");
        PizzaRepository pizzaRepository = (PizzaRepository) appContext.getBean("pizzaRepo");
        InMemPizzaRepository pizzaRepositoryALL = (InMemPizzaRepository) appContext.getBean("pizzaRepo");
        System.out.println("All pizzas:");
        pizzaRepositoryALL.getPizzas().entrySet().stream()
                .forEach(e -> System.out.println(e.getKey() + " - " + e.getValue()));
        System.out.println("Using find method" + pizzaRepository.find(1));
        System.out.println(SEPARATOR);

        ///////// customers/////////
        Customer customer = appContext.getBean("customer", Customer.class);
        Customer newCustomer = appContext.getBean("newCustomer", Customer.class);
        System.out.println(customer.toString());
        System.out.println(newCustomer.toString());

        SimpleOrderService os = appContext.getBean("orderService", SimpleOrderService.class);
        // os.placeNewOrder(customer, os.getOrder());
//        System.out.println(os.getOrder().toString());
        System.out.println(os.toString());

        /* `Не сработают дистрой методы если не закрыть контекст */

        // appContext.getBeanDefinionNames().stream().forEach(e->System.out.println(e));
        System.out.println(SEPARATOR);
        System.out.println("Initializing using pizzaFactoryBean");
        Pizza pizza = appContext.getBean("pizzaFactoryBean", Pizza.class);
        System.out.println(pizza);
        System.out.println(SEPARATOR);
        // надо реализовать подписчиков и тогда сможем получать сообщения
        //     appContext.addApplicationListener();
        appContext.publishEvent(new ApplicationEvent(appContext) {
        });
        appContext.close();
        appContextParent.close();

    }
}

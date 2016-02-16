package run;

import domain.Customer;
import domain.Pizza;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.InMemPizzaRepository;
import repository.PizzaRepository;
import service.SimpleOrderService;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by olexandra on 1/27/16a
 * Might be useful:
 * http://www.programcreek.com/java-api-examples/index.php?class=org.springframework.context.ConfigurableApplicationContext&method=addApplicationListener
 * http://learningviacode.blogspot.com/2012/07/spring-and-events-and-listeners.html
 * http://www.mkyong.com/java/java-custom-annotations-example/
 * http://www.tutorialspoint.com/spring/spring_annotation_based_configuration.htm
 */
public class SpringDeliveryApp {

    public static final String SEPARATOR = "------------------------------------------------";

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        ConfigurableApplicationContext appContextParent
                // = new ClassPathXmlApplicationContext("src/main/repository/appContext.xml");
                = new ClassPathXmlApplicationContext(new String[]{"repoContext.xml"});
        // указали что у апп контекста есть родитель репозитори контекст
        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(
                new String[]{"appContext.xml"}, appContextParent);

        /****************** Parent ******************/
        System.out.println(SEPARATOR);
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

        /****************** Customers ******************/
        System.out.println(SEPARATOR);
        System.out.println("The Customers");
        Customer customer = appContext.getBean("customer", Customer.class);
        Customer newCustomerContext = appContext.getBean("newCustomer", Customer.class);
        Customer newCustomerParent = appContext.getParent().getBean("newCustomer", Customer.class);
        System.out.println(customer.toString());
        System.out.println(newCustomerContext.toString());
        System.out.println(newCustomerParent.toString());

        /****************** SimpleOrderService ******************/
        System.out.println(SEPARATOR);
        System.out.println("SimpleOrderService");
        SimpleOrderService os = appContext.getBean("orderService", SimpleOrderService.class);
     //   int [] pizzaIdsList = (int[]) appContext.getBean("pizzaIdsList");
      //  LinkedList pizzaIds1 = (LinkedList) appContext.getBean("pizzaIdsList");
     //   Order order1 = os.placeNewOrder(customer, pizzaIds1);
       // System.out.println(order1);
        os.createNewOrder();
        System.out.println(os.getOrder().toString());
        os.createNewOrder();
        System.out.println(os.getOrder().toString());
        System.out.println(os.toString());

        System.out.println("Total Price = $" + os.countTotalPrice());
        System.out.println("Total Price with discount = $" + os.countTotalPriceWithPossibleDiscount());


        // appContext.getBeanDefinionNames().stream().forEach(e->System.out.println(e));
        System.out.println(SEPARATOR);
        System.out.println("Initializing using pizzaFactoryBean");
        Pizza pizza = appContext.getBean("pizzaFactoryBean", Pizza.class);
        System.out.println(pizza);
        System.out.println(SEPARATOR);
        // надо реализовать подписчиков и тогда сможем получать сообщения
             appContext.addApplicationListener(event -> System.out.println("event"));
//        appContext.publishEvent(new ApplicationEvent(appContext) {
//        });
        /* `Не сработают дистрой методы если не закрыть контекст */
        appContext.close();
        appContextParent.close();

    }
}

package run;

import domain.Customer;
import domain.Pizza;
import domain.Status;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.InMemPizzaRepository;
import service.SimpleOrderService;

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

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext appContextParent
                = new ClassPathXmlApplicationContext(new String[]{"repoContext.xml"});
        // указали что у апп контекста есть родитель репозитори контекст
        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(
                new String[]{"appContext.xml"}, appContextParent);

        /****************** Pizzas (Repo) ******************/
        System.out.println(SEPARATOR);
        System.out.println("The list of available pizzas");
        InMemPizzaRepository pizzaRepositoryALL = (InMemPizzaRepository) appContext.getBean("pizzaRepo");
        System.out.println("All pizzas:");
        pizzaRepositoryALL.getPizzas().entrySet().stream()
                .forEach(e -> System.out.println(e.getKey() + " - " + e.getValue()));
        System.out.println("Using find method" + pizzaRepositoryALL.find(1));
        System.out.println(SEPARATOR);

        /****************** Customers ******************/
        System.out.println(SEPARATOR);
        System.out.println("The Customers");
        Customer customer = appContext.getBean("customer", Customer.class);
        Customer newCustomerContext = appContext.getBean("newCustomer", Customer.class);
        System.out.println(customer.toString());
        System.out.println(newCustomerContext.toString());

        /****************** SimpleOrderService ******************/
        System.out.println(SEPARATOR);
        System.out.println("SimpleOrderService With Discount");
        SimpleOrderService os = appContext.getBean("orderService", SimpleOrderService.class);
        System.out.println("LOOKUP " + os.createNewOrder());;
        System.out.println(os.getOrder().toString());
        System.out.println(os.toString());
        System.out.println("Total Price = $" + os.countTotalPrice());
        System.out.println("Total Price with discount = $" + os.countTotalPriceWithPossibleDiscounts());
        System.out.println("Move order to Progress = " + os.getOrder().switchStatusTo(Status.IN_PROGRESS));
        System.out.println(os.getOrder().toString());
        System.out.println("Move order to Done = " + os.getOrder().switchStatusTo(Status.DONE));
        System.out.println(os.getOrder().toString());


        System.out.println(SEPARATOR);
        System.out.println("SimpleOrderService No Discount");
        SimpleOrderService osNoDiscount = appContext.getBean("orderServiceNoDiscount", SimpleOrderService.class);
        System.out.println("LOOKUP " + osNoDiscount.createNewOrder());;
        System.out.println("Total Price = $" + osNoDiscount.countTotalPrice());
        System.out.println("Total Price with discount = $" + osNoDiscount.countTotalPriceWithPossibleDiscounts());
        System.out.println("Order with New Status " + osNoDiscount.getOrder().toString());
        System.out.println("Move order to Progress = " + osNoDiscount.getOrder().switchStatusTo(Status.IN_PROGRESS));
        System.out.println("Order with updated In Progress Status " + osNoDiscount.getOrder().toString());


        // appContext.getBeanDefinionNames().stream().forEach(e->System.out.println(e));
        System.out.println(SEPARATOR);
        System.out.println("Initializing using pizzaFactoryBean");
        Pizza pizza = appContext.getBean("pizzaFactoryBean", Pizza.class);
        System.out.println(pizza);
        System.out.println(SEPARATOR);
        // надо реализовать подписчиков и тогда сможем получать сообщения
             appContext.addApplicationListener(event -> System.out.println("event"));
        /* `Не сработают дистрой методы если не закрыть контекст */
        appContext.close();
        appContextParent.close();

    }
}

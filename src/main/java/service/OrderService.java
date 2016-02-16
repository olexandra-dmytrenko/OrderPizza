package service;

import domain.Customer;
import domain.Order;
import domain.PizzaAmount;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public interface OrderService {

    // оставить этот метод, fine grained but not core grained. Customer may already exist. Should be fine for CRUD opsn
    Order placeNewOrder(Customer customer, List<PizzaAmount> pizzaAmountList) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;

    double countTotalPrice();
    double countTotalPriceWithPossibleDiscount();
}

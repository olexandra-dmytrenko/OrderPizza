package repository;

import domain.Order;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class InMemOrderRepository implements OrderRepository {
    public Order save(Order newOrder) {
        Order.getOrders().add(newOrder);
        return newOrder;
    }
}

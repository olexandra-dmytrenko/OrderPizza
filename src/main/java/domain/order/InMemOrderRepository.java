package domain.order;

import repository.OrderRepository;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class InMemOrderRepository implements OrderRepository {
    public Order saveOrder(Order newOrder) {
        Order.getOrders().add(newOrder);
        return newOrder;

    }
}

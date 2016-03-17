package repository;

import domain.Order;
import org.springframework.stereotype.Repository;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
@Repository
public class InMemOrderRepository implements OrderRepository {

    @Override
    public Order saveOrder(Order newOrder) {
        Order.getOrders().add(newOrder);
        return newOrder;
    }
}

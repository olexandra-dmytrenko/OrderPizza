package repository;

import domain.Order;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public interface OrderRepository {
    Order saveOrder(Order newOrder);
}

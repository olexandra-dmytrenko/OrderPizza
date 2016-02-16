package domain;

/**
 * Created by olexandra on 2/16/16.
 */
public interface OrderActions {
    boolean switchStatusTo(Status newStatus);

    double countTotalPrice();

    double countTotalPriceWithPossibleDiscount();

}

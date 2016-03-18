package domain;

import java.util.List;

/**
 * Created by olexandra on 2/15/16.
 */
public interface Utils {
    int MAX_PIZZAS_AMOUNT = 10;

    default void ifPizzaAmountIsGreaterThanMax(int pizzaAmount) {
        if (pizzaAmount > MAX_PIZZAS_AMOUNT) {
            throw new IndexOutOfBoundsException("Amount of pizzas should not exceed " + MAX_PIZZAS_AMOUNT);
        }
    }
    static int countPizzaAmount(List<PizzaAmount> pizzaAmountList) {
        return pizzaAmountList.stream().mapToInt(PizzaAmount::getAmount).sum();
    }
}

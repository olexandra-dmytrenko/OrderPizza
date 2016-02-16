package domain;

/**
 * Created by olexandra on 2/15/16.
 */
public class PizzaAmount implements Utils {
    int pizzaId;
    int amount;

    public PizzaAmount(int pizzaId) {
        this.pizzaId = pizzaId;
        this.amount = 1;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        ifPizzaAmountIsGreaterThanMax(amount);
        this.amount = amount;
    }
}

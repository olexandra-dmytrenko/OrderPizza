package domain;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by olexandra on 2/28/16.
 */
public class PromoCard {
    public static final double TEN_PERCENT_MULTIPLIER = 0.1;
    public static final double THIRTY_PERCENT_MULTIPLIER = 0.3;
    private long id;
    private static AtomicLong idCounter = new AtomicLong(0);
    private double amount = 0;
    private double blockedAmount = 0;

    public PromoCard() {
        this.id = idCounter.incrementAndGet();
    }

    public PromoCard(double amount) {
        this.id = idCounter.incrementAndGet();
        addAmount(amount);
    }

    public void addAmount(double newBuyAmount) {
        this.amount += newBuyAmount;
    }

    public double getDiscount(double newBuyAmount) {
        double tenPercentAmountDiscount = this.amount * TEN_PERCENT_MULTIPLIER;
        double thirtyPercentAmount = newBuyAmount * THIRTY_PERCENT_MULTIPLIER;
        if (tenPercentAmountDiscount <= thirtyPercentAmount) {
            return tenPercentAmountDiscount;
        } else {
            return thirtyPercentAmount;
        }
    }

    @Override
    public String toString() {
        return "PromoCard{" +
                "id=" + id +
                ", amount=" + amount +
                ", blockedAmount=" + blockedAmount +
                '}';
    }

    public double updateAmountFromBlocked() {
        this.addAmount(blockedAmount);
        blockedAmount = 0;
        return this.amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBlockedAmount(double blockedAmount) {
        this.blockedAmount = blockedAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PromoCard)) return false;

        PromoCard promoCard = (PromoCard) o;

        if (id != promoCard.id) return false;
        if (Double.compare(promoCard.amount, amount) != 0) return false;
        return Double.compare(promoCard.blockedAmount, blockedAmount) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(blockedAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

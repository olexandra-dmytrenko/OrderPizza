package domain;

import hibernate.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.time.LocalDateTime;

/**
 * Created by olexandra on 2/28/16.
 */
@Entity
@Table(name = "PROMO_CARD", uniqueConstraints = @UniqueConstraint(columnNames = {"ID"}))
public class PromoCard {
    public static final double TEN_PERCENT_MULTIPLIER = 0.1;
    public static final double THIRTY_PERCENT_MULTIPLIER = 0.3;

//    private static AtomicLong idCounter = new AtomicLong(0);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, length = 10)
    private long id;

    @Column(name = "AMOUNT", nullable = false, length = 20)
    private double amount = 0;

    @Column(name = "BLOCKED_AMOUNT", nullable = false, length = 20)
    private double blockedAmount = 0;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "CREATE_DATE", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime localDateTime;

    public PromoCard() {
      //  this.id = idCounter.incrementAndGet();
    }

    public PromoCard(double amount) {
    //    this.id = idCounter.incrementAndGet();
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
                ", localDateTime=" + localDateTime +
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}

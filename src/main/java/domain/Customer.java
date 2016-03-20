package domain;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
//@BenchMark(countTime = false, createdBy = "Also me")

public class Customer {
    String name;
    Address address;
    long number;
    static AtomicLong id = new AtomicLong(0);
    PromoCard promoCard = null;

    public Customer(String name) {
        this.name = name;
        this.number = id.incrementAndGet();
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PromoCard getPromoCard() {
        if (this.promoCard == null) {
            System.out.println(this.toString() + " doesn't have a promo card. A new one was created");
            this.promoCard = new PromoCard();
        }
        return promoCard;
    }

    public void setPromoCard(PromoCard promoCard) {
        this.promoCard = promoCard;
    }

    public void setPromoCardAmount(double amount) {
        if (this.promoCard == null) {
            this.promoCard = new PromoCard(amount);
        }
    }

    @Override
    public String toString() {
        return "Customer: " + name + " from " + address.toString() + ", customer number = " + number + ", promo card = " + promoCard;
    }
}

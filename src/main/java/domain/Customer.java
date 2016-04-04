package domain;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
//@BenchMark(countTime = false, createdBy = "Also me")
@Entity
@Table(name = "CUSTOMER",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})

public class Customer {

    String name;
    Address address;
    long id;
    PromoCard promoCard = null;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    public PromoCard getPromoCard() {
        if (this.promoCard == null) {
            System.out.println(this.toString() + " doesn't have a promo card. A new one was created");
            this.promoCard = new PromoCard();
        }
        return promoCard;
    }

    public void setPromoCardAmount(double amount) {
        if (this.promoCard == null) {
            this.promoCard = new PromoCard(amount);
        }
    }

    public void setPromoCard(PromoCard promoCard) {
        this.promoCard = promoCard;
    }

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false, unique = true, length = 11)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "NAME", length = 100, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//    @Override
//    public String toString() {
//        return "Customer: " + name + " from " + address.toString() + ", customer id = " + id + ", promo card = " + promoCard;
//    }
}

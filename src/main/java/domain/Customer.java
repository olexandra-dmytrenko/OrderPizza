package domain;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
// @BenchMark(countTime = false, createdBy = "Also me")
@Entity
@Table(name = "CUSTOMER", uniqueConstraints = { @UniqueConstraint(columnNames = { "id", "name" }) })

public class Customer implements Serializable {

    private String name;
    private int id;
    private PromoCard promoCard;
    private List<Address> addresses = new ArrayList<>();
    private List<Order> orders;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
        promoCard = new PromoCard(this);
    }

    // @OneToOne(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    // public Address getAddress() {
    // return address;
    // }

    public void addAddress(Address address) {
        if (addresses == null)
            addresses = new ArrayList<>(1);
        addresses.add(address);
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    public PromoCard getPromoCard() {
//        if (this.promoCard == null) {
//            System.out.println(this.toString() + " doesn't have a promo card. A new one was created");
//            this.promoCard = new PromoCard(0);
//            this.getPromoCard().setCustomer(this);
//        }
        return promoCard;
    }

    public void setPromoCard(PromoCard promoCard) {
        this.promoCard = promoCard;
    }

    public void setPromoCardAmount(double amount) {
        if (this.promoCard == null) {
            this.promoCard = new PromoCard(amount);
        } else
            this.promoCard.addAmount(amount);
        this.getPromoCard().setCustomer(this);
    }

     @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    // @JoinColumn(name = "ADDRESS_ID", nullable = false)
     public List<Address> getAddresses() {
     return addresses;
     }

     public void setAddresses(List<Address> addresses) {
     this.addresses = addresses;
     }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NaturalId
    @Column(name = "NAME", length = 100, nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer: " + name
        // + " from " + addresses == null ? addresses.get(0).toString() : "Nowhere"
                + ", customer id = " + id + ", promo card = " + promoCard;
    }
}

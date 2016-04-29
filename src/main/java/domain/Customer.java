package domain;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
// @BenchMark(countTime = false, createdBy = "Also me")
@Entity
@Table(name = "CUSTOMER", uniqueConstraints = { @UniqueConstraint(columnNames = { "id", "name" }) })

public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Integer id;

    @NaturalId
    @Column(name = "NAME", length = 100, nullable = false, unique = true)
    private String name;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "customer", cascade = {CascadeType.ALL})
//    @PrimaryKeyJoinColumn
    private PromoCard promoCard;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Address> addresses = new HashSet<>(1);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
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
            addresses = new HashSet<>(1);
        addresses.add(address);
    }

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

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    // @JoinColumn(name = "ADDRESS_ID", nullable = false)
    public Set<Address> getAddresses() {
        return addresses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer: " + name
         + " from " + addresses != null ? addresses.stream().toString() : "Nowhere"
                + ", customer id = " + id + ", with promo card = " + promoCard;
    }
}

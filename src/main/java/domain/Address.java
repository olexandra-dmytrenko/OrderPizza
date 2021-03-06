package domain;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;

/**
 * Created by olexandra on 1/29/16.
 */
@Entity
@Table(name = "ADDRESS", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false, unique = true, length = 11)
    private Integer id;

    @Column(name = "CITY", length = 100, nullable = false)
    private String city;

    @Column(name = "COUNTRY", length = 100, nullable = false)
    private String country;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Customer customer;


    public Address(String city) {
        this.city = city;
    }

    public Address(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public Address() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

//    @Required
    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private void destroy() {
        System.out.println("destroy address");
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return city + ", " + country;
    }
}

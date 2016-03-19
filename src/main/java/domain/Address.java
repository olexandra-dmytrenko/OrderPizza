package domain;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;

/**
 * Created by olexandra on 1/29/16.
 */
@Entity
@Table(name = "ADDRESS",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class Address {

    @Id
    @Column(name = "ID", nullable = false, unique = true, length = 11)
    private int id;

    @Column(name = "CITY", length = 100, nullable = false)
    private String city;

    @Column(name = "COUNTRY", length = 100, nullable = false)
    private String country;

    public Address(String city) {
        this.city = city;
    }

    public Address(String city, String country) {
        this.city = city;
        this.country = country;
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

    @Required
    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private void destroy() {
        System.out.println("destroy address");
    }

    @Override
    public String toString() {
        return city + ", " + country;
    }
}

package domain;

import org.springframework.beans.factory.annotation.Required;

/**
 * Created by olexandra on 1/29/16.
 */
public class Address {
    private String city;
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

    private void destroy() {
        System.out.println("destroy address");
    }

    @Override
    public String toString() {
        return city + ", " + country;
    }
}

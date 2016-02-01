package domain;

/**
 * Created by olexandra on 1/29/16.
 */
public class Address {
    String city;
    String country;

    public Address(String city) {
        this.city = city;
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

    public void setCountry(String country) {
        this.country = country;
    }

    private void destroy(){
        System.out.println("destroy address");
    }
}

package domain;

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

 //   @Required
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        return country != null ? country.equals(address.country) : address.country == null;

    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}

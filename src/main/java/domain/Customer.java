package domain;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
//@BenchMark(countTime = false, createdBy = "Also me")
public class Customer {
    String name;
    Address address;

    public Customer(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer: " + name + " from " + address.toString();
    }
}

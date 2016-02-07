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

    public Customer(String name) {
        this.name = name;
        this.number = id.incrementAndGet();
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer: " + name + " from " + address.toString() + ", customer number = " + number;
    }
}

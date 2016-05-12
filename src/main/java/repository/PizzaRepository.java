package repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import domain.BenchMark;
import domain.Pizza;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public interface PizzaRepository extends CrudRepository<Pizza, Integer> {
    @BenchMark
    default String initializeMessage() {
        return "All pizzas have been initialized through init method";
    }

    default HashMap<Integer, Pizza> getPizzas() {
        System.out.println("in Get Pizzas method");
        return new HashMap<>();
    }

    Pizza save(Pizza pizza);

    Pizza findById(int id);

    List<Pizza> findAll();
}

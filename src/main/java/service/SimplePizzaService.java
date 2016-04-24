package service;

import domain.Pizza;
import org.springframework.transaction.annotation.Transactional;
import repository.PizzaRepository;

import java.util.List;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public class SimplePizzaService implements PizzaService {

    PizzaRepository pizzaRepository;

    // public SimplePizzaService() {
    // JavaConfig config = new JavaConfig();
    // try {
    // pizzaRepository = (PizzaRepository) config.getImpl("pizzaRepository");
    // } catch (Exception e) {
    // System.out.print(e);
    // }
    // }

    public SimplePizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public Pizza find(Integer id) {
        return pizzaRepository.find(id);
    }

    @Override
    @Transactional
    public Pizza save(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @Override
    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }
}

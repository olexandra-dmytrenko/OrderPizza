package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Pizza;
import repository.PizzaRepository;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
@Service("PizzaService")
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

    @Autowired
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

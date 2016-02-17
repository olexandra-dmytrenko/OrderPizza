package service;

import domain.Pizza;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import repository.PizzaRepository;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
@Service
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

    @Transactional
    public Pizza find(int id) {
        return pizzaRepository.find(id);
    }

    @Transactional(isolation = Isolation.DEFAULT)
    public Pizza save(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }
}

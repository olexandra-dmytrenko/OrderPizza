package repository;

import domain.BenchMark;
import domain.Pizza;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 */
public interface PizzaRepository {
   @BenchMark
   default String initializeMessage(){
       return "All pizzas have been initialized through init method";
   }

   Pizza find(int id);

}

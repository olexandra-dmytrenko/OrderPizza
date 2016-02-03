package infrastructure;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by Oleksandra_Dmytrenko on 2/3/2016.
 */
//вызывается на этапе создания бинов после методов пост, инит, афтерПропертиз
//    на вход идет бин с засечеными конструктором и сеттерами
public class BenchMarkBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Before: " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("After: " + beanName);
        return bean;
    }
}

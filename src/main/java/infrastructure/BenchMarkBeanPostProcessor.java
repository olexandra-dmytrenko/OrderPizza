package infrastructure;

import domain.BenchMark;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by Oleksandra_Dmytrenko on 2/3/2016.
 */
//вызывается на этапе создания бинов после методов пост, инит, афтерПропертиз
//    на вход идет бин с засечеными конструктором и сеттерами
public class BenchMarkBeanPostProcessor implements BeanPostProcessor {

    private long startTime;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("Before in postProcessBeforeInitialization: " + beanName);
        for (Method method : bean.getClass().getDeclaredMethods()) {
            BenchMark benchMark = method.getAnnotation(BenchMark.class);
            if (benchMark.countTime()) {
                startTime = System.nanoTime();
                //      System.out.println("Benchmark Annotation is present: " + benchMark.toString() + " Start time =" + startTime);
            } else
                System.out.println("Benchmark Annotation is present but no time count is needed:" + benchMark.toString());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        Annotation annotation = bean.getClass().getAnnotation(BenchMark.class);
        BenchMark benchMark = (BenchMark) annotation;
        if (benchMark.countTime()) {
            long resultTime = System.nanoTime() - startTime;
            System.out.println("Benchmark annotation: The process of " + beanName + " creation took " + resultTime / 1000 + " mks");
        }
        return bean;
    }
}

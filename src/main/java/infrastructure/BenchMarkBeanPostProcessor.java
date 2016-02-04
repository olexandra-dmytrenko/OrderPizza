package infrastructure;

import domain.BenchMark;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.annotation.Annotation;

/**
 * Created by Oleksandra_Dmytrenko on 2/3/2016.
 */
//вызывается на этапе создания бинов после методов пост, инит, афтерПропертиз
//    на вход идет бин с засечеными конструктором и сеттерами
public class BenchMarkBeanPostProcessor implements BeanPostProcessor {

    private long startTime;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Before in postProcessBeforeInitialization: " + beanName);
        Annotation annotation = bean.getClass().getAnnotation(BenchMark.class);
        try {
            BenchMark benchMark = (BenchMark) annotation;
            if (benchMark.countTime()) {
                startTime = System.nanoTime();
                //      System.out.println("Benchmark Annotation is present: " + benchMark.toString() + " Start time =" + startTime);
            } else
                System.out.println("Benchmark Annotation is present but no time count is needed:" + benchMark.toString());

        } finally {
            return bean;
        }
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Annotation annotation = bean.getClass().getAnnotation(BenchMark.class);
        try {
            BenchMark benchMark = (BenchMark) annotation;
            if (benchMark.countTime()) {
                long resultTime = System.nanoTime() - startTime;
                System.out.println("Benchmark annotation: The process of " + beanName + " creation took " + resultTime / 1000 + " mks");
            } else {
                //  System.out.println("Benchmark Annotation is present but no time count is needed: " + benchMark.toString());
            }
        } finally {
            System.out.println("After from postProcessAfterInitialization: " + beanName);
            return bean;
        }
    }
}

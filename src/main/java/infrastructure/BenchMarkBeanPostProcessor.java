package infrastructure;

import domain.BenchMark;
import org.springframework.beans.BeansException;
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
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("After from postProcessAfterInitialization: " + beanName);
//        return bean;
        return preProcessorBenchmarkHandler(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("After from postProcessAfterInitialization: " + beanName);
//        return (new BenchMarkProxy(bean)).getProxy();

        return postProcessorBenchmarkHandler(bean, beanName);
    }

    private Object preProcessorBenchmarkHandler(Object bean, String beanName) {
        System.out.println("Before in postProcessBeforeInitialization: " + beanName);
        //   Annotation annotation = bean.getClass().getAnnotation(BenchMark.class);
        try {
       //     BenchMark benchMark = (BenchMark) annotation;
            for (Method method : bean.getClass().getDeclaredMethods()){
                BenchMark benchMark = method.getAnnotation(BenchMark.class);
                if (benchMark.countTime()) {
                    startTime = System.nanoTime();
                    //      System.out.println("Benchmark Annotation is present: " + benchMark.toString() + " Start time =" + startTime);
                } else
                    System.out.println("Benchmark Annotation is present but no time count is needed:" + benchMark.toString());
            }

        } finally {
            return bean;
        }
    }

    private Object postProcessorBenchmarkHandler(Object bean, String beanName) {
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

package proxy;

import domain.BenchMark;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.MessageFormat;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//import java.util.logging.Logger;

/**
 * Created by olexandra on 3/14/16.
 */
public class BenchMarkProxy {
    Object bean;
//    private static Logger log = Logger.getLogger("Log.txt");
    private static Logger log = Logger.getLogger(BenchMarkProxy.class);

    public BenchMarkProxy(Object bean) {
        this.bean = bean;
    }

    public Object getProxy() {
        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(BenchMark.class)) {
                return createProxy();
            }
        }
        return bean;
//        return Stream.of(bean.getClass().getDeclaredMethods())
//                .filter(method -> method.isAnnotationPresent(BenchMark.class))
//                .map(this::createProxy).findFirst().orElse(bean);
    }

    private Object createProxy() {
        System.out.println("In createProxy. Bean name = " + bean.getClass().getName() +" " + bean.getClass().getComponentType() + " " + bean.getClass().getSuperclass());
        //override invoke method
        Method[] methods = bean.getClass().getMethods();
        InvocationHandler invocator = (proxy, method, args) ->
                Stream.of(methods)
                        .filter(method1 -> method1.getName().equals(method.getName()) && method1.isAnnotationPresent(BenchMark.class))
                        .findFirst().map(method1 -> countTimeMethodPerforms(method1, args))
                        .orElse(method.invoke(bean, args));

        Class<?> beanClass = bean.getClass();
        beanClass = forSpringFactoryTakeParent(beanClass);
        return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), invocator);
    }

    private Class<?> forSpringFactoryTakeParent(Class<?> beanClass) {
        for (Class<?> c : beanClass.getInterfaces()) {
            if (beanClass.getName().contains("PizzaFactoryBean")){// beanClass.getName().equals("PizzaFactoryBean")){
                System.out.println("here you're pizzaFactoryBean!");
            }
            if (beanClass.isInstance(FactoryBean.class)){
                beanClass = beanClass.getComponentType();
            }
            if (c.getName().equals("org.springframework.cglib.proxy.Factory")) {
                beanClass = beanClass.getSuperclass();
                break;
            }
        }
        return beanClass;
    }

    private Object countTimeMethodPerforms(Method method, Object[] args) {
        long startTime = System.nanoTime();
        try {
            final String outputMethodCredentials = Stream.of(method.getParameterTypes()).map(Class::getName).collect(Collectors.joining(", ", " with parameters", " "));
            final Object returnValTemp = method.invoke(bean, args);
            log.info(MessageFormat.format("Benchmark INFO: The time the ''{0}{1}{2} ns.", method.getName(), outputMethodCredentials, System.nanoTime() - startTime));
            return returnValTemp;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}

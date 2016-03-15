package proxy;

import domain.BenchMark;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.MessageFormat;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by olexandra on 3/14/16.
 */
public class BenchMarkProxy {
    Object bean;

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
        System.out.println("In createProxy");
        InvocationHandler invocator = (proxy, method, args) -> {
            //invoke method
            Method[] methods = bean.getClass().getMethods();
            return Stream.of(methods)
                    .filter(method1 -> method1.getName().equals(method.getName()) && method1.isAnnotationPresent(BenchMark.class))
                    .findFirst().map(method1 -> {
                        long startTime = System.nanoTime();
                        try {
                            final String outputMethodCredentials = Stream.of(method.getParameterTypes()).map(p -> p.getName()).collect(Collectors.joining(", ", " with parameters", " "));

                            final Object returnValTemp = method.invoke(bean, args);
                            System.out.println(MessageFormat.format("Benchmark INFO: The time the ''{0}{1}{2} ns.", method.getName(), outputMethodCredentials, System.nanoTime() - startTime));
                            return returnValTemp;
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                            return null;
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }).orElse(method.invoke(bean, args));
        };

        Class<?> beanClass = bean.getClass();
//        for (Class<?> c : beanClass.getInterfaces()) {
//            if (c.getName().equals("org.springframework.cglib.proxy.Factory")) {
//                beanClass = beanClass.getSuperclass();
//                break;
//            }
//        }
        return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), invocator);
    }
}

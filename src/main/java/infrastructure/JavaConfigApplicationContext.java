package infrastructure;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.WeakHashMap;

/**
 * Created by olexandra on 1/25/16.
 */
public class JavaConfigApplicationContext implements ApplicationContext {

    private Config config;
    private WeakHashMap<Object, Object> beans = new WeakHashMap<>();

    public JavaConfigApplicationContext(Config config) {
        this.config = config;
    }

    @Override
    public Object getBean(String beanName) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Class<?> type = config.getImpl(beanName);
        Object bean = beans.get(beanName);
        if (bean != null) {
            return bean;
        }
        BeanBuilder builder = new BeanBuilder(type);
        builder.createBean();
        builder.createBeanProxy();
   //     builder.createInitMethod();
        bean = builder.build();

        beans.put(beanName, bean);
        return bean;

//        Object bean = getBean(parameters.getType.toClass)
    }

    class BeanBuilder {
        Class<?> type;
        Object bean;

        BeanBuilder(Class<?> type) {
            this.type = type;
        }

        public void createBean() throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
            Constructor<?> constructor = type.getConstructors()[0];
            Parameter[] parameters = constructor.getParameters();
            if (parameters.length == 0) {
                bean = type.newInstance();
            } else {
                Object[] params = new Object[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    Parameter parameter = parameters[i];
                    String paramType = parameter.getType().getSimpleName();
                    String beanName = Character.toLowerCase(paramType.charAt(0)) + paramType.substring(1);

                    params[i] = getBean(beanName);
                }
                bean = constructor.newInstance(params);
            }
        }

        public void createInitMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Class<?> clazz = bean.getClass();
            Method method = clazz.getMethod("init", new Class[0]);
            if (method != null) {
                method.invoke(bean);
            }

        }

        public void preDestroy() {
        }

        public void createBeanProxy() {
        }

        public Object build() {
            return bean;
        }
    }
}

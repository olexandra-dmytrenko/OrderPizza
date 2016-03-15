package infrastructure;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by olexandra on 1/25/16.
 */
public interface ApplicationContext {
    Object getBean(String beanName) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException;
}

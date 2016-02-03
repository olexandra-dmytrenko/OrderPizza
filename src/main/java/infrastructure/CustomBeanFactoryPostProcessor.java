package infrastructure;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;

/**
 * Created by Oleksandra_Dmytrenko on 2/3/2016.
 */
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition bd = beanFactory.getBeanDefinition("newCustomer");
        ConstructorArgumentValues cav = bd.getConstructorArgumentValues();
        System.out.println("CustomBeanFactoryPostProcessor count = " + cav.getArgumentCount());
        System.out.println("CustomBeanFactoryPostProcessor Val = " + cav.getArgumentValue(0, null).getValue());
        cav.getArgumentValue(0, null).setValue("Nick");
        bd.setScope("singleton");
        System.out.println("CustomBeanFactoryPostProcessor Val set = " + cav.getArgumentValue(0, null).getValue() );

    }
}

package service;

/**
 * Created by Oleksandra_Dmytrenko on 1/21/2016.
 * Pattern Service Locator
 */
public interface Config {

    public <T> Class<T> getImpl(String impl);
    //{
   //     return get(impl);
   // }
}

package ru.itis.context;

/**
 * 19.11.2018
 * ApplicationContext
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface ApplicationContext {
    <T> T getComponent(Class<T> componentClass);
}

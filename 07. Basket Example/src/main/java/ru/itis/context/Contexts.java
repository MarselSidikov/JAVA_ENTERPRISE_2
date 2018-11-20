package ru.itis.context;

/**
 * 19.11.2018
 * Contexts
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Contexts {
    public static ApplicationContextPrimitiveImpl primitive() {
        return ApplicationContextPrimitiveImpl.getContext();
    }
}

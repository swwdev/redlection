package org.lab5;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Properties;
import org.lab5.exeptions.UnknownTypeException;

public class Injector {
    private static final Properties PROPERTIES = new Properties();
    private Injector () {

    }

    static {
        InputStream resourceAsStream = Injector.class.getClassLoader().getResourceAsStream("injectionRules.properties");
        try {
            PROPERTIES.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static <T> void inject (T object) throws IOException {
        Field[] fields = object.getClass().getDeclaredFields();
        Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(AutoInjectable.class))
                .forEach(field -> setField(object,field));
    }

    private static <T> void setField (T object, Field field) {
        field.setAccessible(true);
        String implementationName = PROPERTIES.getProperty(field.getType().getName());
        try {
            if (implementationName == null)
                throw new UnknownTypeException("no rule for this type");
            Object impl = Class.forName(implementationName).getConstructor().newInstance();
            field.set(object, impl);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

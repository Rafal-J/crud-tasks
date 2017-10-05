package com.reflections.annotations;

import static org.reflections.ReflectionUtils.*;

import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

import static org.reflections.ReflectionUtils.getAllMethods;

public class MyAnnotation {
    public static void testingAnnotations() {
        Reflections reflections = new Reflections("com.reflections");
        Set<Class<?>> myAnnotations =
                reflections.getTypesAnnotatedWith(MyTable.class);
        System.out.println("\nLiczba klas z adnotacją: " + myAnnotations.size());

        System.out.println("\n");

        Class testClass = TestClass1.class;
        Set<Method> methods = getAllMethods(testClass,
                withModifier(Modifier.PUBLIC), withParametersCount(0), withAnnotation(MyColumn.class));
        System.out.println("Klasa " + testClass.getSimpleName() + " posiada " + methods.size() + " metody z adnotacją:");
        methods.stream()
                .map(c -> c.getName())
                .forEach(System.out::println);
    }
}

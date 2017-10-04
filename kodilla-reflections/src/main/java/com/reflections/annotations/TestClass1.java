package com.reflections.annotations;

@AnnotationDefinition
public class TestClass1 {
    @MethodAnnotationDefinition
    public static void testMethod1(){
        System.out.println("Test");
    }

    public static void testMethod2(){
        System.out.println("Test");
    }

    @MethodAnnotationDefinition
    public static void testMethod3(){
        System.out.println("Test");
    }
}

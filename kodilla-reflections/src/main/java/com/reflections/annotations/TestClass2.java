package com.reflections.annotations;

@MyTable
public class TestClass2 {
    @MyColumn
    public static void testMethod1(){
        System.out.println("Test");
    }

    public static void testMethod2(){
        System.out.println("Test");
    }

    public static void testMethod3(){
        System.out.println("Test");
    }
}

package com.reflections.annotations;

@MyTable
public class TestClass3 {
    @MyColumn
    public static void testMethod1(){
        System.out.println("Test");
    }

    @MyColumn
    public static void testMethod2(){
        System.out.println("Test");
    }

    @MyColumn
    public static void testMethod3(){
        System.out.println("Test");
    }
}

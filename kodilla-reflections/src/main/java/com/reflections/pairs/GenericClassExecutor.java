package com.reflections.pairs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericClassExecutor {
    public static void genericClassExecuting() {
        List<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        String description = "Moja lista liczb naturalnych";
        MyGenericClass<List<Integer>, String> myPair = new MyGenericClass<>(integers, description);

        System.out.println(myPair.getObject2() + ":");
        myPair.getObject1().stream()
                .forEach(System.out::println);
    }
}

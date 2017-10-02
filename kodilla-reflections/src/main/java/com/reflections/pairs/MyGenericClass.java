package com.reflections.pairs;

public class MyGenericClass<T1, T2> {
    private T1 object1;
    private T2 object2;

    public MyGenericClass(T1 t1, T2 t2) {
        this.object1 = t1;
        this.object2 = t2;
    }

    public T1 getObject1() {
        return object1;
    }

    public T2 getObject2() {
        return object2;
    }

    public void setObject1(T1 t1) {
        this.object1 = object1;
    }

    public void setObject2(T2 t2) {
        this.object2 = object2;
    }
}

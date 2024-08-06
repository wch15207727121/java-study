package com.testdemo;

public class testdemo {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        System.out.println(cl);

//        Class<?> c = cl.loadClass("testdemo1");
//        new testdemo1();

//        testdemo1.staticAciton();
//        testdemo1.id = 1;

//        Class c = testdemo1.class;
//          Class c = Class.forName("com.testdemo.Testdemo1",false,cl);

        //loadclass 不会进行初始化
//        Class<?> c = cl.loadClass("com.testdemo.Testdemo1");
//        c.newInstance();

    }
}

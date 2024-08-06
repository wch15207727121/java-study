package com.testdemo;

import java.io.Serializable;

public class Testdemo1 implements Serializable {

    public String name;
    private int age;

    public static int id;

    public static void staticAciton(){
        System.out.println("静态方法");
    }
    static{System.out.println("静态代码块");}
    Testdemo1(){
        System.out.println("无参构造函数");
    }
    public Testdemo1(String name, int age){
        System.out.println("有参构造函数");
    }
    {
        System.out.println("构造代码块");
    }

    @Override
    public String toString() {
        return "testdemo1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }


}

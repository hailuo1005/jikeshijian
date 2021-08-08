package com.jike.javacode.jvm;

import java.io.*;

public class ClassLoader extends java.lang.ClassLoader {

    public static void main(String[] args) throws Exception {
        new ClassLoader().findClass("com.jike.javacode.jvm.Hello").newInstance();
    }


    @Override
    protected Class<?> findClass(String name) {

        File readFile = new File("src/main/java/com/jike/javacode/jvm/Hello.xlass");
        File outFile = new File("src/main/java/com/jike/javacode/jvm/Hello.class");

        FileInputStream fileInputStream = null;
        FileOutputStream outputStream = null;
        try {
            fileInputStream = new FileInputStream(readFile);
            outputStream = new FileOutputStream(outFile);
            int len = fileInputStream.available();

            byte[] bytes = new byte[len];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }

            outputStream.write(bytes);

            return defineClass(name, bytes, 0, bytes.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

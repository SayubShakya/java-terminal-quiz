package com.sayub.converter.impl;

import com.sayub.converter.JavaObjectFileConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JavaObjectFileConverterJavaImpl<T> implements JavaObjectFileConverter<T> {

    private static final String FILE_SUFFIX = ".obj";

    @Override
    public void serialize(T object, String fileName) {

        File file = new File(fileName + FILE_SUFFIX);

        try {

            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            
            fileOutputStream.close();

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public T deserialize(String fileName) {

        try {

            FileInputStream fileInputStream = new FileInputStream(fileName + FILE_SUFFIX);

            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            T object = (T) objectInputStream.readObject();

            objectInputStream.close();

            fileInputStream.close();

            return object;

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }

    }
}

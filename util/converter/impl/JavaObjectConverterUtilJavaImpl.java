package util.converter.impl;

import util.converter.JavaObjectConverterUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JavaObjectConverterUtilJavaImpl<T> implements JavaObjectConverterUtil<T> {

    private static final String FILE_SUFFIX = ".obj";

    @Override
    public void serialize(T object, String fileName) {
        // Write data to file
        try (FileOutputStream fos = new FileOutputStream(fileName + FILE_SUFFIX)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("File not found: " + fileName);
        }
    }

    @Override
    public T deserialize(String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName + FILE_SUFFIX);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            T object = (T) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

            return object;
        } catch (Exception e) {
            System.out.println("File not found: " + fileName);
            return null;
        }
    }
}

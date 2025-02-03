package util.converter.impl;

import array.DynamicQuestionArray;
import util.converter.JavaObjectConverterUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JavaObjectConverterUtilJavaImpl implements JavaObjectConverterUtil<DynamicQuestionArray> {

    private static final String FILE_SUFFIX = ".obj";

    @Override
    public void serialize(DynamicQuestionArray object, String fileName) {
        // Write data to file
        try (FileOutputStream fos = new FileOutputStream(fileName + FILE_SUFFIX)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    @Override
    public DynamicQuestionArray deserialize(String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName + FILE_SUFFIX);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            DynamicQuestionArray object = (DynamicQuestionArray) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

            return object;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return null;
        }
    }
}

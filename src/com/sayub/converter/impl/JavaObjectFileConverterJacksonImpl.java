package com.sayub.converter.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sayub.converter.JavaObjectFileConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class JavaObjectFileConverterJacksonImpl<T> implements JavaObjectFileConverter<T> {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String FILE_SUFFIX = ".json";
    private final TypeReference<T> typeReference;

    public JavaObjectFileConverterJacksonImpl(TypeReference<T> typeReference) {
        this.typeReference = typeReference;
    }

    @Override
    public void serialize(T object, String fileName) {

        File file = new File(fileName + FILE_SUFFIX);

        try {

            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            
            String json = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            byte[] dataInBytes = json.getBytes();
            fileOutputStream.write(dataInBytes);

            fileOutputStream.close();

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    @Override
    public T deserialize(String fileName) {

        try {

            FileInputStream fileInputStream = new FileInputStream(fileName + FILE_SUFFIX);

            byte[] dataInBytes = fileInputStream.readAllBytes();

            if(dataInBytes.length == 0){
                return null;
            }

            String json = new String(dataInBytes);

            fileInputStream.close();

            return MAPPER.readValue(json, typeReference);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }

    }
}

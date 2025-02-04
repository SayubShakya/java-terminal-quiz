package util.converter.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import util.converter.JavaObjectConverterUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class JavaObjectConverterUtilJacksonImpl<T> implements JavaObjectConverterUtil<T> {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String FILE_SUFFIX = ".json";


    @Override
    public void serialize(T object, String fileName) {

        File file = new File(fileName + FILE_SUFFIX);


        try (FileWriter fw = new FileWriter(fileName + FILE_SUFFIX)) {

            if (!file.exists()) {
                boolean fileCreated = file.createNewFile();
                if (fileCreated) {
                    System.out.println("File created");
                } else {
                    System.out.println("File not created");
                }
            }
            String json = MAPPER.writeValueAsString(object);
            fw.write(json);
        } catch (Exception e) {
            System.out.println("Json processing exception");
        }
    }

    @Override
    public T deserialize(String fileName) {
        try {

            Scanner sc = new Scanner(new File(fileName + FILE_SUFFIX));
            String json = "";
            while (sc.hasNextLine()) {
                json += sc.nextLine();
            }

            return MAPPER.readValue(json, new TypeReference<T>() {
            });

        } catch (JsonProcessingException e) {
            System.out.println("jackson error: "+e.getMessage());
            return null;
        } catch (FileNotFoundException e) {
            System.out.println("jackson error: "+e.getMessage());
        }
        return null;
    }
}


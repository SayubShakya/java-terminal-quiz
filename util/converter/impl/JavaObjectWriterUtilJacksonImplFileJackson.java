package util.converter.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import util.converter.JavaObjectConverterUtil;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class JavaObjectWriterUtilJacksonImplFileJackson<T> implements JavaObjectConverterUtil<T> {


    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String FILE_SUFFIX = ".json";
    private final TypeReference<T> typeReference;

    public JavaObjectWriterUtilJacksonImplFileJackson(TypeReference<T> typeReference) {
        this.typeReference = typeReference;
    }

    @Override
    public void serialize(T object, String fileName) {

        File file = new File(fileName + FILE_SUFFIX);

        try (FileWriter fw = new FileWriter(fileName + FILE_SUFFIX)) {

            if (!file.exists()) {
                file.createNewFile();
            }
            ObjectWriter writer = MAPPER.writerFor(typeReference);
            String json = writer.writeValueAsString(object);

            fw.write(json);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    @Override
    public T deserialize(String fileName) {
        ObjectReader reader = MAPPER.readerFor(typeReference);
        try {
            File file = new File(fileName + FILE_SUFFIX);

            if (!file.exists()) {
                return null;
            }

            Scanner sc = new Scanner(file);
            String json = "";

            while (sc.hasNextLine()) {
                json += sc.nextLine();
            }

            return reader.readValue(json);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }
}


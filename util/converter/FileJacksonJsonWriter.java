package util.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Scanner;

public abstract class FileJacksonJsonWriter<T> {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String FILE_SUFFIX = ".json";

    private final Type conversionType;
    private final ObjectReader reader;
    private final ObjectWriter writer;

    public FileJacksonJsonWriter() {

        conversionType = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        TypeReference<T> typeReference = new TypeReference<T>() {
            @Override
            public Type getType() {
                return conversionType;
            }
        };

        reader = MAPPER.readerFor(typeReference);
        writer = MAPPER.writerFor(typeReference);
    }


    public T deserialize(String fileName) throws JsonProcessingException {
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

    public void serialize(T object, String fileName) throws JsonProcessingException {

        File file = new File(fileName + FILE_SUFFIX);

        try (FileWriter fw = new FileWriter(fileName + FILE_SUFFIX)) {

            if (!file.exists()) {
                file.createNewFile();
            }

            String json = writer.writeValueAsString(object);

            fw.write(json);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
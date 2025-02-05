package converter.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import converter.JavaObjectFileConverter;

import java.io.File;
import java.io.FileInputStream;
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

        try (FileWriter fw = new FileWriter(fileName + FILE_SUFFIX)) {

            if (!file.exists()) {
                file.createNewFile();
            }
            String json = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);

            fw.write(json);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    @Override
    public T deserialize(String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName + FILE_SUFFIX)) {
            byte[] dataInBytes = fileInputStream.readAllBytes();
            if(dataInBytes.length == 0){
                return null;
            }
            String json = new String(dataInBytes);
            return MAPPER.readValue(json, typeReference);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }
}


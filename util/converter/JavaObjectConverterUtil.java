package util.converter;

public interface JavaObjectConverterUtil<T> {

    void serialize(T object, String fileName);

    T deserialize(String fileName);

}

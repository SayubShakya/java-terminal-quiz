package com.sayub.converter;

public interface JavaObjectFileConverter<T> {

    void serialize(T object, String fileName);

    T deserialize(String fileName);
}

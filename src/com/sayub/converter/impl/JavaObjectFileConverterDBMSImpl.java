package com.sayub.converter.impl;

import com.sayub.converter.JavaObjectFileConverter;

public class JavaObjectFileConverterDBMSImpl <T> implements JavaObjectFileConverter<T> {
    @Override
    public void serialize(T object, String fileName) {

    }

    @Override
    public T deserialize(String fileName) {
        return null;
    }
}

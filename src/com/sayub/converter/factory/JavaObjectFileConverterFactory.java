package com.sayub.converter.factory;


import com.fasterxml.jackson.core.type.TypeReference;
import com.sayub.converter.JavaObjectFileConverter;
import com.sayub.converter.impl.JavaObjectFileConverterJacksonImpl;
import com.sayub.converter.impl.JavaObjectFileConverterJavaImpl;

/**
 * @author Manjit Shakya
 */
public class JavaObjectFileConverterFactory {

    public static <T> JavaObjectFileConverter<T> createConverter(com.sayub.converter.factory.JavaObjectFileConverterType type, TypeReference<T> typeReference) {
        return switch (type) {
            case JAVA -> new JavaObjectFileConverterJavaImpl<>();
            case JACKSON -> new JavaObjectFileConverterJacksonImpl<T>(typeReference);
        };
    }
}

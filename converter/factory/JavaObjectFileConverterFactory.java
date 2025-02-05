package converter.factory;


import com.fasterxml.jackson.core.type.TypeReference;
import converter.JavaObjectFileConverter;
import converter.impl.JavaObjectFileConverterJacksonImpl;
import converter.impl.JavaObjectFileConverterJavaImpl;

/**
 * @author Manjit Shakya
 */
public class JavaObjectFileConverterFactory {

    public static <T> JavaObjectFileConverter<T> createConverter(JavaObjectFileConverterType type, TypeReference<T> typeReference) {
        return switch (type) {
            case JAVA -> new JavaObjectFileConverterJavaImpl<>();
            case JACKSON -> new JavaObjectFileConverterJacksonImpl<T>(typeReference);
        };
    }
}

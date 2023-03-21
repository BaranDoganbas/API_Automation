package util;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class ObjectMapperUtils {

    public static <T> T convertJsonToJava(String json, Class<T> cls) {// Generic Method
        try {
            return new ObjectMapper().readValue(json, cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

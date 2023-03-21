package util;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class ObjectMapperUtils {

//    <T> T ==> Herhangi bir data tipi (Generic) -->
//    ObjectMapper(),readValue(json, cls) method'u birinci parametrede aldigi String formatindaki Json data'yi
//    ikinci parametrede belirtilen Java object'ine cevirir.
    public static <T> T convertJsonToJava(String json, Class<T> cls) {// Generic Method
        try {
            return new ObjectMapper().readValue(json, cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

package hexlet.code;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Parser {

    public static Map<String, Object> parse(String result, String filetype) {
        try {
            switch (filetype) {
                case "json":
                    return new ObjectMapper().readValue(result, new TypeReference<>() {
                    });
                case "yml":
                    return new ObjectMapper(new YAMLFactory()).readValue(result, new TypeReference<>() {
                    });
                default:
                    throw new UnsupportedOperationException("Unsupported filetype: " + filetype);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}

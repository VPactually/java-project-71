package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Parser {
    public static File toFile(String filepath) {
        return Paths.get(filepath).toAbsolutePath().normalize().toFile();
    }

    public static Map<String, Object> parse(String filepath) {
        var mapper = new ObjectMapper(new YAMLFactory());
        File file = toFile(filepath);
        try {
            return mapper.readValue(file, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
